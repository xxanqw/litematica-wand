plugins {
    id("dev.kikugie.stonecutter")
    id("dev.architectury.loom") version "1.13-SNAPSHOT" apply false
}

stonecutter {
    active("1.21.4")
}

stonecutter parameters {
    data class VersionInfo(
        val fabricApi: String,
        val yarn: String,
        val fabricLoader: String,
        val neoforge: String,
        val quiltLoader: String,
        val quiltStdLib: String
    )

    val versions = mapOf(
        "1.21.4" to VersionInfo(
            "0.119.4+1.21.4", "1.21.4+build.8", "0.16.9",       // Fabric
            "21.4.156",                                         // NeoForge
            "0.30.0-beta.0", "10.0.0-alpha.6+1.21.1-SNAPSHOT"   // Quilt
        ),
        
        "1.21.11" to VersionInfo(
            "0.138.4+1.21.11", "1.21.11+build.1", "0.16.9",     // Fabric
            "21.11.1-beta",                                     // NeoForge
            "0.27.1", "11.0.0-beta.6+1.21.11"                   // Quilt
        )
    )

    val currentMc = node.metadata.version
    val verInfo = versions[currentMc] 
    val ext = node.project.extensions.extraProperties

    fun setIfMissing(key: String, value: Any) {
        if (!ext.has(key)) {
            ext[key] = value
        }
    }

    swaps["mod_version"] = "\"" + property("mod.version") + "\";"
    swaps["minecraft"] = "\"" + currentMc + "\";"
    constants["release"] = property("mod.id") != "template"

    val activeLoader = (if (node.project.hasProperty("loader")) node.project.property("loader") else "fabric") as String

    constants["fabric"] = activeLoader == "fabric"
    constants["neoforge"] = activeLoader == "neoforge"
    constants["quilt"] = activeLoader == "quilt"
    constants["forge"] = activeLoader == "forge"

    if (verInfo != null) {
        setIfMissing("deps.fabric_api", verInfo.fabricApi)
        setIfMissing("deps.yarn", verInfo.yarn)
        setIfMissing("deps.fabric_loader", verInfo.fabricLoader)
        setIfMissing("deps.neoforge", verInfo.neoforge)
        setIfMissing("deps.quilt_loader", verInfo.quiltLoader)
        setIfMissing("deps.quilt_stdlib", verInfo.quiltStdLib)
    }

    setIfMissing("mod.mc_dep", currentMc)
}
