plugins {
    id(Deps.androidAplicationPlugin)
    kotlin(Deps.androidPlugin)
    id("kotlin-kapt")
    id("jacoco")
    id("plugins.jacoco-report")
}

android {
    namespace = "com.brq.hellocompose"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.brq.hellocompose"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = Deps.androidTestRunnerImplementationClass

    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {

        val key = providers.gradleProperty("API_KEY").get()
        val token = providers.gradleProperty("API_TOKEN").get()

        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String", "API_KEY", key )
            buildConfigField("String", "API_TOKEN", token)
        }

        getByName("debug") {
            isMinifyEnabled = false
            enableAndroidTestCoverage
            enableUnitTestCoverage
            buildConfigField("String", "API_KEY", key )
            buildConfigField("String", "API_TOKEN", token)
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}



dependencies {
    implementation(Deps.androidX)
    implementation(platform(Deps.kotlinBom))
    implementation(Deps.lifecycle)
    implementation(Deps.activityCompose)
    implementation(platform(Deps.composeBom))
    implementation(Deps.composeUi)
    implementation(Deps.composeUiGrafics)
    implementation(Deps.composePreview)
    implementation(Deps.composeAnimatioNavigation)
    implementation(Deps.splashScreen)
    implementation(Deps.roomRuntime)
    annotationProcessor(Deps.roomCompiler)
    kapt(Deps.roomCompiler)
    implementation(Deps.material3)
    implementation(Deps.coilCompose)
    implementation(Deps.koinCompose)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)
    implementation(Deps.interceptor)
    implementation(Deps.jacoco)
    testImplementation(Deps.junitTest)
    androidTestImplementation(Deps.junit5)
    androidTestImplementation(Deps.espresso)
    androidTestImplementation(platform(Deps.composeBom))
    androidTestImplementation(Deps.composeUiTestJunit)
    testImplementation(Deps.roomTests)
    debugImplementation(Deps.composeUiTooling)
    debugImplementation(Deps.composeUiTestManifest)

}
