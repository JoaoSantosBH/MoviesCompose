repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}

gradlePlugin {
    plugins {

        register("jacocoPlugin") {
            id = "jacoco-report"
            implementationClass = "com.brq.hellocompose.gradle.plugins.JacocoReportPlugin.kt"
        }

    }
}

