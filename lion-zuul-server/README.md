# lion-zuul-server（8400）

路由网管服务，zuul的主要功能是路由转发和过滤器。
路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。
zuul默认和Ribbon结合实现了负载均衡的功能。