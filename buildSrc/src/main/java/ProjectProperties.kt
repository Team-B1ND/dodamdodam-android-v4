import org.gradle.api.JavaVersion

object ProjectProperties {
    const val APPLICATION_ID = "kr.hs.dgsw.smartschool.dodamdodam"
    const val COMPILE_SDK_VERSION = 32
    const val MIN_SDK_VERSION = 26
    const val TARGET_SDK_VERSION = 32
    const val VERSION_CODE = 52
    const val VERSION_NAME = "2.0.8"
    const val JVM_TARGET = "1.8"
    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val BUILD_TYPE_NAME = "release"
    const val IS_MINIFY_ENABLED = false
    const val PROGUARD_FILE_NAME = "proguard-android.txt"
    const val PROGUARD_FILE = "proguard-rules.pro"

    const val DATA_BINDING = true

    const val PATH_DATA = ":data"
    const val PATH_DOMAIN = ":domain"
}