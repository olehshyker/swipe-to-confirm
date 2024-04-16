pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SwipeToConfirm"
include(":app")

// https://issuetracker.google.com/issues/315023802?pli=1
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
