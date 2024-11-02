plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.dokka") version "1.9.20"
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.smartlivingcommunity"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.smartlivingcommunity"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("androidx.cardview:cardview:1.0.0")

    //Dokka Plugin
    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:1.9.20")

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.1")

    // Room (for database)
    implementation("androidx.room:room-runtime:2.6.0")
    annotationProcessor("androidx.room:room-compiler:2.6.0")

    // JUnit for general unit testing
    testImplementation ("junit:junit:4.13.2")

    // Mockito for mocking objects
    testImplementation ("org.mockito:mockito-core:3.12.4")
    testImplementation ("org.mockito:mockito-inline:3.12.4")

    // Core Testing library for AndroidX (includes InstantTaskExecutorRule)
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    // Lifecycle LiveData Testing (optional, but useful for LiveData testing)
    testImplementation ("androidx.lifecycle:lifecycle-livedata-core-ktx:2.5.1")


}