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
    implementation(libs.activity.compose)

    // Lifecycle
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.runtime)

    // AppCompat
    implementation(libs.appcompat)

    // Unit testing
    testImplementation(libs.junit5)
    testImplementation(libs.mockk)
    testImplementation(libs.hamcrest)
    testImplementation(libs.kotlinx.coroutines.test)

    // UI testing
    androidTestImplementation(libs.compose.ui.test)
    androidTestImplementation(libs.compose.ui.manifest)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.espresso.core)

    debugImplementation(libs.compose.tooling)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
