#!/bin/bash
set -e

mvn spring-boot:run -Dmaven.test.skip=true
echo "run spring boot done."
