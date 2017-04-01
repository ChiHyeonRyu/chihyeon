#!/bin/bash
if ! [ -d /var/log/riot ];
then
  sudo mkdir /var/log/riot
fi
cd /home/ec2-user/$1
sudo /usr/bin/java -jar -Dserver.port=$2 -Dapplication.name=$1 *.jar > /dev/null 2> /dev/null < /dev/null &
