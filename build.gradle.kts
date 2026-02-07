plugins {
    `maven-publish`
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.kotlin.serialization }
    alias { libs.plugins.ktor.plugin }
}

// コマンド実行時に -Prelease=true をつけた時だけ本番モード
val isRelease = project.hasProperty("release") && project.property("release") == "true"
val currentVersion = rootProject.findProperty("version.gateway")?.toString() ?: "unspecified"

group = "jp.terakoyalabo"
version = if (isRelease) currentVersion else "$currentVersion-SNAPSHOT"

extra["isRelease"] = isRelease

repositories {
    mavenCentral()
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    // Ktor Server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.json)

    // Ktor Client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)

    // Logging
    implementation(libs.logback.classic)

    // Testing
    implementation(libs.kotlin.test)
    implementation(libs.ktor.server.test.host)
}

kotlin {
    jvmToolchain(21)
}

// apply(from = "gradle/sonar.gradle.kts")
apply(from = "gradle/publishing.gradle.kts")

tasks {
    test {
        useJUnitPlatform()
    }

    // publishタスクは、必ずbuildFatJarが完了してから実行されるように強制する
    withType<PublishToMavenRepository> {
        dependsOn(buildFatJar)
    }
}
