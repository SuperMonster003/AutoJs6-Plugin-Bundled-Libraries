import groovy.json.JsonSlurper
import org.gradle.api.GradleException
import org.gradle.api.provider.Property
import java.io.File
import java.util.Properties

plugins {
    id("io.github.supermonster003.autojs6-native-alignment")
    id("org.autojs.build.utils")
    id("org.autojs.build.versions")
    id("org.autojs.build.signs")
    id("org.autojs.build.jvm-convention")
    id("com.android.application")
}

val globalApplicationId = "io.github.supermonster003.autojs6.plugin.bundled.libraries"

// The AutoJs6 build with plugin script modules (roadmap T11 / P4.3 of the host). The host commits of P4.3 keep the
// build number 5935 of their parent; set the build of the published host before the first release.
// zh-CN: 支持插件脚本模块的 AutoJs6 构建 (宿主 ROADMAP T11 / P4.3). 宿主 P4.3 的提交沿用父提交的构建号 5935; 首次发布前改为已发布宿主的构建号.
val requiredHostVersionCode = 5935L

val catalogFile = file("src/main/assets/script-modules.json")
val moduleRootDirectory = file("src/main/assets/modules")

/**
 * Checks the catalog against the files under `assets/modules`: every library has an id, a known group, at least one
 * module and existing files; every file belongs to exactly one library, and no module name is declared twice. The host
 * reads the same catalog (roadmap P4.3 (a) of the host), so a mismatch fails the build instead of a require at runtime.
 * zh-CN: 校验清单与 `assets/modules` 下的文件: 每个库有 id, 已知分组, 至少一个模块且文件存在; 每个文件恰好属于一个库, 模块名不重复.
 * 宿主读取同一份清单 (宿主 ROADMAP P4.3 (a)), 因此不一致时构建失败, 而不是运行时 require 失败.
 */
val verifyScriptModuleCatalog = tasks.register("verifyScriptModuleCatalog") {
    group = "verification"
    description = "Verifies assets/script-modules.json against assets/modules"
    inputs.file(catalogFile)
    inputs.dir(moduleRootDirectory)
    doLast {
        @Suppress("UNCHECKED_CAST")
        val catalog = JsonSlurper().parse(catalogFile) as Map<String, Any?>
        if (catalog["schemaVersion"] != 1) throw GradleException("script-modules.json: schemaVersion must be 1")
        if (catalog["moduleRoot"] != "modules") throw GradleException("script-modules.json: moduleRoot must be \"modules\"")
        @Suppress("UNCHECKED_CAST")
        val groups = (catalog["groups"] as List<Map<String, Any?>>).map { it["id"] as String }.toSet()
        @Suppress("UNCHECKED_CAST")
        val libraries = catalog["libraries"] as List<Map<String, Any?>>
        val owners = mutableMapOf<String, String>()
        val moduleNames = mutableSetOf<String>()
        val libraryIds = mutableSetOf<String>()
        val files = moduleRootDirectory.walkTopDown().filter(File::isFile)
            .map { it.relativeTo(moduleRootDirectory).invariantSeparatorsPath }.toSortedSet()
        libraries.forEach { library ->
            val id = library["id"] as? String ?: throw GradleException("script-modules.json: a library has no id")
            if (!libraryIds.add(id)) throw GradleException("script-modules.json: library $id is declared twice")
            if (library["group"] !in groups) throw GradleException("script-modules.json: library $id has an unknown group")
            @Suppress("UNCHECKED_CAST")
            val modules = library["modules"] as? Map<String, String> ?: emptyMap()
            if (modules.isEmpty()) throw GradleException("script-modules.json: library $id has no module")
            @Suppress("UNCHECKED_CAST")
            val owned = library["files"] as? List<String> ?: emptyList()
            modules.forEach { (name, path) ->
                if (!moduleNames.add(name)) throw GradleException("script-modules.json: module $name is declared twice")
                if (path !in files) throw GradleException("script-modules.json: module $name points to the missing file $path")
                if (owned.none { path == it || it.endsWith("/") && path.startsWith(it) }) {
                    throw GradleException("script-modules.json: module $name of $id is outside the files of $id")
                }
            }
            owned.forEach { entry ->
                val matched = files.filter { if (entry.endsWith("/")) it.startsWith(entry) else it == entry }
                if (matched.isEmpty()) throw GradleException("script-modules.json: $entry of $id matches no file")
                matched.forEach { path ->
                    owners.put(path, id)?.let { throw GradleException("script-modules.json: $path belongs to both $it and $id") }
                }
            }
        }
        (files - owners.keys).takeIf { it.isNotEmpty() }?.let { throw GradleException("script-modules.json: files without a library: $it") }
        logger.lifecycle("script-modules.json: ${libraries.size} libraries, ${moduleNames.size} modules, ${files.size} files")
    }
}

