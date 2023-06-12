object Deps {

    // C O R E
    const val androidBuildTools = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val androidTestRunnerImplementationClass = "androidx.test.runner.AndroidJUnitRunner"
    const val androidX = "androidx.core:core-ktx:${Versions.androidXVersion}"
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlinBomVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"

    // C O M P O S E
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
    const val composeAnimatioNavigation = "com.google.accompanist:accompanist-navigation-animation:${Versions.composeAnimationNavVersion}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBomVersion}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiGrafics = "androidx.compose.ui:ui-graphics"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val compoaseNavTest = "androidx.navigation:navigation-testing:${Versions.composeNavTestVersion}"
    const val composeNavigation =  "androidx.navigation:navigation-compose:${Versions.composeNavVersion}"

    // C O I L
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilComposeVersion}"

    // K O I N
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinComposeVersion}"

    // I N T E R C E P T O R
    const val interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptorVersion}"

    // J A C O C O
    const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacocoVersion}"

    // M A T E R I A L
    const val material3 = "androidx.compose.material3:material3"

    // P L U G I N S
    const val androidLib = "com.android.library"
    const val androidAplicationPlugin = "com.android.application"
    const val androidPlugin = "android"
    const val composePlugin = "org.jetbrains.compose"
    const val kotlinKap = "kotlin-kapt"
    const val jacocoPlugin = "jacoco"
    const val jacocoXtension = "plugins.jacoco-report"

    // R E T R O F I T
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    // R O O M
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // S P L A S H   S C R E E N
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"

    // T E S T S
    const val roomTests = "androidx.room:room-testing:${Versions.roomVersion}"
    const val junitTest = "junit:junit:${Versions.junitTestVersion}"
    const val junit5 = "androidx.test.ext:junit:${Versions.junit5Version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val composeUiTestJunit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val mockK = "io.mockk:mockk:${Versions.mockKVersion}"
    const val testCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestVersion}"
    const val turbineFlowTests =  "app.cash.turbine:turbine:${Versions.turbineVersion}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mokitoCoreVersion}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlinVersion}"

}