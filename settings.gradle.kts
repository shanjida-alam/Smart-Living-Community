pluginManagement {
    repositories {
        google() // Google's Maven repository
        gradlePluginPortal() // Gradle Plugin Portal for other plugins
        mavenCentral() // Maven Central repository
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CreateEvent"
include(":app")
