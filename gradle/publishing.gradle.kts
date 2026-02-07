// 型を明示的に指定して configure を呼ぶ
configure<org.gradle.api.publish.PublishingExtension> {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            // 本体側で buildFatJar された成果物を指定
            artifact(file("${project.layout.buildDirectory.get()}/libs/${project.name}-all.jar")) {
                classifier = "all"
            }
        }
    }

    repositories {
        maven {
            // ルートプロジェクトで定義した変数を参照
            val isRelease = rootProject.extra["isRelease"] as? Boolean ?: false
            val repoType = if (isRelease) "releases" else "snapshots"
            val domain = System.getenv("TERAKOYALABO_DEV_DOMAIN") ?: "https://67e7-160-86-90-2.ngrok-free.app"

            name = "TerakoyaNexus"
            url = uri("$domain/nexus/repository/terakoyalabo-app-$repoType")

            credentials {
                username = System.getenv("TERAKOYA_NEXUS_USERNAME")
                password = System.getenv("TERAKOYA_NEXUS_PASSWORD")
            }
        }
    }
}
