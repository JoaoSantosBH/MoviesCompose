import com.brq.hellocompose.androidTestsDependencies
import com.brq.hellocompose.coilDependencies
import com.brq.hellocompose.composeDependencies
import com.brq.hellocompose.coreDependencies
import com.brq.hellocompose.interceptorDependencies
import com.brq.hellocompose.jacocoDependencies
import com.brq.hellocompose.koinDependencies
import com.brq.hellocompose.materialDependencies
import com.brq.hellocompose.retrofitDependencies
import com.brq.hellocompose.roomDependencies
import com.brq.hellocompose.splashScreenDependencies
import com.brq.hellocompose.testsDependencies

plugins {
    id(Deps.androidAplicationPlugin)
    kotlin(Deps.androidPlugin)
    id(Deps.kotlinKap)
    id(Deps.jacocoPlugin)
    id(Deps.jacocoXtension)
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
    packaging {
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
            enableAndroidTestCoverage = true
            enableUnitTestCoverage  = true
            buildConfigField("String", "API_KEY", key )
            buildConfigField("String", "API_TOKEN", token)
        }
    }

    testCoverage {
        testOptions {
            unitTests.isIncludeAndroidResources = true
            unitTests.isReturnDefaultValues = true
        }
    }
}

dependencies {

    coilDependencies()
    composeDependencies()
    coreDependencies()
    interceptorDependencies()
    koinDependencies()
    materialDependencies()
    retrofitDependencies()
    roomDependencies()
    splashScreenDependencies()

    // T E S T S
    //Instrumented
    androidTestsDependencies()
    //Coverage
    jacocoDependencies()
    //Unit
    testsDependencies()
}
