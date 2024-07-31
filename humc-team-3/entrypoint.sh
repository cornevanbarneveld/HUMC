#!/bin/sh

# Run the app if the first argument is 'run'
if ["$1" = 'run']
then
  # Run the application
  java -jar monoliet.jar
  exit 1
# otherwise try to run the given command
else
  exec "$@"
fi
