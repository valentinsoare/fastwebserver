#!/bin/bash

# Switch to gitlab-runner
sudo su gitlab-runner

# Get the installation script for SDKMAN.io.
curl -s "https://get.sdkman.io" | bash

# Source the script into current bash session.
source "/home/gitlab-runner/.sdkman/bin/sdkman-init.sh"

# Install GraalVM.
sdk install java 22.3.r17-nik