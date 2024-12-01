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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MvvmLargeScaleStructure"
include(":app")
include(":core")
include(":core:data")
include(":core:data:local")
include(":core:data:mapper")
include(":core:data:remote")
include(":core:data:repository")
include(":core:domain")
include(":core:ui")
include(":di")
include(":features")
include(":features:feature1")
include(":features:feature2")
include(":foundation")
include(":foundation:utils")
