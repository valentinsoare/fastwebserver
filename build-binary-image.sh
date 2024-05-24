#!/bin/bash

get_sdk_and_install_tools() {
  curl -s "https://get.sdkman.io" | bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"

  sdk install java 22.3.r17-nik
  sdk use java 22.3.r17-nik
}

build_the_binary() {
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

  get_sdk_and_install_tools
  build_the_binary
  checks_if_build_successful
}

main