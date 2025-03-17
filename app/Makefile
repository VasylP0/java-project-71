# Set SHELL to ensure consistency across environments
SHELL := /bin/bash

# Define the correct app directory (no `code/`)
APP_DIR := .

# Setup project dependencies
setup:
	cd $(APP_DIR) && ./gradlew clean installDist

# Build the project
build:
	cd $(APP_DIR) && ./gradlew clean build

# Run tests
test:
	cd $(APP_DIR) && ./gradlew test

# Run linting (Checkstyle)
lint:
	cd $(APP_DIR) && ./gradlew checkstyleMain checkstyleTest

# Run full pipeline (build + test + lint)
ci: build test lint

# Clean build files
clean:
	cd $(APP_DIR) && ./gradlew clean
