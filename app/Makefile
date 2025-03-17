# Set SHELL to ensure consistency across different environments
SHELL := /bin/bash

# Define variables
APP_DIR := app

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
