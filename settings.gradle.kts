pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven( "https://naver.jfrog.io/artifactory/maven/")
    }
}

rootProject.name = "DodamDodam"
include(":presentation")
include(":data")
include(":domain")
