.PHONY: build test clean setup coverage

build:
	./gradlew clean build

test:
	./gradlew test

coverage:
	./gradlew jacocoTestReport

clean:
	./gradlew clean

setup:
    cd code/app && ./gradlew clean installDist

