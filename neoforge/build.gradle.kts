plugins {
    id("net.neoforged.moddev") version "2.0.141"
}

val modVersion: String by project
val modGroup: String by project
val modId: String by project
val modName: String by project
val modMcDep: String by project
val neoforgeVersion: String by project

version = modVersion
group = modGroup
base.archivesName = modId

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases/")
}

dependencies {
    implementation("net.neoforged:neoforge:$neoforgeVersion")
}

neoForge {
    version = neoforgeVersion

    runs {
        register("client") {
            client()
        }
        register("server") {
            server()
        }
    }

    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
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
        inputs.property("version", modVersion)
        inputs.property("minecraft", modMcDep)

        val props = mapOf(
            "id" to modId,
            "name" to modName,
            "version" to modVersion,
            "minecraft" to modMcDep
        )

        filesMatching("META-INF/neoforge.mods.toml") {
            expand(props)
        }
    }
}
