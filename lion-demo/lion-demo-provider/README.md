# lion-demo-provider（8601）

服务提供者，若有多个提供者服务端口从：8601、8602、8603...开始，以此类推。

- Druid数据源监控地址：[http://localhost:8601/druid](http://localhost:8601/druid)
- RESTful APIs文档地址：[http://localhost:8601/doc.html](http://localhost:8601/doc.html)

### jar
```shell script
SERVER_PORT=8601 \
REGISTER_HOST=localhost \
java -jar lion-demo-provider-x.x.x.jar
```

### docker
```shell script
docker pull micyo202/lion-demo-provider:tagname
```
```shell script
docker run -d \
-p 8601:8601 \
-e REGISTER_HOST=localhost \
micyo202/lion-demo-provider:tagname
```