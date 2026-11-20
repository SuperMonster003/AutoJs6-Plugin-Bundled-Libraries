package io.github.supermonster003.autojs6.plugin.bundled.libraries

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Switch
import android.widget.TextView

/**
 * The launch page: the libraries in two sections (libraries and Node compatibility shims), one switch each, and the
 * release history, in AutoJs6's appearance. A switch takes effect for the next `require` of a module that the running
 * script has not loaded yet.
 * zh-CN: 启动页: 库分两节 (库与 Node 兼容垫片), 每个一个开关, 以及发行历史, 使用 AutoJs6 的外观. 开关对运行中的脚本尚未加载的模块,
 * 在下一次 `require` 时生效.
 */
class SettingsActivity : HostAppearanceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val libraries = LibraryCatalog.read(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(16), dp(8), dp(16), dp(16))
        }
        content.addView(text(getString(R.string.text_settings_summary), 14f, secondary = true))
        listOf(
            LibraryCatalog.GROUP_LIBRARIES to R.string.text_group_libraries,
            LibraryCatalog.GROUP_NODE_SHIMS to R.string.text_group_node_shims,
        ).forEach { (group, title) ->
            content.addView(text(getString(title), 16f).apply { setPadding(0, dp(20), 0, dp(4)) })
            libraries.filter { it.group == group }.forEach { content.addView(row(it)) }
        }
        content.addView(text(getString(R.string.text_settings_about), 16f).apply { setPadding(0, dp(20), 0, dp(4)) })
        content.addView(releaseHistoryRow(), MATCH_PARENT, WRAP_CONTENT)
        val scroll = ScrollView(this).apply { addView(content, MATCH_PARENT, WRAP_CONTENT) }
        scroll.setOnApplyWindowInsetsListener { view, insets ->
            @Suppress("DEPRECATION")
            view.setPadding(insets.systemWindowInsetLeft, insets.systemWindowInsetTop, insets.systemWindowInsetRight, insets.systemWindowInsetBottom)
            insets
        }
        setContentView(scroll)
    }

    private fun row(library: Library): View = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(0, dp(8), 0, dp(8))
        addView(Switch(this@SettingsActivity).apply {
            text = library.id
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            isChecked = LibraryCatalog.isEnabled(context, library.id)
            setOnCheckedChangeListener { _, checked -> LibraryCatalog.setEnabled(context, library.id, checked) }
            tint(this)
        }, MATCH_PARENT, WRAP_CONTENT)
        val details = listOfNotNull(
            library.version?.let { getString(R.string.text_library_version, it) },
            getString(R.string.text_library_license, library.license),
            library.modules.takeIf { it.size > 1 }?.let { getString(R.string.text_library_modules, it.joinToString(", ")) },
        )
        addView(text(details.joinToString("; "), 13f, secondary = true))
    }

    /** "About > Release history" (AUTOJS6_PLUGIN_RELEASE_HISTORY_AGENTS.md section 2). zh-CN: "关于 > 发行历史" (规范第 2 节). */
    private fun releaseHistoryRow(): View = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        tag = R.string.text_release_history
        setPadding(0, dp(8), 0, dp(8))
        isClickable = true
        isFocusable = true
        setBackgroundResource(TypedValue().also { theme.resolveAttribute(android.R.attr.selectableItemBackground, it, true) }.resourceId)
        addView(text(getString(R.string.text_release_history), 16f))
        addView(text(getString(R.string.text_release_history_summary), 13f, secondary = true))
        setOnClickListener { openReleaseHistory() }
    }

    private fun text(value: String, sp: Float, secondary: Boolean = false) = TextView(this).apply {
        text = value
        setTextSize(TypedValue.COMPLEX_UNIT_SP, sp)
        if (secondary) alpha = 0.7f
    }

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()

}
