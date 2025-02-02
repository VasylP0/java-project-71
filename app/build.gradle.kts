plugins {
    application
    java
    id("checkstyle")
    id("jacoco")
}

application {
    mainClass.set("hexlet.code.App")
    applicationDefaultJvmArgs = listOf("src/test/resources/file1.json", "src/test/resources/file2.json") // Default run args
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
    implementation("info.picocli:picocli:4.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    implementation("org.jetbrains:annotations:24.0.1")
}

tasks {
    test {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
        testLogging {
            events("passed", "skipped", "failed")
            showStandardStreams = true
        }
    }

    withType<JavaCompile>().configureEach {
        options.compilerArgs.addAll(listOf("--enable-preview", "-Xlint:unchecked"))
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    val cleanReports by creating(Delete::class) {
        group = "cleanup"
        description = "Cleans up old test and coverage reports"
        delete("build/reports", "build/test-results")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

checkstyle {
    toolVersion = "10.12.0"
    configFile = file("config/checkstyle/checkstyle.xml") // Make sure this file exists!
}

tasks.register("runTests") {
    dependsOn("clean", "test")
    description = "Clean build and run all tests"
    group = "verification"
}
