package io.github.supermonster003.autojs6.plugin.bundled.libraries

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import org.autojs.plugin.common.api.IPluginInfoProvider
import org.autojs.plugin.common.api.PluginCapabilityKeys
import org.autojs.plugin.common.api.PluginInfo

/**
 * Answers `org.autojs.plugin.INFO` (category `script-modules`) for the Plugin Center list. Scripts never reach this
 * service: AutoJs6 reads the modules from the APK.
 * zh-CN: 为插件中心列表响应 `org.autojs.plugin.INFO` (category `script-modules`). 脚本不会用到此服务: AutoJs6 直接从 APK 读取模块.
 */
class BundledLibrariesInfoService : Service() {

    private val binder = object : IPluginInfoProvider.Stub() {
        override fun getInfo(): PluginInfo = pluginInfo()
    }

    override fun onBind(intent: Intent?): IBinder = binder

}

internal fun Context.pluginInfo(): PluginInfo {
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    return PluginInfo().apply {
        name = getString(R.string.app_name)
        description = getString(R.string.plugin_description)
        instruction = BundledLibrariesContract.INSTRUCTION_REFERENCE
        author = "SuperMonster003"
        versionName = packageInfo.versionName.orEmpty()
        versionCode = packageInfo.versionCodeCompat()
        versionDate = BuildConfig.VERSION_DATE
        id = BundledLibrariesContract.PLUGIN_ID
        engine = BundledLibrariesContract.ENGINE
        supportedAbis = emptyArray()
        capabilities = Bundle().apply {
            putLong(PluginCapabilityKeys.REQUIRES_HOST_VERSION, BuildConfig.REQUIRED_HOST_VERSION_CODE)
        }
    }
}

private fun PackageInfo.versionCodeCompat(): Long {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) return longVersionCode
    @Suppress("DEPRECATION")
    return versionCode.toLong()
}
