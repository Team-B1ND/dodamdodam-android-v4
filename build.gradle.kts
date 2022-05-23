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
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}