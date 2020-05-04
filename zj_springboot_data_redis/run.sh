#!/bin/bash
set -u

if [[ $1 == "web" ]]; then
  echo "build spring-boot jar."
  mvn spring-boot:run -Dmaven.test.skip=true
fi

if [[ $1 == "dbstart" ]]; then
  echo "start redis."
  docker run --name test-redis -d --rm -p 6379:6379 -v /tmp/redis:/data redis:4.0
fi

if [[ $1 == "dbstop" ]]; then
  echo "stop redis."
  docker stop test-redis
fi

# CHECK REDIS ENV
# docker exec -it redis-test sh
# docker$ redis-cli
# redis-cli$ keys *
# redis-cli$ get 1

echo "run spring boot data redis done."
