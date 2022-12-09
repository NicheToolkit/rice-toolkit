## [Rice-Toolkit](https://github.com/NicheToolkit/rice-toolkit) rice开发工具组

[![GitHub license](https://img.shields.io/badge/license-Apache-blue.svg)](https://github.com/NicheToolkit/rice-toolkit/blob/master/LICENSE)
[![Maven Release](https://img.shields.io/maven-central/v/io.github.nichetoolkit/rice-toolkit-starter.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.github.nichetoolkit%22%20AND%20a%3A%rice-toolkit-starter%22)
![Tests](https://github.com/NicheToolkit/rice-toolkit/workflows/Tests/badge.svg)


&emsp;&emsp; 依赖[rest-toolkit](https://github.com/NicheToolkit/rest-toolkit/rest-toolkit-starter)组件下的基于[postgresql](https://www.postgresql.org/)数据库环境下的简单业务通用服务开发组件.


## Release介绍

-  [Rice-Toolkit@1.0.4](https://github.com/NicheToolkit/rice-toolkit/tree/master/release/1.0.4.md)

### v1.0.4 Release

1、用户登录相关接口拦截功能配置及实现。

2、数据删除`logicSign`逻辑删除功能实现。

3、用户操作日志拦截及记录功能实现。

4、bug修复及功能优化。

**Full Changelog**: https://github.com/NicheToolkit/rice-toolkit/compare/v1.0.3...v1.0.4

# [rice-toolkit-core](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-core)

1、[RestSort](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/RestSort.java)
的`toString`方法默认拼接的 排序类型的`Key`值，在`SqlBuilder`进行`SQL`拼接时可以直接拼成排序`SQL`语句。

2、[IdFilter](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/filter/IdFilter.java)
的`toOperateSql`方法修复。

3、[SqlBuilder](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/builder/SqlBuilder.java)
的`value`方法新增`RestKey`类型判断，默认取`RestKey`的`getKey`为值，(数据库默认支持将字符串`'1'`转换为数字`1`的条件下)。
 新增`nnu`(`not null`)和`nu`(`null`)的`SQL`拼接方法。

4、[SqlBuilders](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/builder/SqlBuilders.java)
 新增`isnull`和`nonnull`的`SQL`拼接方法。

5、[TimeFilter](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/filter/TimeFilter.java)
`startTime`和`endTime`正反序列化默认指定为`yyyy-MM-dd HH:mm:ss`格式。

6、[PageFilter](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/filter/PageFilter.java)
的`toPage`方法修复，根据`pageSize`判断是否进行分页数据查询。

7、[RestPage](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/RestPage.java)
非分页数据构建的构造函数增加`pages`等属性默认值。

8、[NameFilter](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/filter/NameFilter.java)
新增，用于支持`name`字段的模糊查询和多个`IN`查询。

9、[stereotype](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype)
包新增用于登录相关的注解：
  - [RestAccess](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/RestAccess.java)
    接口访问验证注解。
  - [RestCheck](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/RestCheck.java)
    校验注解。
  - [RestSkip](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/RestSkip.java)
    接口验证跳过注解。
  - [RestUser](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/RestUser.java)
    接口认证用户注解。 
[stereotype.login](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/login)
  - [RestAuth](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/login/RestAuth.java)
    接口验证注解。
  - [RestLogin](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/login/RestLogin.java)
    接口验证登录注解。
  - [RestLogout](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/login/RestLogout.java)
    接口验证登出注解。
  - [RestPending](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/login/RestPending.java)
    接口认证等待注解。
[stereotype.purview](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/purview)
  - [RestActor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/purview/RestActor.java)
    用户行为注解。
  - [RestModule](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/purview/RestModule.java)
    模块注解。
  - [RestPermission](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/stereotype/purview/RestPermission.java)
    权限注解。    
    
10、[RestMap](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-core/src/main/java/io/github/nichetoolkit/rice/RestMap.java)
新增，用于登录模块模型数据存储容器，在具体接口中传递数据给拦截器，此类型参数由自定义参数解析器生成，非前端请求参数。


# [rice-toolkit-starter](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter)

1、[MEBuilderHelper](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/helper/MEBuilderHelper.java)
新增多种`build`方法，针对于一对多、多对多和多对一的数据组合关系构建抽离出的通用方法。举例说明：

>   班主任与学生一对多的构建关系
> ```java
> /**
>  * 学生类，一个学生一个班级只有一个班主任
>  */
>  public class Student {
> 	/* 班主任id */
>     private String classLeaderId;
> 	/* 班主任信息 */
> 	private ClassLeader classLeader;
> 	//TODO 其他属性信息
>  }
> 
> /**
>  * 班主任类，一个班主任管理一个班级的许多学生
>  */
>   public class ClassLeader {
> 	/* 学生信息 */
> 	private Student students;
>     //TODO 其他属性信息
>   }
> ```
> 对于一条班主任查询`SQL`查询到的数据集合`List<ClassLeader> classLeaders`和一条学生查询信息集合`List<Student> students`，需要将`students`学生信息构建到`classLeaders`班主任数据集合中。
> 
> 由于每个学生信息都包含一个班主任id值，因此可以通过对`students`学生进行分类，然后分别构建到对应的班主任数据中，对应方法为`buildMultiSourceId`。
> 
> `Source`代表源对象，即要构建的父对象（`ClassLeader`）。`Target`代表目标对象，即要构建的子对象（`Student`）。`Single`和`Multi`代表二者对应关系。`SourceId`或`TargetId`代表二者分类、关联通过父对象的`id`还是子对象的`id`。

2、[SaveAdvice](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/advice/SaveAdvice.java)
保存过程拦截接口新增。

3、[SuperService](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/SuperService.java)
新增`alertById`和`alertAll`方法，用于修改指定`id`数据的`RestKey<Integer>`类型的值。
实现`SaveAdvice<I, M>`接口，`create`、`update`、`save`和`saveAll`方法增加对`beforeXxx`和`afterXxx`方法的调用，
增加`nichetoolkit.rice.bean.key.invade-enabled`的配置支持，可以接收外部传参`id`,不使用内部生成`id`。 
增加`fieldRepeat`方法用于创建、更新或保存方法的数据校验。


4、[SupperMapper](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/mapper/SuperMapper.java)
新增`alertById`和`alertAll`方法。

5、[service](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service)
包下相关内容迁移或新增:
  - [BuilderAdvice](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/advice/BuilderAdvice.java)
    由`service`包下迁移至`service.advice`包下。
  - [ServiceAdvice](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/advice/ServiceAdvice.java)
    由`service`包下迁移至`service.advice`包下。
  - [OperateService](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/extend/OperateService.java)
    由`service`包下迁移至`service.extend`包下。
  - [RemoveService](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/extend/RemoveService.java)
    由`service`包下迁移至`service.extend`包下。

6、[SingleService](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/extend/SingleService.java)
新增,用于`service`服务开放`create`和`update`方法。

7、[AlertService](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/service/extend/AlertService.java)
新增，用于`service`服务开放`alertAll`和`alertById`方法。

8、[PropertyHelper](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/helper/PropertyHelper.java)
新增`transform`方法，用于`Long`类型按`scale`缩放为`BigDecimal`类型。

9、[RiceLoginProperties](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/configure/RiceLoginProperties.java)
新增,用于登录相关配置参数接收。
 - `enabled`[`nichetoolkit.rest.login.enabled`]: 是否开启登录拦截。
 - `skipUrls`[`nichetoolkit.rest.login.skip-urls`]: 不需要进行登录拦截的`url`。
 - `headerTokens`[`nichetoolkit.rest.login.header-tokens`]: 请求头中的`token`名字。
 - `tokenExpiration`[`nichetoolkit.rest.login.token-expiration`]: `token`过期时间，默认`1800`秒。
 - `tokenTimeUnit`[`nichetoolkit.rest.login.token-time-unit`]: `token`过期时间单位，默认单位秒数。   

10、[error.login](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error/login)
包新增登录相关异常：
 - [LoginAccountException](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error/login/LoginAccountException.java) 
   登录账户异常。
 - [LoginInfoException](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error/login/LoginInfoException.java) 
   登录信息异常。
 - [LoginPasswordException](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error/login/LoginPasswordException.java) 
   登录密码异常。
   
11、[error](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error)
包下相关内容迁移：
 - [ServiceAnnotationException](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error/service/ServiceAnnotationException.java) 
   由`error`包下迁移至`error.service`包下。
 - [ServiceRealizationException](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error/service/ServiceRealizationException.java)
   由`error`包下迁移至`error.service`包下。
 - [ServiceUnsupportedException](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/error/service/ServiceUnsupportedException.java)
   由`error`包下迁移至`error.service`包下。
 
12、[interceptor](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor)
包新增登录相关切片接口：
 - [RiceAccessInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RiceAccessInterceptor.java) 
   接口访问验证注解`RestAccess`切片拦截接口。
 - [RiceActorInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RiceActorInterceptor.java) 
   用户行为注解`RestActor`切片拦截接口。 
 - [RiceLoginInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RiceLoginInterceptor.java) 
   用户登录注解`RestLogin`切片拦截接口。 
 - [RiceModuleInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RiceModuleInterceptor.java) 
   用户模块注解`RestModule`切片拦截接口。 
 - [RicePermissionInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RicePermissionInterceptor.java) 
   用户权限注解`RestPermission`切片拦截接口。 
 - [RiceRequestInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RiceRequestInterceptor.java) 
   接口请求参数切片拦截接口。 
 - [RiceResponseInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RiceResponseInterceptor.java) 
   接口响应结果切片拦截接口。 
 - [RiceUserlogInterceptor](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-starter/src/main/java/io/github/nichetoolkit/rice/interceptor/RiceUserlogInterceptor.java) 
   用户接口访问日志监控注解`RestUserlog`切片拦截接口。 
   
# [rice-toolkit-test-web](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-test-web)

1、[logback-spring.xml](https://github.com/NicheToolkit/rice-toolkit/blob/master/rice-toolkit-test-web/src/main/resources/logback-spring.xml)
默认日志配置文件调整，新增`LOGS_FILE`所有日志的到`./logs.log`文件的输出，方便同一个`Tomcat`服务器下部署多个`War`包时，查看单个服务的日志混乱的问题。


## Maven Central

-  [Maven Central Repository](https://search.maven.org/search?q=io.github.nichetoolkit)

-  [Sonatype Central Repository](https://central.sonatype.dev/search?q=io.github.nichetoolkit)


## 依赖环境
 > [Spring Boot](https://spring.io/projects/spring-boot) 2.6.6.RELEASE\
 > [Maven](https://maven.apache.org/) 3.6.0+\
 > [JDK](https://www.oracle.com/java/technologies/downloads/#java8) 1.8\
 
## rice-toolkit-core
 * Maven (`pom.xml`)
```xml
  <dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rice-toolkit-core</artifactId>
    <version>1.0.4</version>
  </dependency>
``` 

## rice-toolkit-starter
 * Maven (`pom.xml`)
```xml
  <dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rice-toolkit-starter</artifactId>
    <version>1.0.4</version>
  </dependency>
```

## 使用方式

参考[rice-toolkit-test-web](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-test-web)模块.

 ## License 

 [Apache License](https://www.apache.org/licenses/LICENSE-2.0)
 
 ## Dependencies
 
 [rest-toolkit](https://github.com/NicheToolkit/rest-toolkit)
 
 [Spring Boot](https://github.com/spring-projects/spring-boot)
 
 [Lombok](https://github.com/projectlombok/lombok)
 
 [PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
 
 [Mybatis-plus](https://baomidou.com/)
 
 [Postgresql](https://www.postgresql.org/)