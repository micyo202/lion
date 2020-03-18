#!/bin/sh
if [[ -z $1 ]] || [[ "mysql" == $1 ]]; then
  # mysql 方式启动（默认）
  echo "start with mysql..."
  RABBIT_ADDRESSES=localhost:5672 \
  RABBIT_USER=guest \
  RABBIT_PASSWORD=guest \
  STORAGE_TYPE=mysql \
  MYSQL_HOST=localhost \
  MYSQL_USER=lion \
  MYSQL_PASS=lion \
  MYSQL_DB=zipkin \
  java -jar zipkin-server-*exec.jar
elif [[ "es" == $1 ]] || [[ "elasticsearch" == $1 ]]; then
  # elasticsearch 方式启动
  echo "start with elasticsearch..."
  RABBIT_ADDRESSES=localhost:5672 \
  RABBIT_USER=guest \
  RABBIT_PASSWORD=guest \
  STORAGE_TYPE=elasticsearch \
  ES_HOSTS=http://localhost:9200 \
  java -jar zipkin-server-*exec.jar
else
  # 参数不正确
  echo "ERROR:"
  echo "the parameter should be mysql or es/elasticsearch"
fi