buildscript {
    dependencies {
//        classpath(Android.ANDROID_BUILD_TOOL)
//        classpath(Kotlin.KOTLIN_GRADLE_PLUGIN)
        classpath(Google.HILT_ANDROID_PLUGIN)
        classpath(AndroidX.NAVIGATION_SAFE_ARGS)
        classpath(Google.GOOGLE_GMS)
        classpath("com.android.tools.build:gradle:7.3.0")
    }
}

plugins {
    id("com.android.application").version("7.2.1").apply(false)
    id ("com.android.library").version("7.2.1").apply(false)
    id ("org.jetbrains.kotlin.android").version("1.7.0").apply(false)
    id ("org.jetbrains.kotlin.jvm").version("1.7.0").apply(false)
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}