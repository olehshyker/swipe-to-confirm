import com.example.buildlogic.AppConfig

plugins {
    id("build.android.application")
    id("build.android.application.compose")
    id("build.spotless")
}

android {
    namespace = "com.olehsh.swipetoconfirm"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.olehsh.swipetoconfirm"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

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
}

dependencies {
    // compose
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.constraintlayout)

    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.activity.compose)
}