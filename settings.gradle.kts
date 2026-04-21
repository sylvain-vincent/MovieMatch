pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MovieMatch"
include(":app")
include(":domain")
include(":core:network")
include(":core:database")
include(":core:ui")
include(":feature:movies")
include(":feature:detail")
include(":feature:match")

project(":core:network").projectDir = file("core/network")
project(":core:database").projectDir = file("core/database")
project(":core:ui").projectDir = file("core/ui")
project(":feature:movies").projectDir = file("feature/movies")
project(":feature:detail").projectDir = file("feature/detail")
project(":feature:match").projectDir = file("feature/match")
