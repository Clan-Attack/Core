rootProject.name = "Core"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}


include("Api")
include("Library")
include("Pack")
