plugins {
    application
    id("java")
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
    implementation("info.picocli:picocli:4.7.0") // CLI library
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0") // Testing
}

tasks.test {
    useJUnitPlatform()
}
