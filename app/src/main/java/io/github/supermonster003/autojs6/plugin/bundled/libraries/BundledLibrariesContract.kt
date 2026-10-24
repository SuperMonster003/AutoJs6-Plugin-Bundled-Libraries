package io.github.supermonster003.autojs6.plugin.bundled.libraries

/**
 * The names AutoJs6 relies on (host roadmap T11 / P4.3): the catalog meta-data, the per-library aliases and the
 * Plugin Center identity.
 * zh-CN: AutoJs6 依赖的名称 (宿主 ROADMAP T11 / P4.3): 清单 meta-data, 每个库的 alias 与插件中心身份.
 */
internal object BundledLibrariesContract {

    /** Plugin Center id. */
    const val PLUGIN_ID = "bundled-libraries"

    /** Plugin Center category (the `org.autojs.plugin.INFO` category of the info service). */
    const val ENGINE = "script-modules"

    /** Application meta-data with the asset path of the catalog. */
    const val META_DATA_CATALOG = "org.autojs.plugin.scriptModules.CATALOG"

    /** The action of the per-library aliases; a disabled alias is a library switched off. */
    const val ACTION_LIBRARY = "org.autojs.plugin.SCRIPT_MODULE_LIBRARY"

    /** Alias meta-data with the library id. */
    const val META_DATA_LIBRARY = "org.autojs.plugin.scriptModules.LIBRARY"

    /** The alias of library [id], relative to the package. */
    fun aliasName(packageName: String, id: String) = "$packageName.library.$id"

}
