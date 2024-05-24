#!/bin/bash

# Runner written in bash, made in order to be able to load and run the binary file using systemd service.
# With this approach enable/disable/restart/start the application is easily made and
# the application is under monitoring the whole time is running.
# Along those actions we can apply to the app, we have logging provided by the systemd 
# along with proper timestamps and permissions on the app


# Starting the server.
printf "\n\033[32m %s\033[0m\n" " Starting the server"

# Sleep for 1 second to start the server.
sleep 1

# Start the main app, fastwebserver.
./target/fastwebserver

# Print the message that server will be stopped.
printf "\033[32m%s\033[0m\n" " Stopping the server"

# Print a newline after the app exited.
printf "\n\n"
