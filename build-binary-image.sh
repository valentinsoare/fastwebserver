#!/bin/bash

build_the_binary() {
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk use java 22.3.r17-nik
  mvn clean package
  mvn -Pnative -DskipNativeTests native:compile
}

checks_if_build_successful() {
  if [ ! -f target/fastwebserver ]; then
    printf "\n\033[31m%s\033[0m\n" "Failed to create native image."
    exit 1
  fi

  printf "\n\033[32m%s\033[0m\n" "Native image created successfully."
}

main() {
  set -e

  build_the_binary
  checks_if_build_successful
}

main