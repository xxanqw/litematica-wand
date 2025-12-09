plugins {
    id("dev.kikugie.stonecutter")
    id("fabric-loom") version "1.14-SNAPSHOT" apply false
}

stonecutter {
    active("1.21.11")
}

stonecutter parameters {
    data class VersionInfo(val fabricApi: String, val yarn: String, val loader: String)

    val versions = mapOf(
        "1.20.1" to VersionInfo("0.92.2+1.20.1", "1.20.1+build.10", "0.16.9"),
        "1.20.2" to VersionInfo("0.91.2+1.20.2", "1.20.2+build.4", "0.16.9"),
        "1.20.3" to VersionInfo("0.91.2+1.20.3", "1.20.3+build.1", "0.16.9"),
        "1.20.4" to VersionInfo("0.97.0+1.20.4", "1.20.4+build.3", "0.16.9"),

        "1.21.4" to VersionInfo("0.119.4+1.21.4", "1.21.4+build.8", "0.16.9"), 
        "1.21.5" to VersionInfo("0.127.1+1.21.5", "1.21.5+build.1", "0.16.9"),
        "1.21.6" to VersionInfo("0.128.0+1.21.6", "1.21.6+build.1", "0.16.9"),
        "1.21.7" to VersionInfo("0.130.0+1.21.7", "1.21.7+build.1", "0.16.9"),
        "1.21.8" to VersionInfo("0.132.0+1.21.8", "1.21.8+build.1", "0.16.9"),
        "1.21.9" to VersionInfo("0.134.0+1.21.9", "1.21.9+build.1", "0.16.9"),
        "1.21.10" to VersionInfo("0.136.0+1.21.10", "1.21.10+build.1", "0.16.9"),
        "1.21.11" to VersionInfo("0.138.4+1.21.11", "1.21.11+build.1", "0.16.9")
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

    if (verInfo != null) {
        setIfMissing("deps.fabric_api", verInfo.fabricApi)
        setIfMissing("deps.yarn", verInfo.yarn)
        setIfMissing("deps.fabric_loader", verInfo.loader)
    }

    setIfMissing("mod.mc_dep", currentMc)
}
