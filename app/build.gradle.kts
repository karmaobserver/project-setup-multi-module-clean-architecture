@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ktlint)
}

android {
    namespace = "com.aleksejmakaji.filefinder"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.aleksejmakaji.filefinder"
        minSdk = 26
        targetSdk = 33

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(libs.bundles.koin)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.bundles.navigation)

    testImplementation(libs.testing.junit)

    androidTestImplementation(libs.testing.androidx.junit)
    androidTestImplementation(libs.testing.espresso.core)
}
