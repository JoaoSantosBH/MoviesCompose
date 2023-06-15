import Deps.DEBUG
import Deps.M_KEY
import Deps.M_TOKEN
import Deps.RELEASE
import Deps.TYPE_ARG
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

    namespace = Deps.packageIdName
    compileSdk = 33
    defaultConfig {
        applicationId = Deps.packageIdName
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = Deps.androidTestRunnerImplementationClass
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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

        val key = providers.gradleProperty(M_KEY).get()
        val token = providers.gradleProperty(M_TOKEN).get()

        getByName(RELEASE) {
            isMinifyEnabled = false
            buildConfigField(TYPE_ARG, M_KEY, key)
            buildConfigField(TYPE_ARG, M_TOKEN, token)
        }

        getByName(DEBUG) {
            isMinifyEnabled = false
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
            buildConfigField(TYPE_ARG, M_KEY, key)
            buildConfigField(TYPE_ARG, M_TOKEN, token)
        }
    }

    testCoverage {
        jacocoVersion = Versions.jacocoVersion
        testOptions.unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
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
