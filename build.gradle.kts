plugins {
    kotlin("jvm") version "2.1.0"
}

group = "net.iplantevin"
version = "1.0-SNAPSHOT"

val kotestVersion = "5.9.1"
val junitVersion = "5.11.3"
val coroutinesVersion = "1.8.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}