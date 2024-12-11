plugins {
    application
    id("java")
}

application {
    mainClass.set("hexlet.code.App") // Replace "hexlet.code.Main" with your actual Main class name if different
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
