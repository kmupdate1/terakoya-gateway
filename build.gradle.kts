plugins {
    `maven-publish`
    alias { libs.plugins.kotlin.multiplatform }
}

group = "jp.terakoyalabo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    linuxArm64()
    linuxX64()
    macosArm64()
    macosX64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test.common)
        }
    }

    jvmToolchain(21)
}
