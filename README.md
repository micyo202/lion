# Lion

<p align="center" >
  <img src="https://github.com/micyo202/lion/raw/master/images/lion-logo.png" alt="lion-logo" title="lion-logo">
</p>

[![Build Status](https://travis-ci.org/micyo202/lion.svg?branch=master)](https://travis-ci.org/micyo202/lion)
[![Codecov](https://codecov.io/gh/micyo202/lion/branch/master/graph/badge.svg)](https://codecov.io/gh/micyo202/lion)
[![Version](https://img.shields.io/badge/Version-2.0.3-blue.svg)](https://github.com/micyo202/lion)
[![Java](https://img.shields.io/badge/Java-8+-yellow.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Gradle](https://img.shields.io/badge/Gradle-6.3-01BC7E.svg)](https://gradle.org)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-2.2.6.RELEASE-FF69B4.svg)](https://spring.io/projects/spring-boot/)
[![Spring Cloud](https://img.shields.io/badge/SpringCloud-Hoxton.SR2-5DBF3D.svg)](https://spring.io/projects/spring-cloud)
[![License MIT](https://img.shields.io/badge/License-MIT-lightgrey.svg)](https://github.com/micyo202/lion/blob/master/LICENSE)
[![GitHub Stars](https://img.shields.io/github/stars/micyo202/lion.svg?style=social&label=Stars)](https://github.com/micyo202/lion)
[![GitHub Forks](https://img.shields.io/github/forks/micyo202/lion.svg?style=social&label=Fork)](https://github.com/micyo202/lion)

<div style="text-align: center;">
    <table cellspacing="0" cellpadding="0">
        <tr>
            <td align="center" width="36%"><img src="https://raw.githubusercontent.com/micyo202/lion/master/images/jetbrains-logo.png" width="180" /></td>
            <td>感谢<a href="https://www.jetbrains.com/?from=lion">JetBrains</a>提供的开源许可，推荐使用<a href="https://www.jetbrains.com/idea?from=lion">IntelliJ IDEA</a>进行开发</td>
        </tr>
    </table>
</div>

---

#### 项目镜像已经推送至[Docker Hub](https://hub.docker.com)，请前往[https://hub.docker.com/u/micyo202](https://hub.docker.com/u/micyo202)查看/拉取

**lion**是基于**Spring Cloud**体系实现的一套分布式微服务架构，为了让中小型公司解决当下技术瓶颈，快速将现有应用服务架构拆分改造为分布式微服务架构，只需在本架构上进行相关业务开发即可，大大减少了分布式微服务架构的门槛，达到拿来就用，使架构师及开发人员不必过多的关注架构本身，只需专注于业务开发

项目采用**Gradle**构建，基于**Java 8+、SpringBoot 2.2.6.RELEASE、SpringCloud Hoxton.SR2、Spring Cloud Alibaba 2.2.0.RELEASE、MyBatis Plus 3.3.1**等核心技术体系实现的一套分布式微服务架构，包含**OAuth2/JWT**权限认证、支持**Docker**容器化部署、镜像交付、限流、灰度等

使用**Nacos**作为服务注册/发现、配置中心

使用**Sentinel**来查看近实时的接口运行状态和调用频率，并用作服务限流、熔断降级等处理，避免了分布式服务之间调用的“雪崩”效应

使用**Seata**作为分布式事务管理，采用AT事务模式自动完成两阶段提交/回滚

使用**Spring Boot Admin**来监控各个独立Service的运行状态

使用**Spring Cloud Gateway**作为路由网关服务

使用**Zipkin / SkyWalking**进行查看完整链路追踪信息等

使用**Feign**, 做到HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求

项目后期会不断更新与时俱进，敬请期待...

## 项目架构图

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/lion-frame.png" alt="lion-frame" title="lion-frame">
</p>

## 数据库表关系图

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/lion-database.png" alt="lion-database" title="lion-database">
</p>

## 分支说明

- [master分支](https://github.com/micyo202/lion)，基于Java 8+重构升级优化后的最新主线版本（推荐）
- [release1.x分支](https://github.com/micyo202/lion/tree/release1.x)，基于Java 8、SpringBoot 2.1.2.RELEASE、SpringCloud Greenwich.RELEASE、Spring Cloud Alibaba 2.1.0.RELEASE体系实现，支持Java、Scala混编，的最终版
- [eureka分支](https://github.com/micyo202/lion/tree/eureka)，使用 Eureka 作为服务注册发现中心（Eureka官宣2.x版本不再开源，项目使用Nacos）
- [hystrix分支](https://github.com/micyo202/lion/tree/hystrix)，使用 Hystrix 做为服务断路器（Hystix官宣停止更新，项目使用Sentinel）
- [zuul分支](https://github.com/micyo202/lion/tree/zuul)，使用 Zuul 做为路由网关（由于Zuul 2.x的不断跳票，SpringCloud后续没有整合Zuul 2.x的计划，项目使用Spring Cloud Gateway）

## 一、项目开发环境&工具

- MacOS / Windows
- CentOS
- Java 8+
- IntelliJ IDEA / Eclipse

## 二、相关软件

*说明：“√”表示必要服务，“X”表示非必要服务*

名称 | 链接 | 必须 
--- | --- | :-:
MySql 8.0.19 | [https://www.mysql.com](https://www.mysql.com) | √ 
ElasticSearch 7.6.1 | [https://www.elastic.co/cn/](https://www.elastic.co/cn/) | x 
Redis 5.0.7 | [https://redis.io](https://redis.io) | √
RabbitMQ 3.8.2 | [https://www.rabbitmq.com](https://www.rabbitmq.com) | √
Nacos 1.1.4 | [https://nacos.io](https://nacos.io) | √ 
Sentinel 1.7.1 | [https://github.com/alibaba/Sentinel](https://github.com/alibaba/Sentinel) | √ 
Zipkin 2.20 | [https://zipkin.io](https://zipkin.io) | x 
SkyWalking 6.6.0 | [http://skywalking.apache.org](http://skywalking.apache.org) | x 
Seata 1.0.0 | [https://seata.io](https://seata.io) | √ 

注：在启动项目前，请先确保启动：[MySql 8.0.19](https://www.mysql.com)、[Redis 5.0.7](https://redis.io)、[RabbitMQ 3.8.2](https://www.rabbitmq.com)、[Nacos 1.1.4](https://nacos.io)、[Sentinel 1.7.1](https://github.com/alibaba/Sentinel)、[Seata 1.0.0](https://seata.io)这**6**个必备服务（需把**Sentinel**默认端口**8080**改为**8858**）

## 三、组件说明

- 服务注册/发现、配置中心：Nacos
- 服务监控：Spring Boot Admin
- 消息队列：AMQP -> RabbitMQ
- 负载均衡：Feign / Ribbon
- 限流、熔断降级：Sentinel
- 路由网关：Spring Cloud Gateway
- 链路追踪：Spring Cloud Sletuh -> Zipkin / SkyWalking
- 权限认证：Spring Security -> OAuth2 / JWT
- ORM框架：MyBatis（MyBatis-Plus）
- 数据源监控：Druid
- RESTful APIs文档：Knife4j
- 分布式锁：Redisson
- 分布式事务：Seata

## 四、项目结构

```lua
lion -- 根目录
├── lion-admin -- 服务监控
├── lion-gateway -- 网关服务
├── lion-common -- 通用工具类
├── lion-auth -- 安全认证服务
├── lion-demo -- 示例模块
|    ├── lion-demo-provider -- 服务提供者
|    ├── lion-demo-consumer -- 服务消费者
```

## 五、项目部署

1、下载项目`git clone https://github.com/micyo202/lion.git`

2、进入项目根目录执行`./gradlew -x test clean`命令，使用**Gradle**初始化项目

3、初始化完毕后导入到**IDE**开发工具中（建议使用[IntelliJ IDEA](https://www.jetbrains.com/idea?from=lion)作为开发工具

4、创建**3**个数据库分别为**lion、seata、zipkin**并分别执行项目根目录下**database**中的**[lion.sql](https://github.com/micyo202/lion/blob/master/database/lion.sql)、[seata.sql](https://github.com/micyo202/lion/blob/master/database/seata.sql)、[zipkin.sql](https://github.com/micyo202/lion/blob/master/database/zipkin.sql)**脚本，该脚本会创建项目所需的表（lion库中包含：用户表、角色表、菜单资源表等，seata库中包含：全局事务表、分支事务表、全局锁表，zipkin库中包含：链路追踪相关表）

5、参考文档中[二、相关软件](#二、相关软件)的内容，启动**6**个必备服务，否则项目无法正常运行

6、根据自己的服务器情况，修改**resources**下**bootstrap.yml**配置中的**nacos**服务地址，及**application.yml**配置中**mysql、redis、rabbitmq、sentinel**的服务地址跟用户名/密码（*注：可将**application.yml**配置文件注释打开放在项目中，或将**application.yml**配置文件放入**nacos**配置管理中*）

7、**Windows**环境需修改**resources**下**log4j2.yml**配置中`-log.path`的`value`日志输出路径（*注：**Mac、Linux、Ubuntu**环境可忽略此步骤，默认日志输出路径在`/tmp/logs`下*）

8、完成以上步骤就可以正常启动部署服务了

9、项目开发详细示例代码，可参考**lion-demo**示例模块

10、测试方法使用[postman](https://www.getpostman.com)导入项目根目录下**json**中的[postman.json](https://github.com/micyo202/lion/raw/master/json/postman.json)脚本即可

## 六、端口使用

- Nacos（端口：8848）
- Sentinel（端口：8858）
- Seata（端口：8091）
- Zipkin（端口：9411）
- SkyWalking（端口：8900）

- lion-admin（端口：8200）
- lion-gateway（端口：8400）
- lion-auth（端口：8888）
- lion-demo
    - lion-demo-provider（端口：8601、8602、8603...）
    - lion-demo-consumer（端口：8701、8702、8703...）

## 七、效果预览

#### Nacos服务列表

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/nacos-server.png" alt="nacos-server" title="nacos-server">
</p>

#### Nacos配置列表

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/nacos-config.png" alt="nacos-config" title="nacos-config">
</p>

#### Nacos服务详情

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/nacos-detail.png" alt="nacos-detail" title="nacos-detail">
</p>

#### Boot Admin应用监控

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/admin-wallboard.png" alt="admin-wallboard" title="admin-wallboard">
</p>

#### Boot Admin应用列表

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/admin-application.png" alt="admin-application" title="admin-application">
</p>

#### Boot Admin应用详情

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/admin-detail.png" alt="admin-detail" title="admin-detail">
</p>

#### Sentinel服务限流、熔断降级

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/sentinel.png" alt="sentinel" title="sentinel">
</p>

#### Zipkin链路信息

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/zipkin-info.png" alt="zipkin-info" title="zipkin-info">
</p>

#### Zipkin链路追踪

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/zipkin-trace.png" alt="zipkin-trace" title="zipkin-trace">
</p>

#### Zipkin拓扑图

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/zipkin-dependencies.png" alt="zipkin-dependencies" title="zipkin-dependencies">
</p>

### SkyWalking监控面板

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/skywalking-dashboard.png" alt="skywalking-dashboard" title="skywalking-dashboard">
</p>

### SkyWalking链路追踪

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/skywalking-trace.png" alt="skywalking-trace" title="skywalking-trace">
</p>

### SkyWalking拓扑图

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/skywalking-dependencies.png" alt="skywalking-dependencies" title="skywalking-dependencies">
</p>
<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/skywalking-dependencies-link.png" alt="skywalking-dependencies-link" title="skywalking-dependencies-link">
</p>

#### Druid SQL监控

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/druid-sql.png" alt="druid-sql" title="druid-sql">
</p>

#### Druid URI监控

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/druid-uri.png" alt="druid-uri" title="druid-uri">
</p>

#### Druid Spring监控

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/druid-spring.png" alt="druid-spring" title="druid-spring">
</p>

#### RESTful APIs文档

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/knife4j-home.png" alt="knife4j-home" title="knife4j-home">
</p>

<p align="center" >
  <img src="https://raw.githubusercontent.com/micyo202/lion/master/images/knife4j-apis.png" alt="knife4j-apis" title="knife4j-apis">
</p>

## 八、开源协议

[MIT License](https://github.com/micyo202/lion/blob/master/LICENSE)（[英文原文](https://opensource.org/licenses/mit-license.php)）MIT内容与三条款BSD许可证（3-clause BSD license）内容颇为近似，但是赋予软体被授权人更大的权利与更少的限制

- 被授权人有权利使用、复制、修改、合并、出版发行、散布、再授权及贩售软体及软体的副本
- 被授权人可根据程式的需要修改授权条款为适当的内容
- 在软件和软件的所有副本中都必须包含版权声明和许可声明
- 此授权条款并非属copyleft的自由软体授权条款，允许在自由/开放源码软体或非自由软体（proprietary software）所使用
- 此亦为MIT与BSD（The BSD license, 3-clause BSD license）本质上不同处
- MIT条款可与其他授权条款并存。另外，MIT条款也是自由软体基金会（FSF）所认可的自由软体授权条款，与GPL相容