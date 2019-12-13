- **2019-12-10**：优化项目结构，优化OAuth2认证模块，丰富lion-common公共模块，优化lion-demo示例模块

- **2019-11-21**：添加通用base基类，包含：BaseController、BaseService、BaseMapper、BaseRepository

- **2019-10-26**：添加通用Mapper简化代码，添加安全工具类，添加分页对象处理
__
- **2019-10-12**：添加 RabbitMQ 消息生产者、消费者

- **2019-09-30**：移除 Zuul 路由模块，采用 [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) 作为最新路由网关服务，添加缓存策略

- **2019-09-06**：更新 Zuul 路由模块，移除不必要的代码，添加 token 检查机制，优化模块代码

- **2019-09-03**：更新 [Nacos](https://nacos.io)，移除 [Hystrix](https://github.com/Netflix/Hystrix)，采用 [Sentinel](https://github.com/alibaba/Sentinel) 进行流量监控、服务熔断降级

- **2019-08-09**：添加定时任务功能，仅需在配置表 sys_schedule 中配置相关任务Bean方法，即可按配置的 cron 来执行

- **2019-06-30**：添加HDFS相关操作API

- **2019-05-15**：添加分布式锁，在需要上锁的方法上使用注解@Locker即可，该分布式锁是基于Redisson实现的，请参考：[https://github.com/redisson/redisson/wiki](https://github.com/redisson/redisson/wiki)

- **2019-05-05**：添加双buffer分布式自增ID生成服务[lion-id](https://github.com/micyo202/lion/tree/master/lion-id)，支持高并发。设计思路来自：[一线大厂的分布式唯一ID生成方案是什么样的？](https://blog.csdn.net/bntX2jSQfEHy7/article/details/89530118)