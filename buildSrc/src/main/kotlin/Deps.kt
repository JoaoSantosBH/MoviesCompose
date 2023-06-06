object Deps {

    const val androidPlugin = "android"
    const val androidAplicationPlugin = "com.android.application"
    const val androidLib = "com.android.library"
    const val composePlugin = "org.jetbrains.compose"

    const val androidX = "androidx.core:core-ktx:${Versions.androidXVersion}"
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlinBomVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"

    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBomVersion}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGrafics = "androidx.compose.ui:ui-graphics"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeAnimatioNavigation = "com.google.accompanist:accompanist-navigation-animation:${Versions.composeAnimationNavVersion}"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"

    const val material3 = "androidx.compose.material3:material3"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilComposeVersion}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinComposeVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    const val interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptorVersion}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomTests = "androidx.room:room-testing:${Versions.roomVersion}"

    const val junitTest = "junit:junit:${Versions.junitTestVersion}"

    const val junit5 = "androidx.test.ext:junit:${Versions.junit5Version}"

    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

    const val composeUiTestJunit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"


    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"

    const val androidBuildTools = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val androidTestRunnerImplementationClass = "androidx.test.runner.AndroidJUnitRunner"

    const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacocoVersion}"


}