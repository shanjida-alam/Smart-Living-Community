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
    packaging {
        resources {
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/LICENSE.md"
        }
    }
}

dependencies {
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.firebase.firestore)
    dokkaPlugin(libs.android.documentation.plugin)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
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
    implementation("androidx.core:core:1.7.0")

    implementation("com.sun.mail:android-mail:1.6.7")
    implementation("com.sun.mail:android-activation:1.6.7")

    implementation("androidx.cardview:cardview:1.0.0")

    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")

    // Fetching image url from firebase
    implementation("com.github.bumptech.glide:glide:4.15.0") // Check for the latest version
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0") // For annotation processing

    // Android-specific UI and Fragment testing
    androidTestImplementation("androidx.fragment:fragment-testing:1.5.5") // FragmentScenario support
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Mockito (if you decide to use mocking)
    testImplementation("org.mockito:mockito-core:5.7.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

}
