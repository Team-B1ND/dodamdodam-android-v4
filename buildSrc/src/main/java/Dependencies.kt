object Kotlin {
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
}

object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_ARCH = "android.arch.persistence.room:compiler:${Versions.ROOM}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
}

object Google {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
}

object Libraries {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    const val VIEW_PAGER2 = "androidx.viewpager2:viewpager2:${Versions.VIEW_PAGER}"
    const val CIRCLE_INDICATOR = "me.relex:circleindicator:${Versions.CIRCLE_INDICATOR}"

    const val CIRCULAR_IMAGE_VIEW = "de.hdodenhof:circleimageview:${Versions.CIRCULAR_IMAGE_VIEW}"
}

object UnitTest {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object Android {
    const val ANDROID_BUILD_TOOL = "com.android.tools.build:gradle:${Versions.ANDROID_BUILD_TOOL}"
}

