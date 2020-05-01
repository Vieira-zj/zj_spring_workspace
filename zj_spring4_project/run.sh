#!/bin/bash
set -eu

if [[ $1 = "jar" ]]; then
  echo "build spring jar"
  mvn clean package
fi

if [[ $1 = "exec" ]]; then
  echo "run jar"
  target="target/spring4-0.0.1-SNAPSHOT-jar-with-dependencies.jar"
  main="com.zhengjin.spring4.ch2.el.Main"
  java -cp ${target} ${main}
fi

echo "spring4 demo done."
