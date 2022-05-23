plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerPlugin)
}

android {

    compileSdk = Versions.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = "kr.hs.dgsw.smartschool.dodamdodam"
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Versions.JVM_TARGET
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(Google.MATERIAL)
    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    // navigation
    implementation(AndroidX.NAVIGATION)
    implementation(AndroidX.NAVIGATION_UI_KTX)

    // coroutine
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)

    // retrofit
    implementation(Libraries.RETROFIT)
    implementation(Libraries.RETROFIT_CONVERTER_GSON)
    implementation(Libraries.OKHTTP)
    implementation(Libraries.OKHTTP_LOGGING_INTERCEPTOR)

    // hilt
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)

    // glide
    implementation(Libraries.GLIDE)
    kapt(Libraries.GLIDE_COMPILER)

    // room
    implementation(AndroidX.ROOM_RUNTIME)
    kapt(AndroidX.ROOM_COMPILER)
    implementation(AndroidX.ROOM_KTX)

    // circular image view
    implementation(Libraries.CIRCULAR_IMAGE_VIEW)

    implementation(project(":data"))
    implementation(project(":domain"))
}