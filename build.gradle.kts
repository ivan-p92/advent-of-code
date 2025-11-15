plugins {
    kotlin("jvm") version "2.2.21"
}

group = "net.iplantevin"
version = "1.0-SNAPSHOT"

val kotestVersion = "6.0.4"
val junitVersion = "6.0.1"
val coroutinesVersion = "1.10.2"

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