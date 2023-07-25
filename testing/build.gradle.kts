plugins {
    `java-library`
    // `maven-publish`
}

group = "net.minestom.testing"
// version declared by root project

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven("https://maven.uten2c.dev") // Rainstom
}

dependencies {
    api(rootProject)

    api(libs.junit.api)
    api(libs.junit.params)
    api(libs.junit.suite.api)
    runtimeOnly(libs.junit.engine)
    runtimeOnly(libs.junit.suite.engine)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
