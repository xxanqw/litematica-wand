pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net/")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7.11"
}

stonecutter {
    create(rootProject) {
        versions("1.21.4")
        vcsVersion = "1.21.4"
    }
}

rootProject.name = "Litematica Wand"
