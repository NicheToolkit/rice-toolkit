## [Rice-Toolkit](https://github.com/NicheToolkit/rice-toolkit) rice开发工具组

[![GitHub License](https://img.shields.io/badge/license-Apache-blue.svg)](https://github.com/NicheToolkit/rice-toolkit/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.nichetoolkit/rice-toolkit-starter)](https://central.sonatype.com/search?smo=true&q=rice-toolkit-starter&namespace=io.github.nichetoolkit)
[![Nexus Release](https://img.shields.io/nexus/r/io.github.nichetoolkit/rice-toolkit-starter?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/releases/io/github/nichetoolkit/rice-toolkit-starter/)
[![Nexus Snapshot](https://img.shields.io/nexus/s/io.github.nichetoolkit/rice-toolkit-starter?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/nichetoolkit/rice-toolkit-starter/)
![Tests](https://github.com/NicheToolkit/rice-toolkit/workflows/Tests/badge.svg)

## Toolkit Usages

- [Mybatis-Toolkit](https://github.com/NicheToolkit/mybatis-toolkit)

- [File-Toolkit](https://github.com/NicheToolkit/file-toolkit)

## Maven Central Repository

- [Maven Central Repository](https://search.maven.org/search?q=io.github.nichetoolkit)

- [Sonatype Central Repository](https://central.sonatype.dev/search?q=io.github.nichetoolkit)

## Dependent & Environment

> [Spring Boot](https://spring.io/projects/spring-boot) 2.7.18.RELEASE\
> [Maven](https://maven.apache.org/) 3.6.3+\
> [JDK](https://www.oracle.com/java/technologies/downloads/#java8) 1.8

## Instructions

### Maven Usages

#### rice-toolkit-core

* Maven (`pom.xml`)

```xml

<dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rice-toolkit-core</artifactId>
    <version>1.1.0</version>
</dependency>
```

#### rice-toolkit-context

* Maven (`pom.xml`)

```xml

<dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rice-toolkit-context</artifactId>
    <version>1.1.0</version>
</dependency>
```

#### rice-toolkit-starter

* Maven (`pom.xml`)

```xml

<dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>rice-toolkit-starter</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Configure Properties

#### bean configuration

* prefix

>
> nichetoolkit.rice.bean
>

* values

|             value             |     type     | defaultValue |                         description                          |
| :---------------------------: | :----------: | :----------: | :----------------------------------------------------------: |
|    `model.unique-enabled`     |  `Boolean`   |   `false`    | the switch of unique model check on rest service saving handle. |
| `model.dynamic-table-enabled` |  `Boolean`   |   `false`    |     the switch of dynamic table on rest service handle.      |
|     `name.unique-enabled`     |  `Boolean`   |    `true`    | the switch of unique name check on rest service saving handle.(model name must be unique) |
|    `name.nonnull-enabled`     |  `Boolean`   |    `true`    | the switch of nonnull name check on rest service saving handle.(model name can not be null) |
|     `key.invade-enabled`      |  `Boolean`   |   `false`    | the switch of invade model key on rest service saving handle.(the model id can be external input) |
|      `key.exist-enabled`      |  `Boolean`   |    `true`    | the switch of model key exist check on rest service updating handle.(the model of key not exist maybe to save when updating handle) |
|    `partition.query-size`     |  `Integer`   |    `2000`    | the partition size of `in` sql usage on rest service query handle.(the size of sql param: in (p1,p2,p3...p2000)) |
|     `partition.save-size`     |  `Integer`   |    `500`     |  the partition size of  model on rest service save handle.   |
|    `partition.delete-size`    |  `Integer`   |    `1000`    | the partition size of `in` sql usage on rest service delete handle.(the size of sql param: in (p1,p2,p3...p1000)) |
|     `delete.delete-model`     | `DeleteType` |    `none`    | the delete model of data on rest service delete handle.(the logical delete implementation mode) |
|     `delete.remove-model`     | `RemoveType` |   `number`   | the remove model of model on rest service remove handle.(the logical remove implementation mode) |
|     `delete.before-skip`      |  `Boolean`   |    `true`    | the switch of skip delete before handle on rest service delete handle. |
|      `delete.after-skip`      |  `Boolean`   |    `true`    | the switch of skip delete after handle on rest service delete handle. |
|     `delete.remove-index`     |  `Boolean`   |   `false`    | the switch of remove data with index sign on rest service query handle. |
|     `delete.boolean-sign`     |  `Boolean`   |    `true`    | the sign of delete data when logical remove model is `boolean`. |
|    `delete.boolean-value`     |  `Boolean`   |   `false`    | the value of default data when logical remove model is `boolean`. |
|     `delete.number-sign`      |  `Integer`   |     `2`      | the sign of delete data when logical remove model is `number`. |
|     `delete.number-value`     |  `Integer`   |     `1`      | the value of default data when logical remove model is `number`. |

* properties

```properties
nichetoolkit.rice.bean.model.unique-enabled=false
nichetoolkit.rice.bean.model.dynamic-table-enabled=true
nichetoolkit.rice.bean.name.unique-enabled=true
nichetoolkit.rice.bean.name.nonnull-enabled=true
nichetoolkit.rice.bean.key.invade-enabled=false
nichetoolkit.rice.bean.key.exist-enabled=true
nichetoolkit.rice.bean.partition.query-size=2000
nichetoolkit.rice.bean.partition.save-size=500
nichetoolkit.rice.bean.partition.delete-size=1000
nichetoolkit.rice.bean.delete.delete-model=none
nichetoolkit.rice.bean.delete.remove-model=number
nichetoolkit.rice.bean.delete.before-skip=true
nichetoolkit.rice.bean.delete.after-skip=true
nichetoolkit.rice.bean.delete.remove-index=false
nichetoolkit.rice.bean.delete.boolean-sign=true
nichetoolkit.rice.bean.delete.boolean-value=false
nichetoolkit.rice.bean.delete.number-sign=2
nichetoolkit.rice.bean.delete.number-value=1
```

#### login configuration

* prefix

>
> nichetoolkit.rice.login
>

* values

|         value         |    type    |  defaultValue   |                   description                   |
| :-------------------: | :--------: | :-------------: | :---------------------------------------------: |
|       `enabled`       | `Boolean`  |     `false`     |       the switch of login configuration.        |
|   `token-prefixes`    | `String[]` |    `Bearer`     |   the token prefixes of login configuration.    |
|      `skip-urls`      | `String[]` |                 |      the skip urls of login configuration.      |
|    `token-headers`    | `String[]` | `Authorization` |    the token headers of login configuration.    |
|  `token-expiration`   | `Integer`  |     `1800`      |  the token expiration of login configuration.   |
|   `token-time-unit`   | `TimeUnit` |    `seconds`    |   the token time unit of login configuration.   |
| `access-token-header` |  `String`  | `Access-Token`  | the access token header of login configuration. |
| `access-auth-header`  |  `String`  |  `Access-Auth`  | the access auth header of login configuration.  |

* properties

```properties
nichetoolkit.rice.login.enabled=false
nichetoolkit.rice.login.token-prefixes=Bearer
nichetoolkit.rice.login.skip-urls=
nichetoolkit.rice.login.token-headers=Authorization
nichetoolkit.rice.login.token-expiration=1800
nichetoolkit.rice.login.token-time-unit=seconds
nichetoolkit.rice.login.access-token-header=Access-Token
nichetoolkit.rice.login.access-auth-header=Access-Auth
```

#### serialize configuration

* prefix

>
> nichetoolkit.rice.serialize
>

* values

|        value         |   type   | defaultValue |                    description                     |
| :------------------: | :------: | :----------: | :------------------------------------------------: |
| `big-decimal-format` | `String` |    `0.00`    | the big decimal format of serialize configuration. |

* properties

```properties
nichetoolkit.rice.serialize.big-decimal-format=0.00
```

## Examples

[rice-toolkit-test-web](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-test-web)

## License

[Apache License](https://www.apache.org/licenses/LICENSE-2.0)

## Dependencies

[rest-toolkit](https://github.com/NicheToolkit/rest-toolkit)

[Spring Boot](https://github.com/spring-projects/spring-boot)

[Lombok](https://github.com/projectlombok/lombok)

[PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)

[Postgresql](https://www.postgresql.org/)