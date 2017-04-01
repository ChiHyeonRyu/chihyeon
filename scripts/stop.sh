#!/bin/bash
pid=`ps -eaf | grep application.name=$1 | egrep -v "grep|sudo" | awk '{ print $2 }';`
sudo /usr/bin/kill -9 $pid > /dev/null 2> /dev/null < /dev/null
