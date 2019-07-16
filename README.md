# Lion

<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/logo.png" alt="logo" title="logo">
</p>

[![Build Status](https://travis-ci.org/micyo202/lion.svg?branch=master)](https://travis-ci.org/micyo202/lion)
[![Codecov](https://codecov.io/gh/micyo202/lion/branch/master/graph/badge.svg)](https://codecov.io/gh/micyo202/lion)
[![Version](https://img.shields.io/badge/Version-1.0.0-orange.svg)](https://github.com/micyo202/lion)
[![Since](https://img.shields.io/badge/Since-2019-199EC4.svg)](https://github.com/micyo202/lion)
[![Java](https://img.shields.io/badge/Java-1.8-yellow.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Scala](https://img.shields.io/badge/Scala-2.11.12-D72B2A.svg)](https://www.scala-lang.org)
[![Gradle](https://img.shields.io/badge/Gradle-5.3.1-01BC7E.svg)](https://gradle.org)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.1.2.RELEASE-FF69B4.svg)](https://spring.io/projects/spring-boot/)
[![Spring Cloud](https://img.shields.io/badge/SpringCloud-Greenwich.RELEASE-5DBF3D.svg)](https://spring.io/projects/spring-cloud)
[![License MIT](https://img.shields.io/badge/License-MIT-lightgrey.svg)](https://github.com/micyo202/lion/blob/master/LICENSE)
[![GitHub Stars](https://img.shields.io/github/stars/micyo202/lion.svg?style=social&label=Stars)](https://github.com/micyo202/lion)
[![GitHub Forks](https://img.shields.io/github/forks/micyo202/lion.svg?style=social&label=Fork)](https://github.com/micyo202/lion)

**注：该项目是基于SpringCloud微服务架构的，若要使用基于Dubbo的RPC架构项目请查看本人yan项目，前往地址：[https://github.com/micyo202/yan](https://github.com/micyo202/yan)，项目采用[Nacos v1.0.0](https://nacos.io)作为服务注册/发现、配置中心（若要查看Eureka版，请切换分支到[Eureka](https://github.com/micyo202/lion/tree/eureka)）**

---

本项目是使用**Gradle**构建，基于**SpringBoot 2.1.2.RELEASE、SpringCloud Greenwich.RELEASE**体系实现的一套完整微服务架构，采用**Oauth2**统一授权认证，支持**Java**、**Scala**混编、**Docker**容器化部署、**限流**、**灰度发布**等，规划将包含**大数据**、**区块链**等相关模块。

利用**Spring Boot Admin**来监控各个独立Service的运行状态，利用**Turbine**来查看近实时的接口运行状态和调用频率，利用**Zipkin**进行查看链路跟踪等。

基于**Nacos**来实现的服务注册与调用，在SpringCloud中使用**Feign**, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了**Hystrix**的作为熔断器，避免了服务之间的“雪崩”效应。

项目整合了 **spring-boot 2.1.2 + jpa + mybatis + pagehelper**框架。

项目使用**Travis CI**进行持续性**CI**，保证了最新提交代码的**build passing**，使用**Codecov**进行自动化测试代码的覆盖率。

项目后期会不断更新与时俱进，敬请期待...

## 近期更新
**2019-06-30：添加HDFS相关操作API**

**2019-05-15：添加分布式锁，在需要上锁的方法上使用注解@Locker即可，该分布式锁是基于Redisson实现的，请参考：[https://github.com/redisson/redisson/wiki](https://github.com/redisson/redisson/wiki)**

**2019-05-05：添加双buffer分布式自增ID算法服务[lion-id](https://github.com/micyo202/lion/tree/master/lion-id)，支持高并发。设计思路来自：[一线大厂的分布式唯一ID生成方案是什么样的？](https://blog.csdn.net/bntX2jSQfEHy7/article/details/89530118)**

## 项目架构图

<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/frame.png" alt="frame" title="frame">
</p>

## 说明
 
网上有关SpringCloud的教程很多，相关的项目也很多，但很少有完整的项目，即便有也是基于1.x的版本，在这个技术迭代更新发展速度飞快的时代，这样的项目不利于实际开发和落地。因此**lion**诞生了，它是一套基于2.x的完整微服务体系架构，几乎包含了微服务所有常用组件，为了让中小型公司解决当下技术瓶颈，快速将现有技术架构拆分改造为微服务体系架构，只需在本框架上进行相关业务开发即可，大大减少了微服务架构的门槛，达到拿来就用，使架构师及开发人员不用过多的关注架构本身，只需专注业务开发即可。

## 引言

为了帮助初学者快速理解入门微服务，这里简单介绍一下微服务的基本概念及常用组件说明，希望能给初学者带来帮助，如有解释不当的地方还望及时指出，谢谢！（**对微服务相关知识已有了解的可直接跳过引言部分**）

### 系统架构设计
要知道什么是微服务架构，你得先知道什么系统架构设计。

系统架构设计描述了在应用系统的内部，如何根据业务、技术、组织、灵活性、可扩展性以及可维护性等多种因素，将应用系统划分成不同的部分，并使这些部分彼此之间相互分工、相互协作，从而为用户提供某种特定的价值方式。

### 什么是微服务？
微服务是一个概念、一个项目开发的架构思想。

### 微服务架构
微服务架构是一种架构模式，它提倡将单一应用程序划分成一组小的服务，服务之间互相协调、互相配合，为用户提供最终价值。每个服务运行在其独立的进程中，服务于服务间采用轻量级的通信机制互相沟通（通常是基于 HTTP 的 RESTful API）。每个服务都围绕着具体业务进行构建，并且能够被独立地部署到生产环境、类生产环境等。另外，应尽量避免统一的、集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言、工具对其进行构建。

### 微服务架构优势？
复杂度可控、独立部署、轻量级通讯、技术选型灵活、容错、扩展。

### SOA与微服务架构区别
SOA实现 | 微服务架构实现
--- | ---
企业级，自顶向下开展实施 | 团队级，自底向上开展实施
服务由多个子系统组成，粒度大 | 一个系统被拆分成多个服务，粒度细
企业服务总线，几种式的服务架构 | 无集中式总线，松散的服务架构
集成方式复制（ESB/WS/SOAP） | 集成方便简单（HTTP/REST/JSON）
单块架构系统，相互依赖，部署复杂 | 服务能独立部署

### Dubbo VS Spring Cloud
核心要素 | Dubbo | Spring Cloud
--- | --- | ---
服务注册中心 | Zookeeper、Redis | Spring Cloud Netflix Eureka、Consul
服务调度方式 | RPC | REST API
服务网关 | 无 | Spring Cloud Netflix Zuul
断路器 | 不完善 | Spring Cloud Netflix Hystrix
分布式配置 | 无 | Spring Cloud Config
分布式追踪系统 | 无 | Spring Cloud Sleuth
消息总线 | 无 | Spring Cloud Bus
数据流 | 无 | Spring Cloud Stream（基于Redis、RabbitMQ、Kafka实现的消息微服务）
批量任务 | 无 | Spring Cloud Task

### 为什么选用Spring Cloud而不是Dubbo？
Dubbo只是实现了服务治理，而Spring Cloud子项目分别覆盖了微服务架构下的众多部件，而服务治理只是其中的一个方面。
Dubbo提供了各种Filter，可以通过扩展Filter来完善。
我们必须采用与一站式时代、泛 SOA 时代不同的技术栈，而 Spring Cloud 就是其中的佼佼者。

从核心要素来看，Spring Cloud 更胜一筹，在开发过程中只要整合Spring Cloud的子项目就可以顺利的完成各种组件的融合，而Dubbo却需要通过实现各种Filter来做定制，开发成本略高。

### 什么是Spring Cloud？
Spring Cloud 是一系列框架的有序集合。
它利用 Spring Boot 的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用 Spring Boot 的开发风格做到一键启动和部署。
Spring 并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过 Spring Boot 风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。

### 常用组件说明
#### Ribbon
Ribbon主要功能是为 REST 客户端实现负载均衡。

工作时候做4件事：

- 优先选择在同一个 Zone 且负载较少的注册/发现服务；
- 定期从注册/发现中心更新并过滤服务实例列表；
- 根据用户指定的策略，在从 Server 取到的服务注册列表中选择一个实例的地址；
- 通过 RestClient 进行服务调用。

#### Hystrix
Hystrix 库, 实现了断路器的模式。“断路器” 本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

注解方式：
@EnableHystrix
相当于
@EnableCircuitBreaker

#### Feign
Feign 是一个声明式的 Web Service 客户端，它的目的就是让 Web Service 调用更加简单。它整合了 Ribbon 和 Hystrix，从而让我们不再需要显式地使用这两个组件。Feign 还提供了 HTTP 请求的模板，通过编写简单的接口和插入注解，我们就可以定义好 HTTP 请求的参数、格式、地址等信息。接下来，Feign 会完全代理 HTTP 的请求，我们只需要像调用方法一样调用它就可以完成服务请求。

Feign 具有如下特性：

- 可插拔的注解支持，包括 Feign 注解和 JAX-RS 注解
- 支持可插拔的 HTTP 编码器和解码器
- 支持 Hystrix 和它的 Fallback
- 支持 Ribbon 的负载均衡
- 支持 HTTP 请求和响应的压缩

#### Zuul
Zuul路由是微服务架构的不可或缺的一部分，提供动态路由、监控、弹性、安全等的边缘服务。Zuul 是 Netflix 出品的一个基于 JVM 路由和服务端的负载均衡器。

#### Spring Cloud Config
在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件。在Spring Cloud中，有分布式配置中心组件spring cloud config ，它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。在spring cloud config 组件中，分两个角色，一是config server，二是config client

#### Spring Cloud Bus
Spring Cloud Bus 通过轻量消息代理连接各个分布的节点。这会用在广播状态的变化（例如配置变化）或者其他的消息指令。Spring Bus 的一个核心思想是通过分布式的启动器对 Spring Boot 应用进行扩展，也可以用来建立一个多个应用之间的通信频道。目前唯一实现的方式是用 Amqp 消息代理作为通道。
Spring Cloud Bus 被国内很多都翻译为消息总线，也挺形象的。大家可以将它理解为管理和传播所有分布式项目中的消息既可，其实本质是利用了 MQ 的广播机制在分布式的系统中传播消息，目前常用的有 Kafka 和 RabbitMQ。利用 Bus 的机制可以做很多的事情，其中配置中心客户端刷新就是典型的应用场景之一。

## 一、项目开发环境&工具
- MacOS Mojave / Windows 10
- CentOS 7
- JDK 1.8
- Scala 2.11.12
- IntelliJ IDEA 2018.3 / Eclipse 2018-12

## 二、相关软件
名称 | 链接 | 使用
--- | --- | :-:
MySql 8.0.13 | [https://www.mysql.com](https://www.mysql.com) | √
MongoDB 4.0.4 | [http://www.mongodb.org](http://www.mongodb.org) | ×
Redis 5.0.2 | [https://redis.io](https://redis.io) | √
Elasticsearch 6.5.2 | [https://www.elastic.co/cn/](https://www.elastic.co/cn/) | √
Kibana 6.5.2 | [https://www.elastic.co/cn/](https://www.elastic.co/cn/) | √
Logstash 6.5.2 | [https://www.elastic.co/cn/](https://www.elastic.co/cn/) | √
Solr 7.5.0 | [http://lucene.apache.org/solr/](http://lucene.apache.org/solr/) | ×
RabbitMQ 3.7.9 | [https://www.rabbitmq.com](https://www.rabbitmq.com) | √
Zookeeper 3.4.13 | [https://zookeeper.apache.org](https://zookeeper.apache.org) | √
Kafka 2.1.0 | [http://kafka.apache.org](http://kafka.apache.org) | ×
Hadoop 3.1.1 | [http://hadoop.apache.org](http://hadoop.apache.org) | √
Hbase 2.1.1 | [https://hbase.apache.org](https://hbase.apache.org) | √
Hive 3.1.1 | [http://hive.apache.org](http://hive.apache.org) | ×
Spark 2.4.0 | [http://spark.apache.org](http://spark.apache.org) | √
Gradle 5.3.1 | [https://gradle.org](https://gradle.org) | √

## 三、组件说明
- 服务注册/发现、配置中心: nacos
- 服务监控：spring boot admin
- 消息总线：spring cloud bus -> amqp
- 负载均衡：feign / ribbon
- 断路器: hystrix
- 路由网关：gateway / zuul
- 集群监控：hystrix dashboard -> turbine
- 链路追踪：spring cloud sletuh -> zipkin
- 安全认证：spring security -> oauth2
- ORM框架：mybatis + jpa 
- 数据源监控：druid
- api文档输出：swagger2
- 分布式锁：redis
- 消息队列：rabbitmq
- 分布式事物：3PC+TCC（待实现 Fescar[Seata]）

## 四、项目树结构
```lua
lion -- 根目录
├── lion-admin-server -- 服务监控
├── lion-zuul-server -- 路由服务
├── lion-zipkin-server -- 链路追踪服务
├── lion-turbine-server -- 服务调用实时监控
├── lion-common -- 通用工具类模块
├── lion-upms -- 用户权限管理系统
├── lion-auth -- 安全认证服务器
├── lion-id -- 自增ID生成服务
├── lion-bigdata -- 大数据模块
├── lion-blockchain -- 区块链模块
├── lion-demo -- 示例代码模块
|    ├── lion-demo-provider -- 服务提供者
|    ├── lion-demo-consumer -- 服务消费者
|    ├── lion-demo-ribbon -- ribbon + hystrix示例模块
|    ├── lion-demo-sample -- 综合案例包含灰度、权限认证、scala混编等
```

## 五、项目准备
- 在运行该项目前，请确保先正常启动：[Nacos v1.0.0](https://nacos.io)、[RabbitMQ 3.7.9](https://www.rabbitmq.com)、[MySql 8.0.13](https://www.mysql.com)、[Redis 5.0.2](https://redis.io)这4个必备服务，否则项目无法运行

- 建议使用[IntelliJ IDEA](https://www.jetbrains.com/idea/)作为IDE开发工具（注：该工具需要购买，激活步骤详情可参考我个人简书上的方法，请移步至[https://www.jianshu.com/p/3c87487e7121](https://www.jianshu.com/p/3c87487e7121)）

## 六、项目部署
1.下载项目，并且导入到IDE开发工具中

2.使用 Gradle 构建项目

3.创建2个数据库分别为 lion、zipkin 并分别执行根目录 database 中的 lion.sql、zipkin.sql 文件，创建整个项目必要的表（如：用户表、权限表、菜单资源表等...）

4.根据自己服务情况，修改项目中 resources/bootstrap.yml 配置下 nacos 的服务地址

5.将项目中的所有 resources/application.yml 配置文件注释放开（或使用nacos配置中心进行管理），并修改其datasource druid数据库连接信息

6.修改项目的 resources/log4j2.yml 配置文件中的 -log.path value 日志输出路径

7.完成以上步骤就可以正常部署启动服务了

8.项目开发详细代码，可参考lion-demo下的示例模块

## 七、服务启动
注：带~~删除线~~的服务为相关示例模块可根据需要选择启动
- lion-admin-server（端口：8200）
- lion-zuul-server（端口：8400）
- lion-zipkin-server（端口：9411）
- lion-turbine-server（端口：8500）[最后启动]
- lion-upms（端口：8800）
- lion-auth（端口：8888）
- lion-id（端口：8899）
- ~~lion-bigdata（端口：8801）~~
- ~~lion-blockchain （端口：8802）~~
- ~~lion-demo（相关demo示例）~~
    - ~~lion-demo-provider（端口：8601、8602、8603...）~~
    - ~~lion-demo-consumer（端口：8701、8702、8703...）~~
    - ~~lion-demo-ribbon（端口：8781）~~
    - ~~lion-demo-sample（端口：8782）~~

## 八、效果预览

#### 服务注册/发现、配置中心
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/nacos-config.png" alt="nacos-config" title="nacos-config">
</p>

#### 服务详情
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/nacos-details.png" alt="nacos-details" title="nacos-details">
</p>

#### 服务监控
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/admin-wallboard.png" alt="admin-wallboard" title="admin-wallboard">
</p>

#### 服务监控详情
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/admin-details.png" alt="admin-details" title="admin-details">
</p>

#### 链路追踪服务
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/zipkin.png" alt="zipkin" title="zipkin">
</p>

#### 服务实时调用情况
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/turbine.png" alt="turbine" title="turbine">
</p>

#### 数据源监控
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/druid.png" alt="druid" title="druid">
</p>

#### API 文档
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/swagger.png" alt="swagger" title="swagger">
</p>

## 九、许可证
[MIT License](https://github.com/micyo202/lion/blob/master/LICENSE)