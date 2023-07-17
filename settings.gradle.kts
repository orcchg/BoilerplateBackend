rootProject.name = "BackendBoilerplate"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://plugins.gradle.org/m2/")
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://jitpack.io")
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.google.devtools.ksp")) {
                useModule("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.7.10-1.0.6")
            }
        }
    }
}
