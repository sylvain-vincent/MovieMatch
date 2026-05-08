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

// app
include(":app")

// Core modules
include(":core:model")
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:ui")
include(":core:datastore")

// Domain modules
include(":domain")

// Feature modules
include(":feature:movies")
include(":feature:detail")
include(":feature:match")

project(":core:model").projectDir = file("core/model")
project(":core:common").projectDir = file("core/common")
project(":core:network").projectDir = file("core/network")
project(":core:database").projectDir = file("core/database")
project(":core:ui").projectDir = file("core/ui")
project(":core:datastore").projectDir = file("core/datastore")
project(":feature:movies").projectDir = file("feature/movies")
project(":feature:detail").projectDir = file("feature/detail")
project(":feature:match").projectDir = file("feature/match")
