# nacos（8848）

### 官网
[https://nacos.io](https://nacos.io)

### 本地部署

```shell script
bin/startup.sh -m standalone
```

### Docker部署

```shell script
docker nacos/nacos-server:1.1.4
```

```shell script
docker run -d \
        -p 8848:8848 \
        --env MODE=standalone \
        --name nacos \ 
        --restart always \
        nacos/nacos-server:1.1.4
```