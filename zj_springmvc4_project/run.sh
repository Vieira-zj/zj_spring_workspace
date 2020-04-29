#!/bin/bash
set -eu

if [[ $1 == "war" ]]; then
  echo "run clean and package."
  mvn clean package -Dmaven.test.skip=true
fi

if [[ $1 == "start" ]]; then
  container=$(docker ps | grep tomcat | wc -l)
  if [[ $container -eq 0 ]]; then
  	echo "start tomcat container"
    docker run --name tomcat -d --rm -v /tmp/dist:/tmp/dist -p 8080:8080 tomcat
    sleep 1
  fi

  echo "copy war"
  war_file_path="/tmp/dist/springmvc4.war"
  cp target/springmvc4-0.0.1-SNAPSHOT.war ${war_file_path}
  echo "refresh war in tomcat"
  docker exec tomcat cp ${war_file_path} /usr/local/tomcat/webapps/
  sleep 1
  docker restart tomcat
  echo "tomcat restarted, and access: http://localhost:8080/springmvc4/index"
fi

echo "springmvc4 done."
