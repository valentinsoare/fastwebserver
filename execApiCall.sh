#!/bin/bash

# This script is used to make a request to the local server and then immediately kill the request.

# Use curl to make a request to the local server on port 8080. The & at the end runs the command in the background.
curl localhost:8080 &

# Sleep for 0.2 seconds to give the curl command time to start.
sleep 0.2

# Use pgrep to find the process ID of the curl command and kill it. This effectively cancels the request.
kill -9 $(pgrep curl)

