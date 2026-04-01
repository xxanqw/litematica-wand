plugins {
    `maven-publish`
    id("net.fabricmc.fabric-loom")
}

version = "${property("mod.version")}+${stonecutter.current.version}"
base.archivesName = property("mod.id") as String

println("Configuring Fabric for ${stonecutter.current.version}")

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")

    fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
        forRepository { maven(url) { name = alias } }
        filter { groups.forEach(::includeGroup) }
    }
    strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")
}

dependencies {
    minecraft("com.mojang:minecraft:${stonecutter.current.version}")

    implementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    implementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
}

sourceSets {
    main {
        java.srcDir("src/main/java")
        java.srcDir("src/fabric/java")
        resources.srcDir("src/main/resources")
        resources.srcDir("src/fabric/resources")
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
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
    targetCompatibility = JavaVersion.VERSION_25
    sourceCompatibility = JavaVersion.VERSION_25
}

tasks {
    processResources {
        inputs.property("version", project.property("mod.version"))
        inputs.property("minecraft", project.property("mod.mc_dep"))

        val props = mapOf(
            "id" to project.property("mod.id"),
            "name" to project.property("mod.name"),
            "version" to project.property("mod.version"),
            "minecraft" to project.property("mod.mc_dep")
        )

        filesMatching("fabric.mod.json") {
            expand(props)
        }

        exclude("META-INF/neoforge.mods.toml")
        exclude("**/neoforge.mods.toml")

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    val outputDir = rootProject.layout.projectDirectory.dir("versions/fabric/${stonecutter.current.version}")

    val finalModId = project.property("mod.id").toString()
    val finalModVersion = project.property("mod.version").toString()
    val mcRange = stonecutter.current.version

    register<Copy>("collectJars") {
        group = "build"
        description = "Collects built jars into version-specific folders in project root"

        dependsOn("jar")

        from(jar.flatMap { it.archiveFile })

        into(outputDir)

        rename {
            "$finalModId-$finalModVersion+$mcRange-fabric.jar"
        }
    }

    named("build") {
        finalizedBy("collectJars")
    }
}
