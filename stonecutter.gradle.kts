plugins {
    id("dev.kikugie.stonecutter")
    id("net.fabricmc.fabric-loom") version "1.15-SNAPSHOT" apply false
}

stonecutter {
    active("26.1")
}

stonecutter parameters {
    data class VersionInfo(
        val fabricApi: String,
        val fabricLoader: String,
    )

    val versions = mapOf(
        "26.1" to VersionInfo(
            "0.145.1+26.1", "0.18.5",
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

    if (verInfo != null) {
        setIfMissing("deps.fabric_api", verInfo.fabricApi)
        setIfMissing("deps.fabric_loader", verInfo.fabricLoader)
    }

    setIfMissing("mod.mc_dep", currentMc)
}
