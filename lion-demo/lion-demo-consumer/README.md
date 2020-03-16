# lion-demo-consumer（8701）

服务消费者，多消费者服务端口从：8701、8702、8703...以此类推

- Druid数据源监控地址：[http://localhost:8701/druid](http://localhost:8701/druid)
- RESTful APIs文档地址：[http://localhost:8701/doc.html](http://localhost:8701/doc.html)

### jar
```shell script
SERVER_PORT=8701 \
REGISTER_HOST=localhost \
java -jar lion-demo-consumer-x.x.x.jar
```

### docker
```shell script
docker pull micyo202/lion-demo-consumer:tagname
```
```shell script
docker run -d \
-p 8701:8701 \
-e REGISTER_HOST=localhost \
micyo202/lion-demo-consumer:tagname
```