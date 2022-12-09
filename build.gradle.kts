buildscript {
    dependencies {
//        classpath(Android.ANDROID_BUILD_TOOL)
//        classpath(Kotlin.KOTLIN_GRADLE_PLUGIN)
        classpath(Google.HILT_ANDROID_PLUGIN)
        classpath(AndroidX.NAVIGATION_SAFE_ARGS)
        classpath(Google.GOOGLE_GMS)
    }
}

plugins {
    id(Plugins.androidApplication).version(Versions.ANDROID).apply(false)
    id (Plugins.androidLibrary).version(Versions.ANDROID).apply(false)
    id (Plugins.jetbrainsAndroid).version(Versions.JETBRAINS).apply(false)
    id (Plugins.jetbrainsJvm).version(Versions.JETBRAINS).apply(false)
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}