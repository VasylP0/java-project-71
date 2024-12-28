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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2") // JSON parsing
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2") // YAML parsing
    implementation("info.picocli:picocli:4.7.0") // CLI library
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0") // Testing
    testImplementation("org.assertj:assertj-core:3.24.2") // AssertJ for fluent assertions
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.12.0"
    configFile = file("config/checkstyle/checkstyle.xml")
}

tasks.checkstyleMain {
    source = fileTree("src/main/java")
}

tasks.checkstyleTest {
    source = fileTree("src/test/java")
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
