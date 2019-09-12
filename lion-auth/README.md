# lion-auth（8888）

认证服务，该服务会调用 **lion-upms（8800）** 服务获取用户权限

#### 获取access_token请求（/oauth/token） 
- 请求所需参数：client_id、client_secret、scope、grant_type、username、password
```http request
http://localhost:8888/oauth/token?client_id=lion_client&client_secret=secret&scope=server&grant_type=password&username=admin&password=123456
```

#### 检查token是否有效请求（/oauth/check_token） 
- 请求所需参数：token
```http request
http://localhost:8888/oauth/check_token?token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```

#### 刷新token请求（/oauth/token） 
- 请求所需参数：grant_type、refresh_token、client_id、client_secret
```http request
http://localhost:8888/oauth/token?grant_type=refresh_token&refresh_token=fbde81ee-f419-42b1-1234-9191f1f95be9&client_id=lion_client&client_secret=secret
```

#### 获取当前用户权限信息
```http request
http://localhost:8888/user/principal?access_token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```

#### 销毁token
```http request
http://localhost:8888/user/revoke?access_token=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```