// App-level build.gradle.kts file for configuring the Android application module.

plugins {
    alias(libs.plugins.android.application) // Android application plugin
    alias(libs.plugins.kotlin.android) // Kotlin Android plugin
    alias(libs.plugins.google.gms.google.services) // Google services plugin for Firebase
}

android {
    namespace = "com.example.createevent" // Application namespace
    compileSdk = 35 // Compile SDK version

    defaultConfig {
        applicationId = "com.example.createevent" // Unique application ID
        minSdk = 24 // Minimum SDK version
        targetSdk = 35 // Target SDK version
        versionCode = 1 // Version code for the app
        versionName = "1.0" // Version name for the app

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Test runner for instrumentation tests
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Disable code minification for release builds
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Default ProGuard file for optimized builds
                "proguard-rules.pro" // Custom ProGuard rules
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Java source compatibility
        targetCompatibility = JavaVersion.VERSION_1_8 // Java target compatibility
    }
    kotlinOptions {
        jvmTarget = "1.8" // Kotlin JVM target
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.appcompat) // AndroidX AppCompat for backward compatibility
    implementation(libs.material) // Material design components
    implementation(libs.activity) // Activity library
    implementation(libs.constraintlayout) // ConstraintLayout for layouts

    // Firebase dependencies
    implementation(libs.firebase.database) // Firebase Realtime Database
    implementation(libs.firebase.storage) // Firebase Storage
    implementation(libs.firebase.firestore) // Firebase Firestore (if needed)

    // Navigation Component
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.1")

    // Room (for database)
    implementation("androidx.room:room-runtime:2.6.0")
    annotationProcessor("androidx.room:room-compiler:2.6.0")

    // Coroutine support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Testing dependencies
    testImplementation(libs.junit) // JUnit for unit tests
    androidTestImplementation(libs.ext.junit) // AndroidX JUnit extension for instrumentation tests
    androidTestImplementation(libs.espresso.core) // Espresso for UI testing

    // Documentation plugin
    dokkaPlugin("org.jetbrains.dokka:dokka-android:1.9.20") // Dokka plugin for documentation generation
}
