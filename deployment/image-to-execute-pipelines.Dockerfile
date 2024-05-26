# Use Fedora 39 as the base image

FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

# Install necessary packages using the Fedora package manager (dnf)
RUN dnf install unzip zip maven gcc glibc-static libstdc++-static zlib-devel git -y

# Set the global git configuration for user name and email
RUN git config --global user.name "valentinsoare" \
    && git config --global user.email "soarevalentinn@gmail.com"

# Create a Docker volume for persistent storage
VOLUME application-storage:/home/custom-application

# Set the working directory to /home/custom-application/fastwebserver
WORKDIR /home/custom-application/fastwebserver

# Copy the install-tools-for-building-binary.sh script into the container
COPY ./deployment/install-tools-for-building-binary.sh .

# Make the install-tools-for-building-binary.sh script executable
RUN chmod +x ./install-tools-for-building-binary.sh

# Run the install-tools-for-building-binary.sh script and remove all files in the current directory after it's done
RUN ./install-tools-for-building-binary.sh && rm -fdr *

# Define the default command to be run when the container starts
CMD ["/bin/bash"]