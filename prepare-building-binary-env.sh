#!/bin/bash

declare dir_exists

prepare_where_to_build() {
  cd /home/building-binary_environment/fastwebserver

  if [[ "${dir_exists}" -ne 0 ]]; then
    printf "\n%s\n" "Issues when trying to open /home/building-binary_environment/fastwebserver..."
    exit 1
  fi
}

get_sdk_and_install_tools() {
  curl -s "https://get.sdkman.io" | bash
  source $HOME/.sdkman/bin/sdkman-init.sh

  sdk install java 17.0.10-amzn
  sdk install java 22.3.r17-nik

  sdk use java 22.3.r17-nik
}

build_the_binary() {
  mvn -Pnative -DskipNativeTests native:compile
}

checks_if_build_successful() {
  if [ ! -f target/fastwebserver ]; then
    printf "\n%s\n" "Failed to create native image."
    exit 1
  fi

  printf "\n%s\n" "Native image created successfully."
}

main() {
  prepare_where_to_build
  get_sdk_and_install_tools
  build_the_binary
  checks_if_build_successful
}

main