package io.github.supermonster003.autojs6.plugin.bundled.libraries

import org.mozilla.javascript.Context
import org.mozilla.javascript.TopLevel
import org.mozilla.javascript.commonjs.module.RequireBuilder
import org.mozilla.javascript.commonjs.module.provider.StrongCachingModuleScriptProvider
import org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider
import java.io.File

/**
 * Runs the module files of `src/main/assets/modules` on AutoJs6's Rhino build (`libs/`) the way AutoJs6 loads them: ES6,
 * interpreted mode and a sandboxed CommonJS `require` whose ids are file names in that directory (`pangu`, `pangu.js`,
 * `pangu.min.js`).
 *
 * zh-CN: 按 AutoJs6 加载的方式在 AutoJs6 的 Rhino 构建 (`libs/`) 上运行 `src/main/assets/modules` 中的模块文件: ES6, 解释模式,
 * 沙箱化的 CommonJS `require`, 模块 id 是该目录下的文件名 (`pangu`, `pangu.js`, `pangu.min.js`).
 */
internal class RhinoModules : AutoCloseable {

    val context: Context = Context.enter().apply {
        languageVersion = Context.VERSION_ES6
        isInterpretedMode = true
    }

    private val provider = StrongCachingModuleScriptProvider(UrlModuleSourceProvider(listOf(MODULES.toURI()), null))

    /** A new top-level scope with its own `require`, as each script engine of AutoJs6 has. */
    fun newScope(): TopLevel = context.initStandardObjects().also { scope ->
        RequireBuilder().setModuleScriptProvider(provider).setSandboxed(true).createRequire(context, scope).install(scope)
    }

    fun eval(scope: TopLevel, source: String): Any? = context.evaluateString(scope, source, "test", 1, null)

    /**
     * The result of [expression] rendered as the host's module tests render it: JSON, `undefined`, `function`, or
     * `throws <ErrorName>` without the message.
     */
    fun render(scope: TopLevel, expression: String): String = Context.toString(
        eval(
            scope,
            """
            (function () {
                try {
                    var v = ($expression);
                    if (v === undefined) return "undefined";
                    if (typeof v === "function") return "function";
                    if (typeof v === "number" && !isFinite(v)) return String(v);
                    return JSON.stringify(v);
                } catch (e) {
                    return "throws " + ((e && e.name) || Object.prototype.toString.call(e));
                }
            })()
            """.trimIndent(),
        ),
    )

    override fun close() = Context.exit()

    companion object {
        val MODULES = listOf(File("src/main/assets/modules"), File("app/src/main/assets/modules")).first { it.isDirectory }
    }

}
