plugins {
    `maven-publish`
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.kotlin.serialization }
    alias { libs.plugins.ktor.plugin }
}

group = "jp.lax256.terakoya"
version = "0.1.0-SNAPSHOT"

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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            artifact(file("${project.layout.buildDirectory.get()}/libs/${project.name}-all.jar")) {
                classifier = "all"
            }
        }
    }
    repositories {
        maven {
            val repoName = if (project.version.toString().endsWith("-SNAPSHOT")) "snapshots" else "releases"

            name = "TerakoyaNexus"
            url = uri("http://100.98.144.29:8081/repository/maven-$repoName")

            isAllowInsecureProtocol = true
            credentials {
                username = project.findProperty("nexus.username")?.toString()
                password = project.findProperty("nexus.password")?.toString()
            }
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    // publishタスクは、必ずbuildFatJarが完了してから実行されるように強制する
    withType<PublishToMavenRepository> {
        dependsOn(buildFatJar)
    }
}
