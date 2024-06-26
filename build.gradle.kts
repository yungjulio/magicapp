// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    // id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
//    id ("org.jetbrains.kotlin.android") version "1.8.0" apply false
}

buildscript {
    val kotlin_version = "1.8.22"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}