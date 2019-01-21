# lion-zipkin-server（9411）

zipkin 的服务端，在使用 Spring Boot 2.x 版本后，官方就不推荐自行定制编译了，反而是直接提供了编译好的 jar 包来给我们使用。
本项目使用的是自建Zipkin Server，已经配置好了相关存储方式。

### 下载地址：
[https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/](https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/)

### 下载后执行：    
    java -jar zipkin-server-2.11.12-exec.jar

### 使用docker方式：
    docker run -d -p 9411:9411 openzipkin/zipkin

### 启动时也可指定相关参数，详细参数参数查看：
[https://github.com/openzipkin/zipkin/blob/master/zipkin-server/src/main/resources/zipkin-server-shared.yml](https://github.com/openzipkin/zipkin/blob/master/zipkin-server/src/main/resources/zipkin-server-shared.yml)