plugins {
    application
    jacoco
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
    id("io.ktor.plugin") version "2.1.1"
    id("com.squareup.sqldelight") version "1.5.4"
}

group = "com.orcchg.boilerplate.backend"
version = "1.0"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("com.orcchg.boilerplate.backend.MainServiceKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

sqldelight {
    database("BackendBoilerplateDatabase") {
        packageName = "com.orcchg.boilerplate.backend.database"
        dialect = "postgresql"
        schemaOutputDirectory = file("src/main/sqldelight/databases")
        verifyMigrations = true
    }
}

dependencies {
    implementation(libs.google.api)
    implementation(libs.google.integrity)
    implementation(libs.gson)
    implementation(libs.jose4j)
    implementation(libs.json)
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.jvm)
    implementation(libs.ktor.server.netty.core)
    implementation(libs.ktor.server.netty.jvm)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.server.serialization)
    implementation(libs.logback)
    implementation(libs.postgresql.jdbc.driver)
    implementation(libs.sqldelight.coroutines)
    implementation(libs.sqldelight.driver.core)
    implementation(libs.sqldelight.driver.jdbc)

    testImplementation(libs.hamcrest)
    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
    testImplementation(libs.kt.coroutines.test)
    testImplementation(libs.kt.junit)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.ktor.server.test.jvm)
    testImplementation(libs.mockk)
}

tasks.register("stage").configure {
    dependsOn("installDist")
}
