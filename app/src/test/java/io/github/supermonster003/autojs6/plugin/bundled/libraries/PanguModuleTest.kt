package io.github.supermonster003.autojs6.plugin.bundled.libraries

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.TopLevel

/**
 * `pangu.js` / `pangu.min.js`, the text API of pangu.js 10.1.0 that AutoJs6's `tools/pangu` builds for Rhino (moved from
 * the host's PanguModuleTest, host roadmap appendix C Q182).
 *
 * zh-CN: `pangu.js` / `pangu.min.js`, AutoJs6 的 `tools/pangu` 为 Rhino 构建的 pangu.js 10.1.0 文本 API (由宿主的 PanguModuleTest 移来,
 * 宿主 roadmap 附录 C Q182).
 */
class PanguModuleTest {

    private val modules = RhinoModules()

    @After
    fun tearDown() = modules.close()

    @Test
    fun theTextApiLoadsWithoutBrowserOrNodeGlobals() {
        val scope = modules.newScope()
        assertEquals("10.1.0", eval(scope, "require('pangu').version"))
        assertEquals("object", eval(scope, "typeof require('pangu')"))
        assertEquals("undefined", eval(scope, "typeof document"))
        assertEquals("undefined", eval(scope, "typeof process"))
        assertEquals("undefined", eval(scope, "typeof require('pangu').spacePage"))
        assertEquals("undefined", eval(scope, "typeof require('pangu').spaceFile"))
        assertEquals("undefined", eval(scope, "typeof require('pangu').spacingText"))
    }

    @Test
    fun latestTextRulesWorkInRhino() {
        val scope = modules.newScope()
        val cases = listOf(
            "" to "",
            "中文" to "中文",
            "AutoJs6" to "AutoJs6",
            "使用AutoJs6处理文本" to "使用 AutoJs6 处理文本",
            "今天写了10行JavaScript代码" to "今天写了 10 行 JavaScript 代码",
            "中文 JavaScript" to "中文 JavaScript",
            "中文\nJavaScript" to "中文\nJavaScript",
            "中文\u00a0JavaScript" to "中文\u00a0JavaScript",
            "中文/英文" to "中文/英文",
            "使用C++开发" to "使用 C++ 开发",
            "使用Disney+服务" to "使用 Disney+ 服务",
            "公視+上架了新片" to "公視+ 上架了新片",
            "血型是AB-的人" to "血型是 AB- 的人",
            "HiNet光世代+MOD+影劇館+/全選" to "HiNet 光世代 + MOD + 影劇館+/全選",
            "访问https://example.com/中文路径" to "访问 https://example.com/中文路径",
            "中文<a>测试abc</a>结束" to "中文<a>测试 abc</a>结束",
            "版本( AutoJs6 )发布" to "版本 (AutoJs6) 发布",
        )
        for ((input, expected) in cases) {
            ScriptableObject.putProperty(scope.globalThis, "input", input)
            assertEquals(input, expected, eval(scope, "require('pangu').spaceText(input)"))
            assertEquals(input, input == expected, eval(scope, "require('pangu').hasProperSpacing(input)"))
            ScriptableObject.putProperty(scope.globalThis, "spaced", expected)
            assertEquals(input, expected, eval(scope, "require('pangu').spaceText(spaced)"))
        }
    }

    // pangu.js only re-exports pangu.min.js; each engine of AutoJs6 has a require of its own.
    // zh-CN: pangu.js 只是转出 pangu.min.js; AutoJs6 的每个引擎各有自己的 require.
    @Test
    fun moduleAliasesShareAnInstanceButSeparateEnginesDoNot() {
        val first = modules.newScope()
        assertTrue(eval(first, "require('pangu') === require('pangu.js')") as Boolean)
        assertTrue(eval(first, "require('pangu') === require('pangu.min.js')") as Boolean)
        val firstModule = eval(first, "require('pangu')") as Scriptable
        val secondModule = eval(modules.newScope(), "require('pangu')") as Scriptable
        assertNotSame(firstModule, secondModule)
    }

    @Test
    fun nonStringInputKeepsTheUpstreamWarningAndPassThroughBehavior() {
        val scope = modules.newScope()
        eval(scope, "var warnings = []; var console = { warn: function(message) { warnings.push(message); } };")
        assertTrue(eval(scope, "require('pangu').spaceText(null) === null") as Boolean)
        assertTrue(eval(scope, "require('pangu').spaceText(42) === 42") as Boolean)
        assertTrue(eval(scope, "require('pangu').spaceText() === undefined") as Boolean)
        assertEquals(3.0, Context.toNumber(eval(scope, "warnings.length")), 0.0)
    }

    private fun eval(scope: TopLevel, source: String): Any? = modules.eval(scope, source)

}
