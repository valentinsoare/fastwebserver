#!/bin/bash

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
s