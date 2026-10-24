package io.github.supermonster003.autojs6.plugin.bundled.libraries

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import org.json.JSONObject

/** A library of `assets/script-modules.json`. zh-CN: `assets/script-modules.json` 中的一个库. */
internal data class Library(
    val id: String,
    val group: String,
    val version: String?,
    val license: String,
    val modules: List<String>,
)

/**
 * The catalog that AutoJs6 reads as well (host roadmap P4.3 (a)), and the per-library switches: a library is on unless
 * its alias is disabled, so a fresh install has every library on (roadmap Q100 of the host).
 * zh-CN: AutoJs6 同样读取的清单 (宿主 ROADMAP P4.3 (a)), 以及每个库的开关: alias 未被禁用即为开启, 因此新安装时全部开启 (宿主 ROADMAP Q100).
 */
internal object LibraryCatalog {

    const val GROUP_LIBRARIES = "libraries"
    const val GROUP_NODE_SHIMS = "node-shims"

    fun read(context: Context): List<Library> {
        val text = context.assets.open("script-modules.json").bufferedReader(Charsets.UTF_8).use { it.readText() }
        val libraries = JSONObject(text).getJSONArray("libraries")
        return (0 until libraries.length()).map { index ->
            val library = libraries.getJSONObject(index)
            val modules = library.getJSONObject("modules")
            Library(
                id = library.getString("id"),
                group = library.getString("group"),
                version = library.optString("version").takeIf { it.isNotEmpty() },
                license = library.getString("license"),
                modules = modules.keys().asSequence().toList(),
            )
        }
    }

    fun isEnabled(context: Context, id: String): Boolean =
        when (context.packageManager.getComponentEnabledSetting(alias(context, id))) {
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER,
            -> false
            else -> true
        }

    /** Switches library [id]; AutoJs6 sees the change through the PACKAGE_CHANGED broadcast, without starting this process. */
    fun setEnabled(context: Context, id: String, enabled: Boolean) {
        val state = if (enabled) PackageManager.COMPONENT_ENABLED_STATE_DEFAULT else PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        context.packageManager.setComponentEnabledSetting(alias(context, id), state, PackageManager.DONT_KILL_APP)
    }

    private fun alias(context: Context, id: String) =
        ComponentName(context.packageName, BundledLibrariesContract.aliasName(context.packageName, id))

}
