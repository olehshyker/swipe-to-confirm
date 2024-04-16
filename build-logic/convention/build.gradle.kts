plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "build.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "build.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("spotless") {
            id = "build.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}
