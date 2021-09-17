# log4j2-nacos-spring-boot-starter

参考：[log4j 动态化配置](https://logging.apache.org/log4j/2.x/manual/customconfig.html#ConfigurationBuilder) 

此插件实现了利用Nacos实现Log4j2实现log配置文件动态加载

## 在Nacos 增加需要动态刷新的Log4J2配置
![](https://raw.githubusercontent.com/share-framework/sharer-pic/master/20210917111758.png)

## 具体使用
```json
[
  {
    "name":"ROOT",
    "level":"info"
  },
  {
    "name":"org.andot.log4j2.nacos",
    "level":"debug"
  }
]
```

> 这个示例是用来修改 打印日志等级，在项目启动中，如果修改 org.andot.log4j2.nacos 包的 level 为 error， 那这个org.andot.log4j2.nacos 包下面的log.debug("hello")； 就不会打印hello
