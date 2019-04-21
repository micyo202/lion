#!/bin/bash
# 用shell脚本启动docker容器

echo "容器创建开始"

# lion-eureka-server docker 启动命令：
docker run -d -e "SPRING_PROFILES_ACTIVE=dev" --name lion-eureka-server -p 8101:8101 -it com.lion.eureka.server/lion-eureka-server

# lion-admin-server docer 启动命令
docker run -d -e "SPRING_PROFILES_ACTIVE=dev" --name lion-admin-server -p 8200:8200 -it com.lion.admin.server/lion-admin-server

# lion-config-server docer 启动命令
docker run -d -e "SPRING_PROFILES_ACTIVE=dev" --name lion-config-server -p 8300:8300 -it com.lion.config.server/lion-config-server

echo "容器创建完成"