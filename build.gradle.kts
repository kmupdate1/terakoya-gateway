plugins {
    `maven-publish`
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.kotlin.serialization }
}

group = "jp.lax256.terakoya"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
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
    jvmToolchain(22)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            val repoName = if (project.version.toString().endsWith("-SNAPSHOT")) "snapshots" else "releases"

            name = "TerakoyaNexus"
            url = uri("http://100.98.144.29:8081/nexus/repository/maven-$repoName/")

            isAllowInsecureProtocol = true
            credentials {
                username = project.findProperty("nexus.username")?.toString()
                password = project.findProperty("nexus.password")?.toString()
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
