plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.abexapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.abexapp"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.cast.framework)
    implementation(libs.androidx.media3.common.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")

    // Gson converter for Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")

    // OkHttp (for logging, optional but helpful for debugging)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines (if using suspend functions)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Live Data
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // For viewModelScope
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

}