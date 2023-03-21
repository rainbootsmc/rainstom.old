enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositories {
        maven("https://jitpack.io")
        maven("https://maven.uten2c.dev") // Rainstom
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

rootProject.name = "rainstom"
include("code-generators")
include("jmh-benchmarks")
include("jcstress-tests")
include("demo")
include("testing")
