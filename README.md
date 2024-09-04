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

### Model & Entity & Filter

#### model

the model is used on service  or controller handle. 


|       name       |                          typeParams                          |                         description                          |
| :--------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|    SaveModel     |                                                              | the model is used to keep `SaveType` and `logicSign` fields. |
|   OperateModel   |                                                              |        the model is used to keep `OperateType` field.        |
|    TimeModel     |                                                              | the model is used to keep `createTime` and `updateTime` field. |
|     IdModel      |                            `<I>`                             | the model is used to keep `id` field，and the `id` type can be any object. |
|    InfoModel     |                            `<I>`                             |  the model is used to keep `name` and `description` fields.  |
|  DefaultIdModel  | `<M extends DefaultIdModel<M,E,I>,E extends DefaultIdEntity<E,M,I>,I>` | the children models of  `DefaultIdModel` must be implement the `toEntity()` method，and the `id` type can be any object. |
| DefaultInfoModel | `<M extends DefaultInfoModel<M, E, I>, E extends DefaultInfoEntity<E, M, I>, I>` | the children models of  `DefaultInfoModel` must be implement the `toEntity()` method，and the `id` type can be any object. |
|   RestIdModel    | `<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>>` | the children models of  `RestIdModel` must be implement the `toEntity()` method，and the `id` type is default `String`. |
|  RestInfoModel   | `<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>>` | the children models of  `RestInfoModel` must be implement the `toEntity()` method，and the `id` type is default `String`. |

* examples

```java

@Data
public class SimpleModel extends RestInfoModel<SimpleModel,SimpleEntity> {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public SimpleModel() {
    }

    public SimpleModel(String id) {
        super(id);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public SimpleEntity toEntity() {
        SimpleEntity entity = new SimpleEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        entity.setTime(this.getTime());
        return entity;
    }
}
```

#### entity

the entity is used to keep fields on mapper handle. 


|       name        |                          typeParams                          |                         description                          |
| :---------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   OperateEntity   |                                                              | the entity is used to keep `operate` and `logicSign` fields. |
|    TimeEntity     |                                                              | the entity is used to keep `createTime` and `updateTime` fields. |
|     IdEntity      |                            `<I>`                             | the entity is used to keep `id` field，and the `id` type can be any object. |
|    InfoEntity     |                            `<I>`                             | the entity is used to keep `name` and `description` fields.  |
|  DefaultIdEntity  | `<E extends DefaultIdEntity<E,M,I>, M extends DefaultIdModel<M,E,I>,I>` | the children entities of  `DefaultIdEntity` must be implement the `toModel()` method，and the `id` type can be any object. |
| DefaultInfoEntity | `<E extends DefaultInfoEntity<E,M,I>,M extends DefaultInfoModel<M,E,I>,I>` | the children entities of  `DefaultInfoEntity` must be implement the `toModel()` method，and the `id` type can be any object. |
|   RestIdEntity    |  `<E extends RestIdEntity<E,M>,M extends RestIdModel<M,E>>`  | the children entities of  `RestIdEntity` must be implement the `toModel()` method，and the `id` type is default `String`. |
|  RestInfoEntity   | `<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>>` | the children entities of  `RestInfoEntity` must be implement the `toModel()` method，and the `id` type is default `String`. |

* examples

```java

@Data
public class SimpleEntity extends RestInfoEntity<SimpleEntity,SimpleModel> {

    private Date time;

    public SimpleEntity() {
    }

    public SimpleEntity(String id) {
        super(id);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public SimpleModel toModel() {
   	    SimpleModel model = new SimpleModel();
        model.setId(this.getId());
        model.setName(this.getName());
        model.setDescription(this.getDescription());
        model.setTime(this.getTime());
        return entity;
    }

}
```

#### filter

the filter is used to receive query fields on service  or controller handle. 


