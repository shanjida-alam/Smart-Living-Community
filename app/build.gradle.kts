plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.dokka") version "1.9.20"
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
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
        // Add test orchestrator
        testInstrumentationRunnerArguments += mapOf(
            "clearPackageData" to "true"
        )
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += setOf(
                "META-INF/NOTICE.md",
                "META-INF/LICENSE.md",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }

        // Add these configurations
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
    }

}

dependencies {
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.recyclerview)
    implementation(libs.firebase.auth)
    implementation(libs.gridlayout)
    dokkaPlugin(libs.android.documentation.plugin)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("de.hdodenhof:circleimageview:3.1.0")
    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.1")

    // Room (for database)
    implementation("androidx.room:room-runtime:2.6.0")
    annotationProcessor("androidx.room:room-compiler:2.6.0")

    // Coroutine support (optional for later)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

    // Navigation component
    val nav_version = "2.5.3" // Use the latest version
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation ("androidx.core:core:1.7.0")

    implementation("com.sun.mail:android-mail:1.6.7")
    implementation("com.sun.mail:android-activation:1.6.7")

    implementation("androidx.cardview:cardview:1.0.0")

    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")

    // Firebase Firestore for database testing (ensures Firestore integration works as expected)
    androidTestImplementation("com.google.firebase:firebase-firestore:24.0.0")

    // Google Tasks for Task API (used in Firebase query operations)
    androidTestImplementation("com.google.android.gms:play-services-tasks:18.0.2")
//    fetching image url from firebase
    implementation("com.github.bumptech.glide:glide:4.15.0") // Check for the latest version
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0") // For annotation processing



    // Testing Dependancies
    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")

    // Android Testing
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("org.mockito:mockito-android:5.7.0")
    androidTestImplementation("com.google.truth:truth:1.1.5")
    androidTestImplementation("com.google.android.gms:play-services-tasks:18.0.2")

    // For CI Pipeling test
    androidTestUtil("androidx.test:orchestrator:1.4.2")

}