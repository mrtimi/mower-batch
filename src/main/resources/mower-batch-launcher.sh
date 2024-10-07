#!/bin/bash

CONFIG_FILE=$1  # First argument: Path to config.properties

source $CONFIG_FILE

# Run the Spring Batch application using the variables from config.properties
java -jar $JAR_FILE --spring.config.location=file:$PROPERTIES_FILE > $LOG_FILE 2>&1
