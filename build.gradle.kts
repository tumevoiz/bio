import org.gradle.api.JavaVersion.VERSION_11
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "2.0.20"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.gitlab.arturbosch.detekt") version("1.23.7")
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass = "bio.bioKt"
}

tasks {
    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier = null
        archiveVersion = null
        mergeServiceFiles()
        dependsOn(distTar, distZip)
        isZip64 = true
    }
}

repositories {
    mavenCentral()
}

tasks {
    withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            allWarningsAsErrors = false
            jvmTarget.set(JVM_11)
            freeCompilerArgs.add("-Xjvm-default=all")
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }

    java {
        sourceCompatibility = VERSION_11
        targetCompatibility = VERSION_11
    }

}


dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.4.0"))
    implementation(platform("org.http4k:http4k-connect-bom:5.24.1.0"))
    implementation("org.http4k:http4k-client-okhttp")
    implementation("org.http4k:http4k-connect-storage-redis")
    implementation("org.http4k:http4k-contract")
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-jackson")
    implementation("org.http4k:http4k-format-kotlinx-serialization")
    implementation("org.http4k:http4k-format-moshi")
    implementation("org.http4k:http4k-opentelemetry")
    implementation("org.http4k:http4k-server-undertow")
    testImplementation("org.http4k:http4k-testing-approval")
    testImplementation("org.http4k:http4k-testing-hamkrest")
    testImplementation("org.http4k:http4k-testing-kotest")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.1")
}