|     name      | typeParams |                         description                          |
| :-----------: | :--------: | :----------------------------------------------------------: |
|  PageFilter   |            | the filter is used to receive `pageNum` and `pageSize` fields.(the `loadLastPage` is used to load the last page data of query handle) |
|  SortFilter   |            | the filter is used to receive `sorts` field.(the `RestSort` is used to sort the data of query handle) |
| OperateFilter |            | the filter is used to receive `operate` and `operates` fields.(the `remove` is used to load the logical delete data of query handle) |
|  TableFilter  |   `<K>`    | the filter is used to receive `tablekey` field，and the `tablekey` type can be any object.(the `tablekey` is used to load the data of query handle on dynamic table ) |
|   IdFilter    |  `<I, K>`  | the filter is used to receive `id` and `ids` fields.and the `id` type can be any object. |
|  TimeFilter   |  `<I, K>`  | the filter is used to receive `startTime` and `endTime` fields. |
|  JsonbFilter  |  `<I, K>`  | the filter is used to receive `contrasts`、`ranges`、`equals`  and `contains` fields.(the `ContrastRule`、`RangeRule`、`EqualRule` and `ContainRule` is used to filter the `jsonb` data of query handle) |
|  NameFilter   |  `<I, K>`  |   the filter is used to receive `name` and `names` fields.   |
| DefaultFilter |  `<I, K>`  | the children filters of  `DefaultFilter` can build `SQL` with `toRemoveSql()` and `toQuerySql()` methods. |
|  RestFilter   |            |       the `id` and `tablekey`type is default `String`.       |

* examples

```java

public class SimpleFilter extends RestFilter {
}
```

### Advice & Mapper & Service

#### Advice

the advice is used to handle the models or entities on service. 


|     name      |                    typeParams                     | method                                                       |                         description                          |
| :-----------: | :-----------------------------------------------: | ------------------------------------------------------------ | :----------------------------------------------------------: |
|  AlertAdvice  |                       `<I>`                       | `beforeAlert(I id)`、`beforeAlertAll(Collection<I> idList)`、`afterAlert(I id)`and `afterAlertAll(Collection<I> idList)` | the advice is used to handle `alert` and `alertAll` methods. |
| BuilderAdvice | `<I,M extends IdModel<I>, E extends IdEntity<I>>` | `buildEntity(M model, E entity, Object... idArray)`、`buildEntityList(Collection<M> modelList, List<E> entityList, Object... idArray)`、`buildModel(E entity, M model, Boolean... isLoadArray)` and `buildModelList(Collection<E> entityList, List<M> modelList, Boolean... isLoadArray)` | the advice is used to handle `buildEntity` 、`buildEntityList` 、`buildModel` and `buildModelList` methods. |
| OperateFilter |                                                   |                                                              | the filter is used to receive `operate` and `operates` fields.(the `remove` is used to load the logical delete data of query handle) |
|  TableFilter  |                        <K>                        |                                                              | the filter is used to receive `tablekey` field，and the `tablekey` type can be any object.(the `tablekey` is used to load the data of query handle on dynamic table ) |
|   IdFilter    |                      <I, K>                       |                                                              | the filter is used to receive `id` and `ids` fields.and the `id` type can be any object. |
|  TimeFilter   |                      <I, K>                       |                                                              | the filter is used to receive `startTime` and `endTime` fields. |
|  JsonbFilter  |                      <I, K>                       |                                                              | the filter is used to receive `contrasts`、`ranges`、`equals`  and `contains` fields.(the `ContrastRule`、`RangeRule`、`EqualRule` and `ContainRule` is used to filter the `jsonb` data of query handle) |
|  NameFilter   |                      <I, K>                       |                                                              |   the filter is used to receive `name` and `names` fields.   |
| DefaultFilter |                      <I, K>                       |                                                              | the children filters of  `DefaultFilter` can build `SQL` with `toRemoveSql()` and `toQuerySql()` methods. |
|  RestFilter   |                                                   |                                                              |       the `id` and `tablekey`type is default `String`.       |

* examples

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