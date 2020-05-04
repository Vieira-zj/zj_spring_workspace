#!/bin/bash
set -u

if [[ $1 == "web" ]]; then
  echo "build spring-boot jar."
  mvn spring-boot:run -Dmaven.test.skip=true
fi

if [[ $1 == "dbstart" ]]; then
  echo "start mysql."
  docker run --name test-mysql --hostname mysql -d --rm -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=root -v /tmp/mysql:/var/lib/mysql mysql:8.0.20
  sleep 5 
  docker exec test-mysql sh -c "mysql -hmysql -uroot -proot -e \"select version();create database test;\""
fi

if [[ $1 == "dbstop" ]]; then
  echo "stop mysql."
  docker stop test-mysql
fi

# CHECK DB ENV
# docker exec -it test-mysql sh
# mysql -hmysql -uroot -proot
#
# create database test;
# show databases;
# use test;
#
# show tables;
# select COLUMN_NAME,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE,COLUMN_COMMENT from information_schema.COLUMNS where TABLE_NAME='person';
#
# show variables like 'character%';

echo "run spring boot data jpa done."
