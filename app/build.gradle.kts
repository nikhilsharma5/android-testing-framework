plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.foundation)
    implementation("androidx.activity:activity-compose:1.8.0")

    // Lifecycle
    implementation(libs.androidx.lifecycle)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Unit testing
    testImplementation(libs.junit5)
    testImplementation(libs.mockk)
    testImplementation(libs.hamcrest)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // UI testing
    androidTestImplementation(libs.compose.ui.test)
    androidTestImplementation(libs.compose.ui.manifest)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0")

    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
