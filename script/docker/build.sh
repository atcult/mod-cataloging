#!/usr/bin/env bash

mvn clean install

docker build -t mod-marccat .
