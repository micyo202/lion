#lion-gateway（8400）

基于[Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)的路由网关服务，其主要功能是路由转发和过滤器

路由网关功能是微服务的一部分，如/demo/a转发到到a-server服务，/demo/b转发到到b-server服务

### jar
```shell script
SERVER_PORT=8400 \
REGISTER_HOST=localhost \
java -jar lion-gateway-x.x.x.jar
```

### docker
```shell script
docker pull micyo202/lion-gateway:tagname
```
```shell script
docker run -d \
-p 8400:8400 \
-e REGISTER_HOST=localhost \
micyo202/lion-gateway:tagname
```