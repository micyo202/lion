# lion-config-server（8300）

配置中心服务，使用本地native或git两种方式，仅需修改application.yml文件配置即可进行两种方式切换。
采用spring-cloud-bus消息总线连接rabbitmq进行动态刷新配置（动态刷新配置仅在git方式时生效）。

### 动态刷新配置方式
使用注解：@RefreshScope

- Webhook刷新（单个应用刷新，不推荐）

curl -X POST http://localhost:8781/actuator/refresh

- Spring Cloud Bus刷新（整个工程刷新，推荐）

curl -X POST http://localhost:8781/actuator/bus-refresh

某些场景下（例如灰度发布），我们可能只想刷新部分微服务的配置，此时可通过/actuator/bus-refresh/{destination}端点的 destination 参数来定位要刷新的应用程序。
如：
- /actuator/bus-refresh/lion-demo-consumer:8701（刷新指定微服务）
- /actuator/bus-refresh/lion-demo-consumer:**（刷新该微服务所有实例）