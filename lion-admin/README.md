# lion-admin（8200）

监控管理服务，用于查看各服务详细运行状态

### jar
```shell script
SERVER_PORT=8200 \
REGISTER_HOST=localhost \
java -jar lion-admin-x.x.x.jar
```

### docker
```shell script
docker pull micyo202/lion-admin:tagname
```
```shell script
docker run -d \
-p 8200:8200 \
-e REGISTER_HOST=localhost \
micyo202/lion-admin:tagname
```