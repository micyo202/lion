# lion-admin（8200）

监控管理服务，用于查看各服务详细运行状态

### jar
```shell script
SERVER_PORT=8200 \
REGISTER_HOST= localhost \
USERNAME=admin \
PASSWORD=admin \
java -jar lion-admin.jar
```

### docker
```shell script
docker pull micyo202/lion-admin
```
```shell script
docker run -d \
-p 8200:8200 \
-e REGISTER_HOST=localhost \
-e USERNAME=admin \
-e PASSWORD=admin \
micyo202/lion-admin
```

### docker-compose
```yaml
version: "3"
services:
  lion-admin:
    image: micyo202/lion-admin
    ports:
      - "8200:8200"
    environment:
      REGISTER_HOST: localhost
      USERNAME: admin
      PASSWORD: admin
```