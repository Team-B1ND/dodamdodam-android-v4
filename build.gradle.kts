buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Android.ANDROID_BUILD_TOOL)
        classpath(Kotlin.KOTLIN_GRADLE_PLUGIN)
        classpath(Google.HILT_ANDROID_PLUGIN)
        classpath(AndroidX.NAVIGATION_SAFE_ARGS)
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven( "https://naver.jfrog.io/artifactory/maven/")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}