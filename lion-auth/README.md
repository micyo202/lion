# lion-auth（8888）

本服务采用了mysql存储clientId验证信息，需创建oauth_client_details表，请参考
[OAuth2 Schema DDL](https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql)

### jar
```shell script
SERVER_PORT=8888 \
REGISTER_HOST=localhost \
java -jar lion-auth-x.x.x.jar
```

### docker
```shell script
docker pull micyo202/lion-auth:tagname
```
```shell script
docker run -d \
-p 8888:8888 \
-e REGISTER_HOST=localhost \
micyo202/lion-auth:tagname
```

#### 获取access_token请求（/oauth/token） 
请求所需参数：client_id、client_secret、scope、grant_type、username、password
```http request
http://localhost:8888/oauth/token?client_id=lion-client&client_secret=secret&scope=all&grant_type=password&username=admin&password=123456
```

#### 检查token是否有效请求（/oauth/check_token） 
请求所需参数：token
```http request
http://localhost:8888/oauth/check_token?token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```

#### 刷新token请求（/oauth/token） 
请求所需参数：grant_type、refresh_token、client_id、client_secret
```http request
http://localhost:8888/oauth/token?grant_type=refresh_token&refresh_token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx&client_id=lion-client&client_secret=secret
```

#### 获取凭证信息
```http request
http://localhost:8888/principal?access_token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```

#### 销毁凭证信息
```http request
http://localhost:8888/revoke?access_token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```