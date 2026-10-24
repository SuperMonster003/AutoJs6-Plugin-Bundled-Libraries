package io.github.supermonster003.autojs6.plugin.bundled.libraries

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.widget.Switch

/**
 * A settings page in AutoJs6's appearance ([HostAppearance]): the language and night mode through the base context, the
 * theme colors on the action bar, the status bar and the switches. Asks the host again on every resume (back from
 * AutoJs6 after a change of its settings) and recreates the page when the answer differs and the page was not touched.
 * zh-CN: 使用 AutoJs6 外观 ([HostAppearance]) 的设置页: 语言与夜间模式经 base context 生效, 主题色用于操作栏, 状态栏与开关.
 * 每次恢复时 (例如在 AutoJs6 改设置后返回) 重新询问宿主, 应答不同且页面未被操作时重建页面.
 */
abstract class HostAppearanceActivity : Activity() {

    internal lateinit var appearance: HostAppearance
        private set
    private var generation = 0
    private var interacted = false

    override fun attachBaseContext(newBase: Context) {
        appearance = HostAppearance.load(newBase)
        super.attachBaseContext(appearance.wrap(newBase))
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appearance.primary?.let { actionBar?.setBackgroundDrawable(ColorDrawable(it)) }
        appearance.primaryDark?.let { color ->
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
            val flags = window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            val light = AppearanceColors.foregroundOn(color) == AppearanceColors.BLACK
            window.decorView.systemUiVisibility = if (light) flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else flags
        }
    }

    override fun onTitleChanged(title: CharSequence?, color: Int) {
        super.onTitleChanged(title, color)
        val background = appearance.primary ?: return
        val foreground = AppearanceColors.foregroundOn(background)
        actionBar?.title = SpannableString(title ?: "").apply {
            setSpan(ForegroundColorSpan(foreground), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    /** Shows the up arrow in the foreground color of the host's action bar. zh-CN: 以宿主操作栏的前景色显示向上箭头. */
    protected fun showUpArrow() {
        val bar = actionBar ?: return
        bar.setDisplayHomeAsUpEnabled(true)
        val background = appearance.primary ?: return
        val attributes = theme.obtainStyledAttributes(intArrayOf(android.R.attr.homeAsUpIndicator))
        val arrow = try {
            attributes.getDrawable(0)
        } finally {
            attributes.recycle()
        }
        arrow?.mutate()?.setTint(AppearanceColors.foregroundOn(background))
        bar.setHomeAsUpIndicator(arrow)
    }

    /** Colors [switch] with the host's accent, made readable on the page. zh-CN: 用宿主的强调色 (调整为在页面上可读) 为 [switch] 着色. */
    protected fun tint(switch: Switch) {
        val accent = appearance.accent ?: return
        val inactive = themeColor(android.R.attr.textColorSecondary, AppearanceColors.BLACK)
        val active = AppearanceColors.readable(accent, themeColor(android.R.attr.colorBackground, AppearanceColors.WHITE))
        val states = arrayOf(intArrayOf(-android.R.attr.state_enabled), intArrayOf(android.R.attr.state_checked), intArrayOf())
        switch.thumbTintList = ColorStateList(states, intArrayOf(AppearanceColors.withAlpha(inactive, 96), active, inactive))
        switch.trackTintList = ColorStateList(
            states,
            intArrayOf(AppearanceColors.withAlpha(inactive, 32), AppearanceColors.withAlpha(active, 128), AppearanceColors.withAlpha(inactive, 64)),
        )
    }

    private fun themeColor(attribute: Int, fallback: Int): Int {
        val attributes = theme.obtainStyledAttributes(intArrayOf(attribute))
        return try {
            attributes.getColor(0, fallback)
        } finally {
            attributes.recycle()
        }
    }

    override fun onResume() {
        super.onResume()
        interacted = false
        val current = ++generation
        HostAppearance.refresh(this) { host ->
            runOnUiThread {
                if (current != generation || isFinishing || isDestroyed) return@runOnUiThread
                if (!interacted && host != appearance) recreate()
            }
        }
    }

    override fun onUserInteraction() {
        interacted = true
        super.onUserInteraction()
    }

    override fun onPause() {
        generation++
        super.onPause()
    }

}
