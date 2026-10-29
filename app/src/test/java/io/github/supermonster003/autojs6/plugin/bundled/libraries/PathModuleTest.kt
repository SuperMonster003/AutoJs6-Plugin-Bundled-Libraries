package io.github.supermonster003.autojs6.plugin.bundled.libraries

import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mozilla.javascript.ScriptableObject

/**
 * `path.js`, which AutoJs6 loads for `require('path')` / `require('node:path')`, against `path.posix` of Node.js 24.15
 * (moved from the host's PathShimTest, host roadmap appendix C Q131 / Q182): the expected values are Node's results
 * rendered as [RhinoModules.render] does. The host keeps the same expressions as device cases, which run them through
 * `require('path')` of AutoJs6 with this plugin installed.
 *
 * zh-CN: `path.js` (AutoJs6 的 `require('path')` / `require('node:path')` 加载它) 对照 Node.js 24.15 的 `path.posix` (由宿主的 PathShimTest 移来,
 * 宿主 roadmap 附录 C Q131 / Q182): 期望值是 Node 的结果按 [RhinoModules.render] 的规则渲染. 宿主保留同一组表达式作为设备用例,
 * 在装有本插件的设备上经 AutoJs6 的 `require('path')` 运行.
 */
class PathModuleTest {

    private val modules = RhinoModules()

    @After
    fun tearDown() = modules.close()

    @Test
    fun followsNodePathPosix() {
        val scope = modules.newScope()
        ScriptableObject.putProperty(scope.globalThis, "path", modules.eval(scope, "require('path')"))
        val failures = CASES.mapNotNull { (expression, expected) ->
            val actual = modules.render(scope, expression)
            if (actual == expected) null else "$expression\n    expected $expected\n    actual   $actual"
        }
        assertTrue("Unexpected results:\n" + failures.joinToString("\n"), failures.isEmpty())
    }

