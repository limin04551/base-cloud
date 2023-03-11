## 平台简介



> _base-cloud_ `分布式微服务后台架构` 基于最新spring-cloud-alibaba微服务中间件与Spring-Boot3.0.x构建,最低要求为JDK17

## 架构图
<img src="https://i.328888.xyz/2023/03/10/snxrt.png">

## 功能规划介绍

|  完成情况  | 功能介绍     | 使用技术                     | 文档地址                                                                                               | 特性注意事项                            |
|:------:|----------|--------------------------|----------------------------------------------------------------------------------------------------|-----------------------------------|
|   ✅    | 后端开发框架   | SpringBoot3.0.4          | [SpringBoot官网](https://spring.io/projects/spring-boot/#learn)                                      |                                   |
|   ✅    | 微服务开发框架  | SpringCloud              | [SpringCloud官网](https://spring.io/projects/spring-cloud)                                           |                                   |
|   ✅    | 微服务开发框架  | SpringCloudAlibaba       | [SpringCloudAlibaba官网](https://spring.io/projects/spring-cloud-alibaba)                            |                                   |
|   ✅    | 容器框架     | Undertow                 | [Undertow官网](https://undertow.io/)                                                                 | 基于 XNIO 的高性能容器                    |
|        | 权限认证框架   | Sa-Token、Jwt             | [Sa-Token官网](https://sa-token.dev33.cn/)                                                           | 强解耦、强扩展                           |
|   ✅    | 关系数据库    | MySQL                    | [MySQL官网](https://dev.mysql.com/)                                                                  | 适配 8.X 最低 5.7                     |
|   ✅    | 关系数据库    | Oracle                   | [Oracle官网](https://www.oracle.com/cn/database/)                                                    | 适配 12c                            |
|   ❌    | 关系数据库    | PostgreSQL               | [PostgreSQL官网](https://www.postgresql.org/)                                                        | 适配 14                             |
|   ❌    | 缓存数据库    | Redis                    | [Redis官网](https://redis.io/)                                                                       | 适配 6.X 最低 5.X                     |
|   ✅    | 分布式注册中心  | Alibaba Nacos            | [Alibaba Nacos文档](https://nacos.io/zh-cn/docs/quick-start.html)                                    | 采用2.X 基于GRPC通信高性能                 |
|   ✅    | 分布式配置中心  | Alibaba Nacos            | [Alibaba Nacos文档](https://nacos.io/zh-cn/docs/quick-start.html)                                    | 采用2.X 基于GRPC通信高性能                 |
|   ✅    | 服务网关     | SpringCloud Gateway      | [SpringCloud Gateway文档](https://spring.io/projects/spring-cloud-gateway)                           | 响应式高性能网关                          |
|   ✅    | 负载均衡     | SpringCloud Loadbalancer | [SpringCloud Loadbalancer文档](https://spring.io/guides/gs/spring-cloud-loadbalancer/)               | 负载均衡处理                            |
|   ✅    | RPC远程调用  | Apache Dubbo             | [Apache Dubbo官网](https://dubbo.apache.org/zh/)                                                     | 原生态使用体验、高性能                       |
|   ❌    | 分布式限流熔断  | Alibaba Sentinel         | [Alibaba Sentinel文档](https://sentinelguard.io/zh-cn/)                                              | 无侵入、高扩展                           |
|   ✅    | 分布式事务    | Alibaba Seata            | [Alibaba Seata文档](http://seata.io/zh-cn/)                                                          | 无侵入、高扩展 支持 四种模式                   |
|   ❌    | 分布式消息队列  | SpringCloud Stream       | [SpringCloud Stream文档](https://spring.io/projects/spring-cloud-stream)                             | 门面框架兼容各种MQ集成                      |
|   ❌    | 分布式消息队列  | Apache Kafka             | [Apache Kafka文档](https://kafka.apache.org/)                                                        | 高性能高速度                            |
|   ❌    | 分布式消息队列  | Apache RocketMQ          | [Apache RocketMQ文档](http://rocketmq.apache.org/)                                                   | 高可用功能多样                           |
|   ❌    | 分布式消息队列  | RabbitMQ                 | [RabbitMQ文档](https://www.rabbitmq.com/)                                                            | 支持各种扩展插件功能多样性                     |
|   ❌    | 分布式搜索引擎  | ElasticSearch、Easy-Es    | [Easy-Es官网](https://www.easy-es.cn/)                                                               | 以 Mybatis-Plus 方式操作 ElasticSearch |
|   ❌    | 分布式链路追踪  | Apache SkyWalking        | [Apache SkyWalking文档](https://skywalking.apache.org/docs/)                                         | 链路追踪、网格分析、度量聚合、可视化                |
|   ❌    | 分布式日志中心  | ELK                      | [ElasticSearch官网](https://www.elastic.co/cn/elasticsearch/)                                        | ELK业界成熟解决方案                       |
|   ❌    | 分布式锁     | Lock4j                   | [Lock4j官网](https://gitee.com/baomidou/lock4j)                                                      | 注解锁、工具锁 多种多样                      |
|   ❌    | 分布式幂等    | Redisson                 | [Lock4j文档](https://gitee.com/baomidou/lock4j)                                                      | 拦截重复提交                            |
|   ❌    | 分布式任务调度  | Xxl-Job                  | [Xxl-Job官网](https://www.xuxueli.com/xxl-job/)                                                      | 高性能 高可靠 易扩展                       |
|   ❌    | 分布式文件存储  | Minio                    | [Minio文档](https://docs.min.io/)                                                                    | 本地存储                              |
|   ❌    | 分布式云存储   | 七牛、阿里、腾讯                 | [OSS使用文档](https://gitee.com/JavaLionLi/RuoYi-Vue-Plus/wikis/pages?sort_id=4359146&doc_id=1469725)  | 云存储                               |
|   ❌    | 短信模块     | 阿里、腾讯                    | [短信使用文档](https://gitee.com/JavaLionLi/RuoYi-Vue-Plus/wikis/pages?sort_id=5578491&doc_id=1469725)   | 短信发送                              |
|   ❌    | 分布式监控    | Prometheus、Grafana       | [Prometheus文档](https://prometheus.io/docs/introduction/overview/)                                  | 全方位性能监控                           |
 |   ❌    | 服务监控     | SpringBoot-Admin         | [SpringBoot-Admin文档](https://codecentric.github.io/spring-boot-admin/current/)                     | 全方位服务监控                           |
|   ✅    | 数据库框架    | Mybatis-Plus             | [Mybatis-Plus文档](https://baomidou.com/guide/)                                                      | 快速 CRUD 增加开发效率                    |
|   ❌    | 数据库框架    | P6spy                    | [p6spy官网](https://p6spy.readthedocs.io/)                                                           | 更强劲的 SQL 分析                       |
|   ✅    | 多数据源框架   | Dynamic-Datasource       | [dynamic-ds文档](https://www.kancloud.cn/tracy5546/dynamic-datasource/content)                       | 支持主从与多种类数据库异构                     |
|   ✅    | 序列化框架    | Jackson                  | [Jackson官网](https://github.com/FasterXML/jackson)                                                  | 统一使用 jackson 高效可靠                 |
|   ❌    | Redis客户端 | Redisson                 | [Redisson文档](https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95)                         | 支持单机、集群配置                         |
|   ❌    | 校验框架与完善  | Validation               | [Validation文档](https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/)     | 增强接口安全性、严谨性 支持国际化                 |
|   ❌    | Excel框架  | Alibaba EasyExcel        | [EasyExcel文档](https://www.yuque.com/easyexcel/doc/easyexcel)                                       | 性能优异 扩展性强                         |
|   ❌    | 文档框架     | SpringDoc、javadoc        | [接口文档](https://gitee.com/JavaLionLi/RuoYi-Cloud-Plus/wikis/pages?sort_id=5877829&doc_id=2056143)   | 无注解零入侵基于java注释                    |
|   ✅    | 工具类框架    | Hutool、Lombok            | [Hutool文档](https://www.hutool.cn/docs/)                                                            | 减少代码冗余 增加安全性                      |
|   ❌    | 代码生成器    | 适配MP、SpringDoc规范化代码      | [代码生成文档](https://gitee.com/JavaLionLi/RuoYi-Cloud-Plus/wikis/pages?sort_id=5522467&doc_id=2056143) | 一键生成前后端代码                         |
 |   ❌    | 部署方式     | Docker      k8s          | [Docker文档](https://docs.docker.com/)                                                               | 容器编排 一键部署业务集群                     |
|   ❌    | 国际化      | SpringMessage            | [SpringMVC文档](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc)    | Spring标准国际化方案                     |


## 贡献代码

欢迎各路英雄豪杰 `PR` 代码 请提交到 `dev` 开发分支 统一测试发版
