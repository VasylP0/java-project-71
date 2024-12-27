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
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation("info.picocli:picocli:4.7.0")
}

tasks.test {
    useJUnitPlatform()
}
