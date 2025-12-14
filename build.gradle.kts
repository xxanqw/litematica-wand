plugins {
    `maven-publish`
    id("dev.architectury.loom")
}

version = "${property("mod.version")}+${stonecutter.current.version}"
base.archivesName = property("mod.id") as String

val activeLoader = project.findProperty("loader") as? String ?: "fabric"
println("Configuring for loader: $activeLoader")

if (activeLoader == "neoforge") {
    ext["loom.platform"] = "neoforge"
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.neoforged.net/releases/")
    maven("https://maven.quiltmc.org/repository/release/")
    maven("https://maven.quiltmc.org/repository/snapshot/")

    fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
        forRepository { maven(url) { name = alias } }
        filter { groups.forEach(::includeGroup) }
    }
    strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")
}

dependencies {
    minecraft("com.mojang:minecraft:${stonecutter.current.version}")

    if (activeLoader == "neoforge") {
        println("Applying NeoForge Mappings")
        mappings(loom.officialMojangMappings()) 
    } else {
        println("Applying Fabric/Yarn Mappings")
        mappings("net.fabricmc:yarn:${property("deps.yarn")}:v2")
    }

    // FABRIC
    if (activeLoader == "fabric") {
        modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
        fun fapi(vararg modules: String) {
            for (it in modules) modImplementation(fabricApi.module(it, property("deps.fabric_api") as String))
        }
        fapi(
            "fabric-lifecycle-events-v1",
            "fabric-resource-loader-v0",
            "fabric-content-registries-v0",
            "fabric-item-group-api-v1",
            "fabric-item-api-v1"
        )
    }

    // QUILT
    else if (activeLoader == "quilt") {
        modImplementation("org.quiltmc:quilt-loader:${property("deps.quilt_loader")}")
        modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    }
    
    // NEOFORGE
    else if (activeLoader == "neoforge") {
        println("Adding NeoForge Dependency")
        val neoDep = "net.neoforged:neoforge:${property("deps.neoforge")}"
        modImplementation(neoDep)
        compileOnly(neoDep) 
    }
}

sourceSets {
    main {
        val activeLoader = project.findProperty("loader") as? String ?: "fabric"
        
        java.srcDir("src/main/java")
        resources.srcDir("src/main/resources")

        if (activeLoader == "fabric") {
            java.srcDir("src/fabric/java")
            resources.srcDir("src/fabric/resources")
        } else if (activeLoader == "neoforge") {
            java.srcDir("src/neoForge/java")
            resources.srcDir("src/neoForge/resources")
        } else if (activeLoader == "quilt") {
            java.srcDir("src/fabric/java")
            resources.srcDir("src/fabric/resources")
        }
    }
}

loom {    
    decompilerOptions.named("vineflower") {
        options.put("mark-corresponding-synthetics", "1")
    }

    runConfigs.all {
        ideConfigGenerated(true)
        vmArgs("-Dmixin.debug.export=true")
        runDir = "../../run"
    }
}

java {
    withSourcesJar()
    val requiresJava21: Boolean = stonecutter.eval(stonecutter.current.version, ">=1.20.6")
    val javaVersion: JavaVersion = if (requiresJava21) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
    targetCompatibility = javaVersion
    sourceCompatibility = javaVersion
}

tasks {
    processResources {
        inputs.property("version", project.property("mod.version"))
        inputs.property("minecraft", project.property("mod.mc_dep"))
        inputs.property("loader", activeLoader)
        
        val props = mapOf(
            "id" to project.property("mod.id"),
            "name" to project.property("mod.name"),
            "version" to project.property("mod.version"),
            "minecraft" to project.property("mod.mc_dep"),
            "file" to mapOf("jarVersion" to project.property("mod.version")),
            "loader_version_range" to "[1,)", 
            "minecraft_version_range" to "[${project.property("mod.mc_dep")},)"
        )

        filesMatching(listOf("fabric.mod.json", "quilt.mod.json", "META-INF/neoforge.mods.toml")) {
            expand(props)
        }
        
        if (activeLoader == "neoforge") {
            exclude("fabric.mod.json")
            exclude("quilt.mod.json")
            exclude("**/fabric.mod.json")
        } else {
            exclude("META-INF/neoforge.mods.toml")
            exclude("**/neoforge.mods.toml")
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    val outputDir = rootProject.layout.projectDirectory.dir("versions/$activeLoader/${stonecutter.current.version}")
    
    val finalModId = project.property("mod.id").toString()
    val finalModVersion = project.property("mod.version").toString()
    val finalLoader = activeLoader
    val mcRange = project.findProperty("mod.mc_title")?.toString() ?: stonecutter.current.version

    register<Copy>("collectJars") {
        group = "build"
        description = "Collects built jars into loader/version specific folders in project root"
        
        dependsOn("remapJar", "remapSourcesJar")
        
        from(remapJar.flatMap { it.archiveFile })
        
        into(outputDir)
        
        rename { 
            "$finalModId-$finalModVersion+$mcRange-$finalLoader.jar"
        }
    }

    named("build") {
        finalizedBy("collectJars")
    }
}
