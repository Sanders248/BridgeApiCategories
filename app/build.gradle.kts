plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.bridgeapicategories"
    val sdkVersion: Int by rootProject.extra
    compileSdk = sdkVersion

    defaultConfig {
        applicationId = "com.bridgeapicategories"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    kapt(libs.hilt.compiler)
    kapt(libs.room.compiler)

    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)

    implementation(libs.hilt.android)
    implementation(libs.room.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.preview)
    implementation(libs.compose.material3)

    implementation("com.squareup.okhttp3:okhttp:4.10.0")


    testImplementation(libs.junit)
}