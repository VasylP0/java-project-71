plugins {
    application
    id("java")
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
    useJUnitPlatform()
    jvmArgs("--enable-preview") // Enable preview features for tests
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Set Java version to 21
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview") // Enable preview features during compilation
}

checkstyle {
    toolVersion = "10.12.0" // Checkstyle version
    configFile = file("config/checkstyle/checkstyle.xml") // Checkstyle config
}

tasks.checkstyleMain {
    source = fileTree("src/main/java") // Checkstyle for main sources
}

tasks.checkstyleTest {
    source = fileTree("src/test/java") // Checkstyle for test sources
}

jacoco {
    toolVersion = "0.8.10" // Jacoco version for code coverage
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // Ensure Jacoco runs after tests
    reports {
        xml.required.set(true) // Generate XML report
        html.required.set(true) // Generate HTML report
    }
}
