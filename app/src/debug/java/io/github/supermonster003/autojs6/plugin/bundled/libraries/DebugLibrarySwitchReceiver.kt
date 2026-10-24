package io.github.supermonster003.autojs6.plugin.bundled.libraries

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Debug builds only: the library switches from adb, for the device check of the four plugin states in AutoJs6
 * (`tools/augment/device_plugin_modules_check.py`). Since Android 9 the shell may not change the component state of an
 * app that is not test-only, so `pm disable` of an alias is refused. Only senders holding DUMP (the adb shell) reach it.
 *
 *     am broadcast -n <package>/<package>.DebugLibrarySwitchReceiver --es library axios,buffer --ez enabled false
 *
 * The result data lists the state of each library after the change, as `axios=false,buffer=false`.
 * zh-CN: 仅 debug 构建: 从 adb 切换库开关, 供 AutoJs6 的插件四态真机检查使用. Android 9 起 shell 不能改非 test-only 应用的组件状态, `pm disable` alias 会被拒绝.
 * 只有持有 DUMP 的发送者 (adb shell) 能到达. 结果数据为改后每个库的状态.
 */
class DebugLibrarySwitchReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val ids = intent.getStringExtra(EXTRA_LIBRARY).orEmpty().split(',').map { it.trim() }.filter { it.isNotEmpty() }
        val unknown = ids - LibraryCatalog.read(context).map { it.id }.toSet()
        if (ids.isEmpty() || unknown.isNotEmpty()) {
            resultCode = Activity.RESULT_CANCELED
            resultData = "unknown library: ${unknown.joinToString(",")}"
            return
        }
        val enabled = intent.getBooleanExtra(EXTRA_ENABLED, true)
        ids.forEach { LibraryCatalog.setEnabled(context, it, enabled) }
        resultCode = Activity.RESULT_OK
        resultData = ids.joinToString(",") { "$it=${LibraryCatalog.isEnabled(context, it)}" }
    }

    private companion object {
        const val EXTRA_LIBRARY = "library"
        const val EXTRA_ENABLED = "enabled"
    }

}