var isSignsValid = false

android {
    namespace = globalApplicationId
    compileSdk = versions.sdkVersionCompile

    defaultConfig {
        applicationId = globalApplicationId
        minSdk = versions.sdkVersionMin
        targetSdk = versions.sdkVersionTarget
        versionCode = versions.appVersionCode
        versionName = versions.appVersionName

        buildConfigField("String", "VERSION_DATE", "\"${utils.getDateString("MMM d, yyyy", "GMT+08:00")}\"")
        buildConfigField("long", "REQUIRED_HOST_VERSION_CODE", "${requiredHostVersionCode}L")

        manifestPlaceholders["requiredHostVersion"] = requiredHostVersionCode
    }

    signingConfigs {
        val props = Properties().also { properties ->
            File("${project.rootDir}/sign.properties").takeIf(File::exists)?.let { signFile ->
                signFile.inputStream().use(properties::load)
                isSignsValid = properties.isNotEmpty()
            }
        }
        if (isSignsValid) {
            create("release") {
                val configuredStoreFile = props.getProperty("storeFile")
                storeFile = configuredStoreFile?.let { configured ->
                    val path = File(configured)
                    when {
                        path.isAbsolute -> path
                        rootProject.file(configured).isFile -> rootProject.file(configured)
                        else -> project.file(configured)
                    }
                }
                keyPassword = props.getProperty("keyPassword")
                keyAlias = props.getProperty("keyAlias")
                storePassword = props.getProperty("storePassword")
            }
        }
    }

    buildTypes {
        val releaseSigningConfig = takeIf { isSignsValid }?.let { signingConfigs.getByName("release") }
        debug {
            isMinifyEnabled = false
            releaseSigningConfig?.let { signingConfig = it }
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            releaseSigningConfig?.let { signingConfig = it }
        }
    }

    buildFeatures { buildConfig = true }

    // The module files are read by AutoJs6 as they are; compressing them only costs the host a decompression.
    // zh-CN: 模块文件由 AutoJs6 原样读取; 压缩只会让宿主多一次解压.
    androidResources { noCompress += listOf("js", "json") }
}

androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { output ->
            val outputFileNameProperty = output.javaClass.methods.firstOrNull {
                it.name == "getOutputFileName" && it.parameterTypes.isEmpty()
            }?.invoke(output) as? Property<*>
            @Suppress("UNCHECKED_CAST")
            (outputFileNameProperty as? Property<String>)?.set(
                output.versionName.map { versionName ->
                    "${rootProject.name}-v$versionName-universal.apk".lowercase()
                },
            )
        }
    }
}

dependencies {
    implementation(files("$rootDir/libs/common-plugin-api.aar"))
}

tasks {
    withType(JavaCompile::class.java) { options.encoding = "UTF-8" }
    named("preBuild") { dependsOn(verifyScriptModuleCatalog) }
}

// The plugin ships JavaScript files only.
// zh-CN: 插件只包含 JavaScript 文件.
nativeAlignment { expectNoNativeLibraries.set(true) }
