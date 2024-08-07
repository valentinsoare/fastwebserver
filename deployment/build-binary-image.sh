#!/bin/bash

# Function to build the binary of the application
build_the_binary() {
  # Source the script into current bash session.
  source "/$HOME/.sdkman/bin/sdkman-init.sh"

  # Set to use GraalVM.
  sdk use java 22.3.r17-nik

  # Run Maven clean and package commands to clean the project and package it into a JAR file
  mvn clean package

  # Run Maven with the native profile to compile the project into a native image
  # Skip the native tests during this process
  mvn -Pnative -DskipNativeTests native:compile
}

# Function to check if the build was successful, means that if src directory exists or not.
checks_if_build_successful() {
  if [ ! -f target/fastwebserver ]; then
    printf "\n\033[31m%s\033[0m\n" "Failed to create native image."
    exit 1
  fi

  printf "\n\033[32m%s\033[0m\n" "Native image created successfully."
}

main() {
  # Set the shell option -e (errexit) to exit the script if any command returns a non-zero status
  set -e

  build_the_binary
  checks_if_build_successful
}

main