    companion object {

        // Expressions with `path` the module and their rendering on Node 24.15 (a backslash is shown as "|").
        private val CASES = listOf(
            """path.join('a', 'b', 'c')""" to """"a/b/c"""",
            """path.join('/a', 'b', '../c')""" to """"/a/c"""",
            """path.join('a', '', 'b')""" to """"a/b"""",
            """path.join()""" to """"."""",
            """path.join('')""" to """"."""",
            """path.join('a/', '/b/')""" to """"a/b/"""",
            """path.join('./a', './b')""" to """"a/b"""",
            """path.join('..', 'a')""" to """"../a"""",
            """path.join('/', '..')""" to """"/"""",
            """path.join('a', 'b/')""" to """"a/b/"""",
            """path.join('a//b', 'c')""" to """"a/b/c"""",
            """path.join('foo', 'bar', 'baz/asdf', 'quux', '..')""" to """"foo/bar/baz/asdf"""",
            """path.join('a', 1)""" to """throws TypeError""",
            """path.join('a' + String.fromCharCode(92) + 'b', 'c').split(String.fromCharCode(92)).join('|')""" to """"a|b/c"""",
            """path.normalize('/foo/bar//baz/asdf/quux/..')""" to """"/foo/bar/baz/asdf"""",
            """path.normalize('')""" to """"."""",
            """path.normalize('./')""" to """"./"""",
            """path.normalize('/')""" to """"/"""",
            """path.normalize('a/../..')""" to """".."""",
            """path.normalize('//a')""" to """"/a"""",
            """path.normalize('a/./b/../c/')""" to """"a/c/"""",
            """path.normalize('/a' + String.fromCharCode(92) + '..' + String.fromCharCode(92) + 'b').split(String.fromCharCode(92)).join('|')""" to """"/a|..|b"""",
            """path.resolve('/foo/bar', './baz')""" to """"/foo/bar/baz"""",
            """path.resolve('/foo/bar', '/tmp/file/')""" to """"/tmp/file"""",
            """path.resolve('/a', 'b', '..', 'c')""" to """"/a/c"""",
            """path.resolve('/')""" to """"/"""",
            """path.resolve('/a/b', '', 'c')""" to """"/a/b/c"""",
            """path.dirname('/a/b/c')""" to """"/a/b"""",
            """path.dirname('/a/b/')""" to """"/a"""",
            """path.dirname('a')""" to """"."""",
            """path.dirname('/a')""" to """"/"""",
            """path.dirname('/')""" to """"/"""",
            """path.dirname('a/b//')""" to """"a"""",
            """path.dirname('a/../b')""" to """"a/.."""",
            """path.dirname('')""" to """"."""",
            """path.dirname('a//b')""" to """"a/"""",
            """path.dirname('//a')""" to """"//"""",
            """path.dirname('a/')""" to """"."""",
            """path.dirname(1)""" to """throws TypeError""",
            """path.basename('/a/b/c.txt')""" to """"c.txt"""",
            """path.basename('/a/b/')""" to """"b"""",
            """path.basename('c.txt', '.txt')""" to """"c"""",
            """path.basename('/')""" to """""""",
            """path.basename('a/../b')""" to """"b"""",
            """path.basename('.txt', '.txt')""" to """""""",
            """path.basename('')""" to """""""",
            """path.basename('/a/b.txt/', '.txt')""" to """"b"""",
            """path.basename('aaa', 'aaa')""" to """""""",
            """path.basename('/a/b', 'b')""" to """"b"""",
            """path.basename('a.txt', 'txt')""" to """"a."""",
            """path.basename('a', 1)""" to """throws TypeError""",
            """path.basename('a' + String.fromCharCode(92) + 'b').split(String.fromCharCode(92)).join('|')""" to """"a|b"""",
            """path.extname('index.html')""" to """".html"""",
            """path.extname('index.coffee.md')""" to """".md"""",
            """path.extname('index.')""" to """"."""",
            """path.extname('index')""" to """""""",
            """path.extname('.index')""" to """""""",
            """path.extname('a/b.c/')""" to """".c"""",
            """path.extname('..')""" to """""""",
            """path.extname('...')""" to """"."""",
            """path.extname('a.b.')""" to """"."""",
            """path.extname('.a.b')""" to """".b"""",
            """path.isAbsolute('/a')""" to """true""",
            """path.isAbsolute('a')""" to """false""",
            """path.isAbsolute('')""" to """false""",
            """path.relative('/data/orandea/test/aaa', '/data/orandea/impl/bbb')""" to """"../../impl/bbb"""",
            """path.relative('/a/b', '/a/b')""" to """""""",
            """path.relative('/a/b', '/a/b/c/d')""" to """"c/d"""",
            """path.parse('/home/user/dir/file.txt')""" to """{"root":"/","dir":"/home/user/dir","base":"file.txt","ext":".txt","name":"file"}""",
            """path.parse('file.txt')""" to """{"root":"","dir":"","base":"file.txt","ext":".txt","name":"file"}""",
            """path.parse('/')""" to """{"root":"/","dir":"/","base":"","ext":"","name":""}""",
            """path.parse('a/b/')""" to """{"root":"","dir":"a","base":"b","ext":"","name":"b"}""",
            """path.parse('')""" to """{"root":"","dir":"","base":"","ext":"","name":""}""",
            """path.parse('//a//b.c//')""" to """{"root":"/","dir":"//a/","base":"b.c","ext":".c","name":"b"}""",
            """path.parse('.bashrc')""" to """{"root":"","dir":"","base":".bashrc","ext":"","name":".bashrc"}""",
            """path.parse('/a/.x.y')""" to """{"root":"/","dir":"/a","base":".x.y","ext":".y","name":".x"}""",
            """path.parse('./a')""" to """{"root":"","dir":".","base":"a","ext":"","name":"a"}""",
            """path.format({"root":"/","dir":"/home/user/dir","base":"file.txt"})""" to """"/home/user/dir/file.txt"""",
            """path.format({"root":"/","base":"file.txt"})""" to """"/file.txt"""",
            """path.format({"name":"file","ext":".txt"})""" to """"file.txt"""",
            """path.format({"dir":"a","name":"b","ext":"c"})""" to """"a/b.c"""",
            """path.format({"root":"/","dir":"/","base":"x"})""" to """"/x"""",
            """path.format({"root":"/r/","name":"x"})""" to """"/r/x"""",
            """path.format({"dir":"a/","base":"b"})""" to """"a//b"""",
            """path.format({})""" to """""""",
            """path.format(null)""" to """throws TypeError""",
            """path.sep""" to """"/"""",
            """path.delimiter""" to """":"""",
            """path.posix === path""" to """true""",
        )

    }

}
