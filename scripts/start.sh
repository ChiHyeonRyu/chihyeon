#!/bin/bash
if ! [ -d /var/log/riot ];
then
  sudo mkdir /var/log/riot
fi
cd /home/ec2-user/app
sudo /usr/bin/java -jar -Dserver.port=80 *.jar > /dev/null 2> /dev/null < /dev/null &
