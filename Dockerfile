# Use Fedora version 39 as the base image
FROM fedora:39

# Set the maintainer of the Docker image
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

# Run a series of commands in the base image:
# 1. Modify the sudoers file to allow members of the wheel group to execute any command
# 2. Create a new user 'fastwebserver' and add it to the 'wheel' group
# 3. Install the 'passwd' and 'java-17-openjdk.x86_64' packages
# 4. Set the password for the 'fastwebserver' user to 'default'
# 5. Change the owner of the '/home/fastwebserver' directory to 'fastwebserver'
# 6. Change the permissions of the '/home/fastwebserver' directory to 750 (read, write, execute for owner, read and execute for group, no permissions for others)
RUN sed -i 's/# %wheel/%wheel/g' /etc/sudoers \
    && useradd -c 'fastwebserver user application' -m -s /bin/bash fastwebserver \
    && usermod -aG wheel fastwebserver \
    && dnf install passwd java-17-openjdk.x86_64 -y \
    && echo "default" | passwd fastwebserver --stdin \
    && chown -R fastwebserver:fastwebserver /home/fastwebserver \
    && chmod 750 /home/fastwebserver/

# Create a symbolic link from '/dev/stdout' to '/home/fastwebserver/server.logs' and switch to the 'fastwebserver' user
RUN ln -sf /dev/stdout /home/fastwebserver/server.logs \
    && sudo su fastwebserver

# Expose port 8080 from the container to the host
EXPOSE 8080

# Create a volume 'webserver-storage' mounted at '/home/fastwebserver'
VOLUME fastwebserver-storage:/home/fastwebserver

# Set the working directory in the container to '/home/fastwebserver'
WORKDIR /home/fastwebserver/

# Copy the 'fastwebserver' directory from the host to the current location ('/home/fastwebserver') in the container
COPY target/fastwebserver target/fastwebserver-runner.sh ./

# Set the command to be executed when the container starts. It runs the 'fastwebserver-runner.sh' script and redirects its output to 'server.logs'
CMD ["/home/fastwebserver/fastwebserver-runner.sh", ">", "server.logs", "2>&1"]
