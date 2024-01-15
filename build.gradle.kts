import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_KOTLIN
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_TEST_SRC_DIR_KOTLIN
import kotlinx.benchmark.gradle.JvmBenchmarkTarget
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val benchmarksSrc = "src/benchmarks/kotlin"

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.allopen") version "1.9.21"

    id("org.jetbrains.kotlinx.benchmark") version "0.4.10"
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.10")
    testImplementation(kotlin("test"))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.4")
}

kotlin {
    sourceSets {
        main {
            kotlin {
                srcDir(benchmarksSrc)
            }
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.ExperimentalStdlibApi")
        jvmTarget = JvmTarget.JVM_21
        languageVersion = KotlinVersion.KOTLIN_1_9
    }
}

tasks.withType<Test> {
    useJUnitPlatform {
        excludeTags = setOf(getProperty("TEST_IGNORE_TAG", "playground"))
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

detekt {
    config.from(files("detekt-config.yml"))
    buildUponDefaultConfig = true
    source.from(
        objects.fileCollection().from(
            DEFAULT_SRC_DIR_KOTLIN,
            DEFAULT_TEST_SRC_DIR_KOTLIN,
            benchmarksSrc
        )
    )
}

benchmark {
    configurations {
        named("main") {
            warmups = 1
            iterations = 4
            iterationTime = 5
            iterationTimeUnit = "sec"
        }
    }
    targets {
        register("main") {
            this as JvmBenchmarkTarget
            jmhVersion = "1.36"
        }
    }
}

fun getProperty(key: String, default: String = ""): String = System.getenv(key) ?: default
