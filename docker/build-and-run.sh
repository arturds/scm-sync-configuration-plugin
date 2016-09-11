#!/bin/bash

container_name="jenkins-scm"
image_name="netoht/$container_name:latest"
hpi="target/scm-sync-configuration.hpi"

echo 'copy plugin to target'
mkdir target/
cp ../target/scm-sync-configuration.hpi target/

export PLUGIN_VERSION=$(unzip -p $hpi META-INF/MANIFEST.MF | tr -d '\r' | grep "^Plugin-Version:" | sed 's/Plugin-Version: //')
echo "plugin version: $PLUGIN_VERSION"

echo 'stop docker-compose'
docker-compose down

echo 'remove container'
docker rm -vf $container_name

echo 'remove image'
docker rmi -f $image_name

echo 'build image'
docker build -t $image_name --build-arg PLUGIN_VERSION="$PLUGIN_VERSION" .

echo 'startup docker-compose'
docker-compose up --force-recreate