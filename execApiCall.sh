#!/bin/bash

curl localhost:8080 &
sleep 0.2
kill -9 $(pgrep curl)

