.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install-dist:
	./gradlew clean installDist

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew test
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

checkstyle:
	./gradlew checkstyleMain

coverage:
	./gradlew test
	./gradlew jacocoTestReport

setup:
	./gradlew clean installDist # Corrected to use installDist for applications

.PHONY: build clean install-dist run test report lint checkstyle coverage setup
