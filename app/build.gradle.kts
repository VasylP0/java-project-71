plugins {
    application
    java
    id("checkstyle")
    id("jacoco")
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Libraries for parsing JSON and YAML
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")

    // CLI library
    implementation("info.picocli:picocli:4.7.0")

    // Testing dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")

    // JetBrains annotations for nullability
    implementation("org.jetbrains:annotations:24.0.1")
}

tasks.test {
    useJUnitPlatform() // Use JUnit Platform for tests
    jvmArgs("--enable-preview") // Enable Java preview features
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Use Java 21
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview") // Enable Java preview features during compilation
}

checkstyle {
    toolVersion = "10.12.0" // Checkstyle version
    configFile = file("config/checkstyle/checkstyle.xml") // Checkstyle config
}

jacoco {
    toolVersion = "0.8.10" // JaCoCo version for code coverage
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // Ensure JaCoCo runs after tests
    reports {
        xml.required.set(true) // Generate XML report
        html.required.set(true) // Generate HTML report
    }
}
