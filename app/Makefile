.DEFAULT_GOAL := build

# Clean the project
clean:
	./gradlew clean

# Build the project
build:
	./gradlew clean build

# Install distribution (Fixes Hexlet's 'install' task issue)
install-dist:
	./gradlew clean installDist

# Run the project
run:
	./gradlew run

# Run tests
test:
	./gradlew test

# Generate coverage report (Fixes Hexlet's 'no rule to make target coverage' error)
coverage:
	./gradlew jacocoTestReport

# Run linting (Checkstyle)
lint:
	./gradlew checkstyleMain checkstyleTest

# Run Checkstyle only
checkstyle:
	./gradlew checkstyle

# Generate test reports
report:
	./gradlew report

# Ensure Makefile runs by default with `build`
.PHONY: build
