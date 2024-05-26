#!/bin/bash

# Get the installation script for SDKMAN.io.
curl -s "https://get.sdkman.io" | bash

# Source the script into current bash session.
source "/$HOME/.sdkman/bin/sdkman-init.sh"

ls -latr

# Install GraalVM.
sdk install java 22.3.r17-nik

# Set GraalVM as current runner for java.
sdk use java 22.3.r17-nik