#!/bin/bash
# 用shell脚本启动docker容器

echo "容器创建开始"

# lion-admin-server docker 启动命令
docker run -d -e "SPRING_PROFILES_ACTIVE=dev" --name lion-admin-server -p 8200:8200 -it com.lion.admin.server/lion-admin-server

# lion-upms docker 启动命令
docker run -d -e "SPRING_PROFILES_ACTIVE=dev" --name lion-upms -p 8800:8800 -it com.lion.upms/lion-upms

# lion-auth docker 启动命令
docker run -d -e "SPRING_PROFILES_ACTIVE=dev" --name lion-auth -p 8888:8888 -it com.lion.auth/lion-auth

# lion-gateway-server docker 启动命令
docker run -d -e "SPRING_PROFILES_ACTIVE=dev" --name lion-gateway-server -p 8400:8400 -it com.lion.gateway.server/lion-gateway-server

echo "容器创建完成"