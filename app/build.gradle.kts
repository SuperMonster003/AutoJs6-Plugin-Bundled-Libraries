import groovy.json.JsonSlurper
import org.gradle.api.GradleException
import org.gradle.api.provider.Property
import java.io.File
import java.util.Properties
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

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
// build number 5935 of their parent; when AutoJs6 6.8.0 is final, set its build number here and in .readme/common.json
// before the first release (ROADMAP.md "Release steps"; .python/check_repository.py keeps the two equal).
// zh-CN: 支持插件脚本模块的 AutoJs6 构建 (宿主 ROADMAP T11 / P4.3). 宿主 P4.3 的提交沿用父提交的构建号 5935; AutoJs6 6.8.0
// 定版时, 首次发布前把这里与 .readme/common.json 改为其构建号 (ROADMAP.md "发布步骤"; .python/check_repository.py 保证两处一致).
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

        // Routing metadata read by the official plugin index generator.
        // zh-CN: 官方插件索引生成器读取的路由元数据.
        resValue("string", "plugin_id", "bundled-libraries")
        resValue("string", "plugin_engine", "script-modules")
        resValue("string", "plugin_variant", "default")
        resValue("string", "plugin_requires_host_version", "$requiredHostVersionCode")
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

    buildFeatures {
        buildConfig = true
        resValues = true
    }

    // The module files are read by AutoJs6 as they are; compressing them only costs the host a decompression.
    // The suffixes cover every file under assets/modules (verifySignedReleaseArtifacts checks it).
    // zh-CN: 模块文件由 AutoJs6 原样读取; 压缩只会让宿主多一次解压.
    // zh-CN: 后缀覆盖 assets/modules 下的全部文件 (由 verifySignedReleaseArtifacts 检查).
    androidResources { noCompress += listOf("js", "json", "md", "ts", "txt", "LICENSE") }
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
    testImplementation(libs.junit)

    // The module tests run on the Rhino build that AutoJs6 runs the modules on: a copy of the host's
    // libs/org-mozilla-rhino-2_0_0-SNAPSHOT.jar (Rhino-For-AutoJs6; no release of mozilla/rhino keeps a const in a loop
    // body per iteration yet, which path.js relies on). Copy it again when AutoJs6 updates it.
    // zh-CN: 模块测试运行在 AutoJs6 运行这些模块的 Rhino 构建上: 宿主 libs/org-mozilla-rhino-2_0_0-SNAPSHOT.jar 的副本
    // (Rhino-For-AutoJs6; mozilla/rhino 的发布版尚未让循环体中的 const 每轮重新绑定, 而 path.js 依赖它). AutoJs6 更新它时重新复制.
    testImplementation(files("$rootDir/libs/org-mozilla-rhino-2_0_0-SNAPSHOT.jar"))
}

tasks {
    withType(JavaCompile::class.java) { options.encoding = "UTF-8" }
    named("preBuild") { dependsOn(verifyScriptModuleCatalog) }

    // Collects the signed release APK into releases/ with its CRC32 appended (the official plugin convention).
    // zh-CN: 把签名的 release APK 收集到 releases/ 并在文件名后追加 CRC32 (官方插件惯例).
    register<Copy>("appendDigestToReleasedFiles") {
        group = "distribution"
        description = "Appends CRC32 digest to released APK files"
        dependsOn("assembleRelease")
        from(layout.buildDirectory.dir("outputs/apk/release")) {
            include("*.${utils.FILE_EXTENSION_APK}")
            eachFile { name = "${name.removeSuffix(".${utils.FILE_EXTENSION_APK}")}-${utils.digestCRC32(file)}.${utils.FILE_EXTENSION_APK}" }
        }
        into(rootProject.layout.projectDirectory.dir("releases"))
        includeEmptyDirs = false
        duplicatesStrategy = DuplicatesStrategy.FAIL
        doLast { println("Destination: ${rootProject.layout.projectDirectory.dir("releases").asFile}") }
    }
}

// Fail before collection when the signing identity or the APK is not what AutoJs6 expects: complete credentials, one
// universal APK that apksigner accepts, no native library, and the catalog with every module file stored uncompressed
// (AutoJs6 reads them from the APK as they are).
// zh-CN: 签名身份或 APK 不符合 AutoJs6 的预期时, 在收集之前失败: 凭据完整, 只有一个通过 apksigner 校验的通用 APK, 不含原生库,
// 清单与每个模块文件都以不压缩方式存储 (AutoJs6 直接从 APK 原样读取).
val verifySignedReleaseArtifacts = tasks.register("verifySignedReleaseArtifacts") {
    group = "verification"
    dependsOn("assembleRelease")
    doLast {
        val signing = android.buildTypes.getByName("release").signingConfig
        check(
            signing != null && signing.storeFile?.isFile == true &&
                !signing.storePassword.isNullOrBlank() && !signing.keyAlias.isNullOrBlank() &&
                !signing.keyPassword.isNullOrBlank(),
        ) { "Release signing configuration is missing or incomplete" }
        val directory = layout.buildDirectory.dir("outputs/apk/release").get().asFile
        val apks = directory.listFiles { file -> file.isFile && file.extension == utils.FILE_EXTENSION_APK }.orEmpty()
        check(apks.map { it.name }.toSet() == setOf("${rootProject.name}-v${versions.appVersionName}-universal.apk")) {
            "Unexpected release APK set: ${apks.map { it.name }.sorted()}"
        }
        val signerJar = androidComponents.sdkComponents.sdkDirectory.get().asFile
            .resolve("build-tools/${android.buildToolsVersion}/lib/apksigner.jar")
        check(signerJar.isFile) { "Android SDK apksigner is unavailable" }
        val result = providers.exec {
            commandLine("java", "-jar", signerJar.absolutePath, "verify", apks.single().absolutePath)
            isIgnoreExitValue = true
        }.result.get()
        check(result.exitValue == 0) { "Release APK signature verification failed" }
        val moduleFiles = moduleRootDirectory.walkTopDown().filter(File::isFile)
            .map { "assets/modules/" + it.relativeTo(moduleRootDirectory).invariantSeparatorsPath }.toSet()
        ZipFile(apks.single()).use { zip ->
            val entries = zip.entries().asSequence().associateBy(ZipEntry::getName)
            check(entries.keys.none { it.startsWith("lib/") }) { "The release APK must not contain native libraries" }
            (moduleFiles + "assets/script-modules.json").forEach { path ->
                val entry = entries[path] ?: throw GradleException("The release APK misses $path")
                check(entry.method == ZipEntry.STORED) { "$path must be stored uncompressed" }
            }
        }
    }
}
tasks.named("appendDigestToReleasedFiles") { dependsOn(verifySignedReleaseArtifacts) }

// The plugin ships JavaScript files only.
// zh-CN: 插件只包含 JavaScript 文件.
nativeAlignment { expectNoNativeLibraries.set(true) }
