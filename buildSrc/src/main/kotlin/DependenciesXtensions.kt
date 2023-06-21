package com.brq.hellocompose

import Deps
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.coreDependencies() {
    "implementation"(Deps.androidX)
    "implementation"(platform(Deps.kotlinBom))
    "implementation"(Deps.lifecycle)
}

fun DependencyHandlerScope.composeDependencies() {
    "implementation"(Deps.activityCompose)
    "implementation"(Deps.composeAnimatioNavigation)
    "implementation"(platform(Deps.composeBom))
    "implementation"(Deps.composeUi)
    "implementation"(Deps.composeUiGrafics)
    "implementation"(Deps.composePreview)
    "implementation"(Deps.composeNavigation)
}

fun DependencyHandlerScope.coilDependencies() {
    "implementation"(Deps.coilCompose)
}

fun DependencyHandlerScope.koinDependencies() {
    "implementation"(Deps.koinCompose)
}

fun DependencyHandlerScope.interceptorDependencies() {
    "implementation"(Deps.interceptor)
}

fun DependencyHandlerScope.jacocoDependencies() {
    "implementation"(Deps.jacoco)
}

fun DependencyHandlerScope.materialDependencies() {
    "implementation"(Deps.material3)
}

fun DependencyHandlerScope.retrofitDependencies() {
    "implementation"(Deps.retrofit)
    "implementation"(Deps.retrofitGson)
}

fun DependencyHandlerScope.roomDependencies() {
    "implementation"(Deps.roomRuntime)
    "annotationProcessor"(Deps.roomCompiler)
    "kapt"(Deps.roomCompiler)
}

fun DependencyHandlerScope.splashScreenDependencies() {
    "implementation"(Deps.splashScreen)
}

fun DependencyHandlerScope.androidTestsDependencies() {
    "androidTestImplementation"(Deps.espresso)
    "debugImplementation"(Deps.composeUiTooling)
    "debugImplementation"(Deps.composeUiTestManifest)
    "androidTestImplementation"(platform(Deps.composeBom))
    "androidTestImplementation"(Deps.composeUiTestJunit)
    "testImplementation"(Deps.roomTests)
    "androidTestImplementation"(Deps.junit5)
    "androidTestImplementation"(Deps.compoaseNavTest)
}

fun DependencyHandlerScope.testsDependencies() {
    "testImplementation"(Deps.junitTest)
    "testImplementation"(Deps.mockK)
    "testImplementation"(Deps.testCoroutines)
    "testImplementation"(Deps.turbineFlowTests)
    "testImplementation"(Deps.mockitoCore)
    "testImplementation"(Deps.mockitoKotlin)
}

