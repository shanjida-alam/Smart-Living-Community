// App-level build.gradle.kts file for configuring the Android application module

plugins {
    alias(libs.plugins.android.application) // Android application plugin
    alias(libs.plugins.kotlin.android) // Kotlin Android plugin
    alias(libs.plugins.google.gms.google.services) // Google services plugin for Firebase
    alias(libs.plugins.dokka.android) // Dokka plugin for documentation
}

android {
    namespace = "com.example.createevent" // Application namespace
    compileSdk = 34 // Set compileSdk to 34 for compatibility with AGP 8.5.2

    defaultConfig {
        applicationId = "com.example.createevent" // Unique application ID
        minSdk = 24 // Minimum SDK version
        targetSdk = 34 // Set targetSdk to 34 to match compileSdk
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

    // Firebase dependencies with compatible versions
    implementation("com.google.firebase:firebase-common:20.1.1")
    implementation("com.google.firebase:firebase-database:20.0.5")
    implementation("com.google.firebase:firebase-storage:20.1.0")
    implementation("com.google.firebase:firebase-firestore:24.1.1")

    // Navigation Component
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // ViewModel and LiveData for lifecycle management
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.1")

    // Room (for database)
    implementation("androidx.room:room-runtime:2.6.0")
    annotationProcessor("androidx.room:room-compiler:2.6.0")

    // Coroutine support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Testing dependencies for unit tests
    testImplementation("junit:junit:4.13.2") // JUnit for unit testing
    testImplementation("org.mockito:mockito-core:4.5.1") // Mockito for mocking
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4") // Coroutine test support
    testImplementation("androidx.arch.core:core-testing:2.1.0") // For InstantTaskExecutorRule

    // AndroidX Testing dependencies for instrumented tests
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // AndroidX JUnit extension
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Espresso for UI testing
}

// Enforce compatible Firebase versions
configurations.all {
    resolutionStrategy {
        force("com.google.firebase:firebase-common:20.1.1")
        force("com.google.firebase:firebase-firestore:24.1.1")
    }
}
