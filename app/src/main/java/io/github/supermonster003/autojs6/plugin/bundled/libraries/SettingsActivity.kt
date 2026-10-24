package io.github.supermonster003.autojs6.plugin.bundled.libraries

import android.app.Activity
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
 * The launch page: the libraries in two sections (libraries and Node compatibility shims), one switch each. A switch
 * takes effect for the next `require` of a module that the running script has not loaded yet.
 * zh-CN: 启动页: 库分两节 (库与 Node 兼容垫片), 每个一个开关. 开关对运行中的脚本尚未加载的模块, 在下一次 `require` 时生效.
 */
class SettingsActivity : Activity() {

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
        }, MATCH_PARENT, WRAP_CONTENT)
        val details = listOfNotNull(
            library.version?.let { getString(R.string.text_library_version, it) },
            getString(R.string.text_library_license, library.license),
            library.modules.takeIf { it.size > 1 }?.let { getString(R.string.text_library_modules, it.joinToString(", ")) },
        )
        addView(text(details.joinToString("; "), 13f, secondary = true))
    }

    private fun text(value: String, sp: Float, secondary: Boolean = false) = TextView(this).apply {
        text = value
        setTextSize(TypedValue.COMPLEX_UNIT_SP, sp)
        if (secondary) alpha = 0.7f
    }

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()

}
