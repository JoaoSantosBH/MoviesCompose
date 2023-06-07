repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}

