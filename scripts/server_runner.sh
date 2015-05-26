#!/bin/bash

PROJECT_NAME=openbidder
PROJECT_VERSION=1.0
PROJECT=${PROJECT_NAME}-${PROJECT_VERSION}

if [ $# -eq 0 ]
  then
    echo "No server runner class supplied"
    exit 1
fi

sbt clean dist
cd target/universal/
unzip ${PROJECT}.zip
cd ${PROJECT}/bin
chmod u+x ${PROJECT_NAME}
./${PROJECT_NAME} $1