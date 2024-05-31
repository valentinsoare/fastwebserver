# Use Fedora 39 as the base image
FROM fedora:39
LABEL maintainer="Valentin Soare <https://github.com/valentinsoare/fastwebserver>"

# Create a new directory for the application
RUN mkdir -p /home/custom-application/fastwebserver

# Copy the current directory contents into the container at /home/custom-application/fastwebserver
COPY .. /home/custom-application/fastwebserver/

# Create a symbolic link from /dev/stdout to /home/custom-application/fastwebserver/server.logs
# This allows the application to write logs to stdout, which can be accessed using the `docker logs` command
RUN ln -sf /dev/stdout /home/custom-application/fastwebserver/server.logs

# Expose port 8080 for the application
EXPOSE 8080

# Create a Docker volume for persistent storage
VOLUME application-storage:/home/custom-application

# Set the working directory to /home/custom-application/fastwebserver
WORKDIR /home/custom-application/fastwebserver

# Set exec on running bash script
RUN chmod +x fastwebserver-runner.sh

# Define the command to run the application
# The application logs are redirected to server.logs
CMD ["sudo", "./fastwebserver-runner.sh", ">", "server.logs", "2>&1"]
