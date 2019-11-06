
# Lion

<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/logo.png" alt="logo" title="logo">
</p>

[![Build Status](https://travis-ci.org/micyo202/lion.svg?branch=master)](https://travis-ci.org/micyo202/lion)
[![Codecov](https://codecov.io/gh/micyo202/lion/branch/master/graph/badge.svg)](https://codecov.io/gh/micyo202/lion)
[![Version](https://img.shields.io/badge/Version-1.5.2-orange.svg)](https://github.com/micyo202/lion)
[![Since](https://img.shields.io/badge/Since-2019-199EC4.svg)](https://github.com/micyo202/lion)
[![Java](https://img.shields.io/badge/Java-1.8-yellow.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Scala](https://img.shields.io/badge/Scala-2.11.12-D72B2A.svg)](https://www.scala-lang.org)
[![Gradle](https://img.shields.io/badge/Gradle-5.6.3-01BC7E.svg)](https://gradle.org)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.1.2.RELEASE-FF69B4.svg)](https://spring.io/projects/spring-boot/)
[![Spring Cloud](https://img.shields.io/badge/SpringCloud-Greenwich.RELEASE-5DBF3D.svg)](https://spring.io/projects/spring-cloud)
[![License MIT](https://img.shields.io/badge/License-MIT-lightgrey.svg)](https://github.com/micyo202/lion/blob/master/LICENSE)
[![GitHub Stars](https://img.shields.io/github/stars/micyo202/lion.svg?style=social&label=Stars)](https://github.com/micyo202/lion)
[![GitHub Forks](https://img.shields.io/github/forks/micyo202/lion.svg?style=social&label=Fork)](https://github.com/micyo202/lion)

**注：该项目是基于SpringCloud微服务架构的，若要使用基于Dubbo的RPC架构项目请查看本人yan项目，前往地址：[https://github.com/micyo202/yan](https://github.com/micyo202/yan)**

- [master分支](https://github.com/micyo202/lion)，使用 [Nacos](https://nacos.io) 作为服务注册/发现、配置中心；使用[Sentinel](https://github.com/alibaba/Sentinel)作为流量监控、服务降级、熔断处理；使用[Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)作为路由网关。
- [eureka分支](https://github.com/micyo202/lion/tree/eureka)，使用 Eureka 作为服务注册发现中心。
- [hystrix分支](https://github.com/micyo202/lion/tree/hystrix)，使用 Hystrix 做为服务降级、熔断处理。
- [zuul分支](https://github.com/micyo202/lion/tree/zuul)，使用 Zuul 做为路由网关。

---

本项目是使用**Gradle**构建，基于**SpringBoot 2.1.2.RELEASE、SpringCloud Greenwich.RELEASE**体系实现的一套完整微服务架构，采用**OAuth2**统一授权认证，支持**Java**、**Scala**混编、**Docker**容器化部署、**限流**、**灰度**等，规划将包含**大数据**、**区块链**等相关模块。

利用**Spring Boot Admin**来监控各个独立Service的运行状态，利用**Sentinel**来查看近实时的接口运行状态和调用频率，利用**Zipkin**进行查看链路跟踪等。

基于**Nacos**来实现的服务注册与调用，在SpringCloud中使用**Feign**, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了**Sentinel**的作为熔断器，避免了服务之间的“雪崩”效应。

项目整合了 **spring-boot 2.1.2 + jpa + mybatis + pagehelper**框架。

项目使用**Travis CI**进行持续性**CI**，保证了最新提交代码的**build passing**，使用**Codecov**进行自动化测试代码的覆盖率。

项目后期会不断更新与时俱进，敬请期待...

[更新日志](https://github.com/micyo202/lion/blob/master/CHANGELOG.md)


## 项目架构图

<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/frame.png" alt="frame" title="frame">
</p>

## 数据库表关系图

<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/database.png" alt="database" title="database">
</p>

## 说明
 
网上有关SpringCloud的教程很多，相关的项目也很多，但很少有完整的项目，即便有也是基于1.x的版本，在这个技术迭代更新发展速度飞快的时代，这样的项目不利于实际开发和落地。因此**lion**诞生了，它是一套基于2.x的完整微服务体系架构，几乎包含了微服务所有常用组件，为了让中小型公司解决当下技术瓶颈，快速将现有技术架构拆分改造为微服务体系架构，只需在本框架上进行相关业务开发即可，大大减少了微服务架构的门槛，达到拿来就用，使架构师及开发人员不用过多的关注架构本身，只需专注业务开发即可。

若首次接触微服务或对微服务不熟悉请先查看本人整理的[微服务相关介绍](https://github.com/micyo202/lion/blob/master/INTRODUCTION.md)

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
Gradle 5.6.3 | [https://gradle.org](https://gradle.org) | √

## 三、组件说明
- 服务注册/发现、配置中心: nacos
- 服务监控：spring boot admin
- 消息队列：amqp -> rabbitmq
- 负载均衡：feign / ribbon
- 限流、熔断降级: sentinel
- 路由网关：gateway
- 链路追踪：spring cloud sletuh -> zipkin
- 安全认证：spring security -> oauth2
- ORM框架：mybatis + jpa
- 数据源监控：druid
- api文档输出：swagger2
- 分布式锁：redis
- 分布式事物：待实现

## 四、项目树结构
```lua
lion -- 根目录
├── lion-admin-server -- 服务监控
├── lion-gateway-server -- 路由服务
├── lion-zipkin-server -- 链路追踪服务
├── lion-common -- 通用工具类模块
├── lion-upms -- 用户权限管理系统
├── lion-auth -- 安全认证服务器
├── lion-id -- 自增ID生成服务
├── lion-bigdata -- 大数据模块
├── lion-blockchain -- 区块链模块
├── lion-demo -- 示例代码模块（包含灰度、权限认证、scala混编等）
|    ├── lion-demo-provider -- 服务提供者
|    ├── lion-demo-consumer -- 服务消费者
```

## 五、项目准备
- 在运行该项目前，请确保先正常启动：[Nacos 1.1.0](https://nacos.io)、[Sentinel 1.6.3](https://github.com/alibaba/Sentinel)、[RabbitMQ 3.7.9](https://www.rabbitmq.com)、[MySql 8.0.13](https://www.mysql.com)、[Redis 5.0.2](https://redis.io)这**5**个必备服务，否则项目无法运行

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

9.测试方法使用[postman](https://www.getpostman.com)导入[postman.json](https://github.com/micyo202/lion/blob/master/postman.json)脚本即可

## 七、服务启动
注：带~~删除线~~的服务为相关示例模块可根据需要选择启动
- lion-admin-server（端口：8200）
- lion-gateway-server（端口：8400）
- lion-zipkin-server（端口：9411）
- lion-upms（端口：8800）
- lion-auth（端口：8888）
- ~~lion-id（端口：8899）~~
- ~~lion-bigdata（端口：8801）~~
- ~~lion-blockchain （端口：8802）~~
- ~~lion-demo（相关demo示例）~~
    - ~~lion-demo-provider（端口：8601、8602、8603...）~~
    - ~~lion-demo-consumer（端口：8701、8702、8703...）~~

## 八、效果预览

#### Nacos服务列表
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/nacos-server.png" alt="nacos-server" title="nacos-server">
</p>

#### Nacos配置列表
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/nacos-config.png" alt="nacos-config" title="nacos-config">
</p>

#### Nacos服务详情
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/nacos-detail.png" alt="nacos-detail" title="nacos-detail">
</p>

#### Boot Admin应用监控
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/admin-wallboard.png" alt="admin-wallboard" title="admin-wallboard">
</p>

#### Boot Admin应用列表
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/admin-application.png" alt="admin-application" title="admin-application">
</p>

#### Boot Admin应用详情
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/admin-detail.png" alt="admin-detail" title="admin-detail">
</p>

#### Sentinel服务限流、熔断/降级
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/sentinel.png" alt="sentinel" title="sentinel">
</p>

#### Zipkin链路信息
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/zipkin-info.png" alt="zipkin-info" title="zipkin-info">
</p>

#### Zipkin链路关系
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/zipkin-relation.png" alt="zipkin-relation" title="zipkin-relation">
</p>

#### Druid SQL监控
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/druid-sql.png" alt="druid-sql" title="druid-sql">
</p>

#### Druid URI监控
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/druid-uri.png" alt="druid-uri" title="druid-uri">
</p>

#### Druid Spring监控
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/druid-spring.png" alt="druid-spring" title="druid-spring">
</p>

#### Swagger2 API文档
<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/swagger2-api.png" alt="swagger2-api" title="swagger2-api">
</p>

## 九、许可证
[MIT License](https://github.com/micyo202/lion/blob/master/LICENSE)