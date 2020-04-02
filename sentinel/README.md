# Sentinel（8858）

### 官网
[https://github.com/alibaba/Sentinel](https://github.com/alibaba/Sentinel)

### 本地部署

```shell script
java -Dserver.port=8858 \
-Dcsp.sentinel.dashboard.server=localhost:8858 \
-Dproject.name=sentinel-dashboard \
-jar sentinel-dashboard-1.7.1.jar
```

### Docker部署

```shell script
docker pull bladex/sentinel-dashboard:1.7.1
```

```shell script
docker run -d \
        -p 8858:8858 \
        --name sentinel \
        --restart always \
        bladex/sentinel-dashboard:1.7.1
```