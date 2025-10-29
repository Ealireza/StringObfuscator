pluginManagement {
    repositories {
        // Keep Google first but restrict to Android-related groups to avoid accidental plugin lookups here
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        // Plugin & artifact repos
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

rootProject.name = "StringObfuscator"

include(":annotation")
include(":processor")
include(":runtime")
include(":sample")
