plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.daggerPlugin)
    id(Plugins.kotlinParcelize)
    id(Plugins.navigation_safe_args)
    id(Plugins.kt_lint) version Versions.KT_LINT
    id(Plugins.googleService)
    id(Plugins.jetbrainsKotlinAndroid)
}

android {

    compileSdk = ProjectProperties.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = ProjectProperties.APPLICATION_ID
        minSdk = ProjectProperties.MIN_SDK_VERSION
        targetSdk = ProjectProperties.TARGET_SDK_VERSION
        versionCode = ProjectProperties.VERSION_CODE
        versionName = ProjectProperties.VERSION_NAME

        testInstrumentationRunner = Plugins.testRunner
    }

    buildTypes {
        getByName(ProjectProperties.BUILD_TYPE_NAME) {
            isMinifyEnabled = ProjectProperties.IS_MINIFY_ENABLED
            proguardFiles(getDefaultProguardFile(ProjectProperties.PROGUARD_FILE_NAME), ProjectProperties.PROGUARD_FILE)
        }
    }
    compileOptions {
        sourceCompatibility = ProjectProperties.JAVA_VERSION
        targetCompatibility = ProjectProperties.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = ProjectProperties.JVM_TARGET
    }

    buildFeatures {
        dataBinding = ProjectProperties.DATA_BINDING
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(Google.MATERIAL)
    implementation(AndroidX.VIEW_MODEL_KTX)
    implementation(AndroidX.FRAGMENT_KTX)

    testImplementation(UnitTest.JUNIT)
    androidTestImplementation(AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    // splash screen
    implementation(AndroidX.SPLASH_SCREEN)

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

    // MP Android Chart
    implementation(Libraries.MP_ANDROID_CHART)

    // Swipe refresh layout
    implementation(AndroidX.SWIPE_REFRESH_LAYOUT)

    // jsoup
    implementation(Libraries.JSOUP)

    // lottie
    implementation(Libraries.LOTTIE)

    // map
    implementation(NaverMap.NAVER_MAP)

    implementation(Firebase.ANALYTICS)

    // exo player
    implementation(Google.EXO_PLAYER_CORE)
    implementation(Google.EXO_PLAYER_UI)
    implementation(Google.EXO_PLAYER_DASH)

    // in app update
    implementation(Google.APP_UPDATE)
    implementation(Google.APP_UPDATE_KTX)

    implementation(Libraries.INDICATOR)

    implementation(project(ProjectProperties.PATH_DATA))
    implementation(project(ProjectProperties.PATH_DOMAIN))
}
