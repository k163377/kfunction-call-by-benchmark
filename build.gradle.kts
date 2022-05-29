import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0-RC"
    id("me.champeau.gradle.jmh") version "0.5.3"
}

group = "org.wrongwrong"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

jmh {
    warmupForks = 2
    warmupBatchSize = 3
    warmupIterations = 3
    warmup = "1s"

    fork = 2
    batchSize = 3
    iterations = 2
    timeOnIteration = "1500ms"

    failOnError = true
    isIncludeTests = false

    resultFormat = "CSV"
}
