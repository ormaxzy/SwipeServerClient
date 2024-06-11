plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id ("kotlinx-serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation ( libs.kotlin.stdlib )
    implementation ( libs.kotlinx.serialization.json )
}