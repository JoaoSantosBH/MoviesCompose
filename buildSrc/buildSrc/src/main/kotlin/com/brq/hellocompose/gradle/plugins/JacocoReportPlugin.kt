package com.brq.hellocompose.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.testing.jacoco.tasks.JacocoReportsContainer


class JacocoReportPlugin : Plugin<Project> {

    override fun apply(project: Project) =
        with(project) {

            tasks.withType<Test> {
                configure<JacocoTaskExtension> {
                    isIncludeNoLocationClasses = true
                }
            }

            val fileFilter = mutableSetOf(
                "**/R.class",
                "**/R\$*.class",
                "**/BuildConfig.*",
                "**/Manifest*.*",
                "**/*Test*.*",
                "android/**/*.*",
                "**/*\$Lambda$*.*", // Jacoco can not handle several "$" in class name.
                "**/*\$inlined$*.*" // Kotlin specific, Jacoco can not handle several "$" in class name.
            )

            val classDirectoriesTree = fileTree(project.buildDir) {
                include(
                    "**/classes/**/main/**",
                    "**/intermediates/classes/debug/**",
                    "**/intermediates/javac/debug/*/classes/**", // Android Gradle Plugin 3.2.x support.
                    "**/tmp/kotlin-classes/debug/**"
                )

                exclude(fileFilter)
            }

            val sourceDirectoriesTree = fileTree("${project.buildDir}") {
                include(
                    "src/main/java/**",
                    "src/main/kotlin/**",
                    "src/debug/java/**",
                    "src/debug/kotlin/**"
                )
            }

            val executionDataTree = fileTree(project.buildDir) {
                include(
                    "outputs/code_coverage/**/*.ec",
                    "jacoco/jacocoTestReportDebug.exec",
                    "jacoco/testDebugUnitTest.exec",
                    "jacoco/test.exec"
                )
            }

            fun JacocoReportsContainer.reports() {
                xml.required.set(false)
                html.required.set(true)
                html.outputLocation.set(file("${buildDir}/reports/jacoco/jacocoTestReport/html"))
            }

            fun JacocoCoverageVerification.setDirectories() {
                sourceDirectories.setFrom(sourceDirectoriesTree)
                classDirectories.setFrom(classDirectoriesTree)
                executionData.setFrom(executionDataTree)
            }

            fun JacocoReport.setDirectories() {
                sourceDirectories.setFrom(sourceDirectoriesTree)
                classDirectories.setFrom(classDirectoriesTree)
                executionData.setFrom(executionDataTree)
            }


            if (tasks.findByName("jacocoAndroidTestReport") == null) {

                tasks.register<JacocoReport>("jacocoAndroidTestReport") {
                    group = "versioning"
                    description = "Code coverage report for both Android and Unit tests."
                    dependsOn("connectedAndroidTest", "createDebugCoverageReport")
                    reports {
                        reports()
                    }
                    setDirectories()
                }
            }

            if (tasks.findByName("jacocoAndroidCoverageVerification") == null) {
                tasks.register<JacocoCoverageVerification>("jacocoAndroidCoverageVerification") {
                    group = "versioning"
                    description =
                        "Code coverage verification for Android both Android and Unit tests."
                    dependsOn("connectedAndroidTest", "createDebugCoverageReport")
                    violationRules {
                        rule {
                            limit {
                                counter = "INSTRUCTION"
                                value = "COVEREDRATIO"
                                minimum = "0.3".toBigDecimal()
                            }
                        }
                    }
                    setDirectories()
                }
            }

        }
}