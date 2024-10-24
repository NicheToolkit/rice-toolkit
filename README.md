## [Rice-Toolkit](https://github.com/NicheToolkit/rice-toolkit)

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

## Wiki Reference

[Wiki Reference](https://github.com/NicheToolkit/rice-toolkit/wiki): https://github.com/NicheToolkit/rice-toolkit/wiki

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
> nichetoolkit.rice.service
>

* values

|          value          |     type     | defaultValue |                         description                          |
| :---------------------: | :----------: | :----------: | :----------------------------------------------------------: |
|     `unique-model`      |  `Boolean`   |   `false`    | the switch of unique model check on rest service saving handle. |
|     `dynamic-table`     |  `Boolean`   |   `false`    |     the switch of dynamic table on rest service handle.      |
|      `unique-name`      |  `Boolean`   |    `true`    | the switch of unique name check on rest service saving handle.(model name must be unique) |
|     `nonnull-name`      |  `Boolean`   |    `true`    | the switch of nonnull name check on rest service saving handle.(model name can not be null) |
|    `identity-invade`    |  `Boolean`   |   `false`    | the switch of invade model identity on rest service saving handle.(the model id can be external input) |
|    `identity-check`     |  `Boolean`   |    `true`    | the switch of model identity exist check on rest service updating handle.(the model of key not exist maybe to save when updating handle) |
|      `delete-mode`      | `DeleteMode` |   `remove`   | the delete mode of data on rest service delete handle.(the logical delete implementation mode) |
|      `before-skip`      |  `Boolean`   |    `true`    | the switch of skip before handle on rest service delete handle. |
|      `after-skip`       |  `Boolean`   |    `true`    | the switch of skip after handle on rest service delete handle. |
| `partition.query-size`  |  `Integer`   |    `2000`    | the partition size of `in` sql usage on rest service query handle.(the size of sql param: in (p1,p2,p3...p2000)) |
|  `partition.save-size`  |  `Integer`   |    `500`     |  the partition size of  model on rest service save handle.   |
| `partition.delete-size` |  `Integer`   |    `1000`    | the partition size of `in` sql usage on rest service delete handle.(the size of sql param: in (p1,p2,p3...p1000)) |
| `delete.accurate-judge` |  `Boolean`   |   `false`    | the switch of remove data with accurate judge sign on rest service query handle. |
|   `delete.logic-mode`   | `LogicMode`  |   `config`   | the logic mode of data on rest service remove handle.(the logical remove implementation mode) |
|  `delete.config-mark`   | `ConfigMark` |   `number`   |    the config mode of data on rest service logic handle.     |
|   `delete.auto-mark`    |  `AutoMark`  |  `identity`  |     the auto mode of data on rest service logic handle.      |
|  `delete.unmark-value`  |   `Object`   |     `1`      |     the unmark of normal data when logical remove model.     |
|   `delete.mark-value`   |   `Object`   |     `2`      |      the mark of delete data when logical remove model.      |

* properties

```properties
nichetoolkit.rice.service.unique-model=false
nichetoolkit.rice.service.dynamic-table= false
nichetoolkit.rice.service.identity-invade= false
nichetoolkit.rice.service.identity-check=true
nichetoolkit.rice.service.unique-name= true
nichetoolkit.rice.service.nonnull-name= true
nichetoolkit.rice.service.partition.query-size=2000
nichetoolkit.rice.service.partition.save-size=500
nichetoolkit.rice.service.partition.delete-size=1000
nichetoolkit.rice.service.before-skip=true
nichetoolkit.rice.service.after-skip=true
nichetoolkit.rice.service.delete-mode=remove
nichetoolkit.rice.service.delete.logic-mode=config
nichetoolkit.rice.service.delete.accurate-judge=true
nichetoolkit.rice.service.delete.unmark-value=1
nichetoolkit.rice.service.delete.mark-value=2
```

#### login configuration

* prefix

>
> nichetoolkit.rice.login
>

* values

|         value         |    type    |  defaultValue   |                   description                   |
|:---------------------:|:----------:|:---------------:|:-----------------------------------------------:|
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
|:--------------------:|:--------:|:------------:|:--------------------------------------------------:|
| `big-decimal-format` | `String` |    `0.00`    | the big decimal format of serialize configuration. |

* properties

```properties
nichetoolkit.rice.serialize.big-decimal-format=0.00
```

### Model & Entity & Filter

#### model

the model is used on service or controller handle.

|        name        |                                    typeParams                                    |                                                        description                                                        |
|:------------------:|:--------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------:|
|    `SaveModel`     |                                                                                  |                                        the model is used to keep `SaveType` field.                                        |
|    `LogicModel`    |                                                                                  |                                         the model is used to keep `logic` field.                                          |
|   `OperateModel`   |                                                                                  |                                      the model is used to keep `OperateType` field.                                       |
|    `TimeModel`     |                                                                                  |                              the model is used to keep `createTime` and `updateTime` field.                               |
|     `IdModel`      |                                      `<I>`                                       |                         the model is used to keep `id` field，and the `id` type can be any object.                         |
|    `InfoModel`     |                                      `<I>`                                       |                                the model is used to keep `name` and `description` fields.                                 |
|  `DefaultIdModel`  |      `<M extends DefaultIdModel<M,E,I>,E extends DefaultIdEntity<E,M,I>,I>`      |  the children models of  `DefaultIdModel` must be implement the `toEntity()` method，and the `id` type can be any object.  |
| `DefaultInfoModel` | `<M extends DefaultInfoModel<M, E, I>, E extends DefaultInfoEntity<E, M, I>, I>` | the children models of  `DefaultInfoModel` must be implement the `toEntity()` method，and the `id` type can be any object. |
|   `RestIdModel`    |          `<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>>`           |  the children models of  `RestIdModel` must be implement the `toEntity()` method，and the `id` type is default `String`.   |
|  `RestInfoModel`   |        `<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>>`         | the children models of  `RestInfoModel` must be implement the `toEntity()` method，and the `id` type is default `String`.  |

* examples

```java

@Data
public class SimpleModel extends RestInfoModel<SimpleModel, SimpleEntity> {
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

|        name         |                                 typeParams                                 |                                                         description                                                         |
|:-------------------:|:--------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|
|    `LogicEntity`    |                                                                            |                                          the entity is used to keep `logic` field.                                          |
|   `OperateEntity`   |                                                                            |                                         the entity is used to keep `operate` field.                                         |
|    `TimeEntity`     |                                                                            |                              the entity is used to keep `createTime` and `updateTime` fields.                               |
|     `IdEntity`      |                                   `<I>`                                    |                         the entity is used to keep `id` field，and the `id` type can be any object.                          |
|    `InfoEntity`     |                                   `<I>`                                    |                                 the entity is used to keep `name` and `description` fields.                                 |
|  `DefaultIdEntity`  |  `<E extends DefaultIdEntity<E,M,I>, M extends DefaultIdModel<M,E,I>,I>`   |  the children entities of  `DefaultIdEntity` must be implement the `toModel()` method，and the `id` type can be any object.  |
| `DefaultInfoEntity` | `<E extends DefaultInfoEntity<E,M,I>,M extends DefaultInfoModel<M,E,I>,I>` | the children entities of  `DefaultInfoEntity` must be implement the `toModel()` method，and the `id` type can be any object. |
|   `RestIdEntity`    |         `<E extends RestIdEntity<E,M>,M extends RestIdModel<M,E>>`         |  the children entities of  `RestIdEntity` must be implement the `toModel()` method，and the `id` type is default `String`.   |
|  `RestInfoEntity`   |     `<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>>`      | the children entities of  `RestInfoEntity` must be implement the `toModel()` method，and the `id` type is default `String`.  |

* examples

```java

@Data
public class SimpleEntity extends RestInfoEntity<SimpleEntity, SimpleModel> {

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

the filter is used to receive query fields on service or controller handle.

|      name       | typeParams |                                                                                             description                                                                                              |
|:---------------:|:----------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|  `PageFilter`   |            |                                the filter is used to receive `pageNum` and `pageSize` fields.(the `loadLastPage` is used to load the last page data of query handle)                                 |
|  `SortFilter`   |            |                                                the filter is used to receive `sorts` field.(the `RestSort` is used to sort the data of query handle)                                                 |
| `OperateFilter` |            |                                 the filter is used to receive `operate` and `operates` fields.(the `remove` is used to load the logical delete data of query handle)                                 |
|  `TableFilter`  |   `<K>`    |                 the filter is used to receive `tablekey` field，and the `tablekey` type can be any object.(the `tablekey` is used to load the data of query handle on dynamic table )                 |
|   `IdFilter`    |  `<I, K>`  |                                                       the filter is used to receive `id` and `ids` fields.and the `id` type can be any object.                                                       |
|  `TimeFilter`   |  `<I, K>`  |                                                                   the filter is used to receive `startTime` and `endTime` fields.                                                                    |
|  `JsonbFilter`  |  `<I, K>`  | the filter is used to receive `contrasts`、`ranges`、`equals`  and `contains` fields.(the `ContrastRule`、`RangeRule`、`EqualRule` and `ContainRule` is used to filter the `jsonb` data of query handle) |
|  `NameFilter`   |  `<I, K>`  |                                                                       the filter is used to receive `name` and `names` fields.                                                                       |
| `DefaultFilter` |  `<I, K>`  |                                              the children filters of  `DefaultFilter` can build `SQL` with `toRemoveSql()` and `toQuerySql()` methods.                                               |
|  `RestFilter`   |            |                                                                           the `id` and `tablekey`type is default `String`.                                                                           |

* examples

```java

public class SimpleFilter extends RestFilter {
}
```

### Advice & Mapper & Service

#### Advice

the advice is used to handle the models or entities on service.
<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>beforeAlert(I id)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeAlertAll(Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>afterAlert(I id)</code></td>
</tr>
<tr>
<td><code>afterAlertAll(Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeDelete(E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeDeleteAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>afterDelete(E entity)</code></td>
</tr>
<tr>
<td><code>afterDeleteAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeOperate(E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeOperateAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>afterOperate(E entity)</code></td>
</tr>
<tr>
<td><code>afterOperateAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeRemove(E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeRemoveAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>afterRemove(E entity)</code></td>
</tr>
<tr>
<td><code>afterRemoveAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>SaveAdvice</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeCreate(M model)</code></td>
<td rowspan=8 style="vertical-align: middle;">the advice is used to handle <code>create</code> 、<code>update</code> 、<code>save</code> and <code>saveAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeUpdate(M model)</code></td>
</tr>
<tr>
<td><code>beforeSave(M model)</code></td>
</tr>
<tr>
<td><code>beforeSaveAll(Collection&lt;M&gt; modelList)</code></td>
</tr>
<tr>
<td><code>afterCreate(M model)</code></td>
</tr>
<tr>
<td><code>afterUpdate(M model)</code></td>
</tr>
<tr>
<td><code>afterSave(M model)</code></td>
</tr>
<tr>
<td><code>afterSaveAll(Collection&lt;M&gt; modelList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>BuilderAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>buildEntity(M model, E entity, Object... idArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>buildEntity</code> 、<code>buildEntityList</code> 、<code>buildModel</code> and <code>buildModelList</code> methods.</td>
</tr>
<tr>
<td><code>buildEntityList(Collection&lt;M&gt; modelList, List&lt;E&gt; entityList, Object... idArray)</code></td>
</tr>
<tr>
<td><code>buildModel(E entity, M model, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>buildModelList(Collection&lt;E&gt; entityList, List&lt;M&gt; modelList, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>MutateAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>mutateEntity(E entity, Boolean... isLoadArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>mutateEntity</code> 、<code>mutateEntityList</code> 、<code>mutateModel</code> and <code>mutateModelList</code> methods.</td>
</tr>
<tr>
<td><code>mutateEntityList(List&lt;E&gt; entityList, ConsumerActuator&lt;E&gt; actuator, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>mutateModel(M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>mutateModelList(List&lt;M&gt; modelList, ConsumerActuator&lt;M&gt; actuator, Object... idArray)</code></td>
</tr>
<tr>
<td rowspan=3 style="vertical-align: middle;"><code>TablenameAdvice</code></td>
<td rowspan=3 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>resolveTablename(K tablekey)</code></td>
<td rowspan=3 style="vertical-align: middle;">the advice is used to handle <code>resolveTablename</code> method.</td>
</tr>
<tr>
<td><code>resolveTablename(K tablekey, M model)</code></td>
</tr>
<tr>
<td><code>resolveTablename(K tablekey, Collection&lt;M&gt; modelList)</code></td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>FilterAdvice</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td><code>queryWhereSql(F filter)</code></td>
<td rowspan=8 style="vertical-align: middle;">the advice is used to handle filter build <code>SQL</code>methods.</td>
</tr>
<tr>
<td><code>deleteWhereSql(F filter)</code></td>
</tr>
<tr>
<td><code>removeWhereSql(F filter)</code></td>
</tr>
<tr>
<td><code>operateWhereSql(F filter)</code></td>
</tr>
<tr>
<td><code>findLoadArray(F filter)</code></td>
</tr>
<tr>
<td><code>queryLoadArray(F filter)</code></td>
</tr>
<tr>
<td><code>fieldArray(F filter)</code></td>
</tr>
<tr>
<td><code>tablekey(F filter)</code></td>
</tr>
<tr>
</table>
* examples

```java

@Service
public class SimpleServiceImpl extends RestInfoService<SimpleModel, SimpleEntity, SimpleFilter> implements SimpleService {

    @Override
    protected void optionalInit(@NonNull SimpleModel model) throws RestException {
        model.setTime(Optional.ofNullable(model.getTime()).orElse(new Date()));
    }

    @Override
    public String queryWhereSql(SimpleFilter filter) throws RestException {
        return filter.toTimeSql("create_time").toNameSql("name").toQuerySql(this, "logic").addSorts("id").toIdSql().toSql();
    }
}
```

#### Mapper

* default mapper

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>alertById(@Param("id") I id, @Param("key") Integer key)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("key") Integer key)</code></td>
</tr>
<tr>
<td><code>alertAll(@Param("idList") Collection&lt;I&gt; idList, @Param("key") Integer key)</code></td>
</tr>
<tr>
<td><code>alertDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("key") Integer key)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>DeleteMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>deleteById(@Param("id") I id)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteDynamicById(@Param("tablename") String tablename, @Param("id") I id)</code></td>
</tr>
<tr>
<td><code>deleteAll(@Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>deleteDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>deleteAllByWhere(@Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td><code>deleteDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>OperateMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>operateById(@Param("id") I id, @Param("operate") Integer operate)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>operateDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateAll(@Param("idList") Collection&lt;I&gt; idList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateAllByWhere(@Param("whereSql") String whereSql, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>RemoveMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>removeById(@Param("id") I id, @Param("logic") String logic)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeAll(@Param("idList") Collection&lt;I&gt; idList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeAllByWhere(@Param("whereSql") String whereSql, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql,@Param("logic") String logic)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>SaveMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>save(@Param("entity") E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>save</code> and <code>saveAll</code> methods.</td>
</tr>
<tr>
<td><code>saveDynamic(@Param("tablename") String tablename, @Param("entity") E entity)</code></td>
</tr>
<tr>
<td><code>saveAll(@Param("entityList") Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>saveDynamicAll(@Param("tablename") String tablename, @Param("entityList") Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>FindMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>findById(@Param("id") I id)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>find</code> and <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicById(@Param("tablename") String tablename, @Param("id") I id)</code></td>
</tr>
<tr>
<td><code>findAll(@Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>findDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>findAllByWhere(@Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td><code>findDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>SuperMapper</code></td>
<td style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td></td>
<td style="vertical-align: middle;">the mapper is default super mapper.</td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>InfoMapper</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>findByName(@Param("name") String name, @Param("logic") String logic)</code></td>
<td rowspan=8 style="vertical-align: middle;">the mapper is used to handle <code>find</code> and <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByName(@Param("tablename") String tablename, @Param("name") String name, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findByNameAndNotId(@Param("name") String name, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findByEntity(@Param("entity") E entity, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findDynamicByEntity(@Param("tablename") String tablename, @Param("entity") E entity, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findByEntityAndNotId(@Param("entity") E entity, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findDynamicByEntityAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
</table>

* link & field mapper

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteLinkMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>deleteByLinkId(@Param("linkId") I linkId)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId)</code></td>
</tr>
<tr>
<td><code>deleteAllByLinkIds(@Param("linkIdList") Collection&lt;I&gt; linkIdList)</code></td>
</tr>
<tr>
<td><code>deleteDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;I&gt; linkIdList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateLinkMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>operateByLinkId(@Param("id") I linkId, @Param("operate") Integer operate)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>operateDynamicByLinkId(@Param("tablename") String tablename, @Param("id") I linkId, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateAllByLinkIds(@Param("linkIdList") Collection&lt;I&gt; linkIdList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;I&gt; linkIdList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveLinkMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>removeByLinkId(@Param("linkId") I linkId, @Param("logic") String logic)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeAllByLinkIds(@Param("linkIdList") Collection&lt;I&gt; linkIdList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;I&gt; linkIdList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertFieldMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>alertFieldById(@Param("id") I id, @Param("field") String field, @Param("key") Integer key)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertDynamicFieldById(@Param("tablename") String tablename, @Param("id") I id, @Param("field") String field, @Param("key") Integer key)</code></td>
</tr>
<tr>
<td><code>alertFieldAll(@Param("idList") Collection&lt;I&gt; idList, @Param("field") String field, @Param("key") Integer key)</code></td>
</tr>
<tr>
<td><code>alertDynamicFieldAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("field") String field, @Param("key") Integer key)</code></td>
</tr>
</table>

* native mapper

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>FindLoadMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findByIdLoad(@Param("id") I id, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>find</code> and <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByIdLoad(@Param("tablename") String tablename, @Param("id") I id, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findAllLoad(@Param("idList") Collection&lt;I&gt; idList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findDynamicAllLoad(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>FilterLoadMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicAllByLoadWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>NameLoadMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findByNameLoad(@Param("name") String name,@Param("logic") String logic, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>find</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByNameLoad(@Param("tablename") String tablename, @Param("name") String name, @Param("logic") String logic, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>FindFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>DeleteFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>deleteAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>RemoveFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>removeAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("logic") String logic)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>OperateFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>operateAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>operateDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>FindFieldMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findAllByFieldWhere(@Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicAllByFieldWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams)</code></td>
</tr>
</table>

* examples

```java

@Component
public interface SimpleMapper extends RestInfoMapper<SimpleEntity>, Mapper<SimpleEntity, String> {
}
```

#### Service

* default service

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>alertAll(Collection&lt;I&gt; idList, RestKey&lt;Integer&gt; keyType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertAll(String tablekey, Collection&lt;I&gt; idList, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertById(I id, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertById(String tablekey, I id, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,K&gt;</code></td>
<td><code>deleteAll(Collection&lt;I&gt; idList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteAll(K tablekey, Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>deleteById(I id)</code></td>
</tr>
<tr>
<td><code>deleteById(K tablekey, I id)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,K&gt;</code></td>
<td><code>operateAll(Collection&lt;I&gt; idList, OperateType operateType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>operateAll(K tablekey, Collection&lt;I&gt; idList, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateById(I id, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateById(K tablekey, I id, OperateType operateType)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,K&gt;</code></td>
<td><code>removeAll(Collection&lt;I&gt; idList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeAll(K tablekey, Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>removeById(I id)</code></td>
</tr>
<tr>
<td><code>removeById(K tablekey, I id)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>NameService</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>queryByName(String name, Boolean... isLoadArray)</code></td>
<td rowspan=2 style="vertical-align: middle;">the service is used to handle <code>query</code>  methods.</td>
</tr>
<tr>
<td><code>queryByName(K tablekey, String name, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>SingleService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>create(M model, Object... idArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>create</code> and <code>update</code> methods.</td>
</tr>
<tr>
<td><code>create(K tablekey, M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>update(M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>update(K tablekey, M model, Object... idArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>SaveService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>save(M model, Object... idArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>save</code> and <code>saveAll</code> methods.</td>
</tr>
<tr>
<td><code>save(K tablekey, M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>saveAll(Collection&lt;M&gt; modelList, Object... idArray)</code></td>
</tr>
<tr>
<td><code>saveAll(K tablekey, Collection&lt;M&gt; modelList, Object... idArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>QueryService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>queryAll(Collection&lt;I&gt; idList, Boolean... isLoadArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>query</code> and <code>queryAll</code> methods.</td>
</tr>
<tr>
<td><code>queryAll(K tablekey, Collection&lt;I&gt; idList, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>queryById(I id, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>queryById(K tablekey, I id, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>FilterService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, F extends IdFilter&lt;I,K&gt;,I,K&gt;</code></td>
<td><code>queryAllWithFilter(F filter)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>queryAll</code>、<code>deleteAll</code>、<code>removeAll</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteAllWithFilter(F filter)</code></td>
</tr>
<tr>
<td><code>removeAllWithFilter(F filter)</code></td>
</tr>
<tr>
<td><code>operateAllWithFilter(F filter)</code></td>
</tr>
<tr>
<td><code>SuperService</code></td>
<td><code>&lt;M extends RestId&lt;I&gt;, E extends RestId&lt;I&gt;, F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td></td>
<td>the service is default super service.</td>
</tr>
<tr>
<td><code>InfoService</code></td>
<td><code>&lt;M extends InfoModel&lt;I&gt;, E extends InfoEntity&lt;I&gt;, F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td></td>
<td>the service is default info service.</td>
</tr>
</table>

* extend service

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=7 style="vertical-align: middle;"><code>OptionalService</code></td>
<td rowspan=7 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td><code>optional(@NonNull M model)</code></td>
<td rowspan=7 style="vertical-align: middle;">the service is used to handle <code>optional</code> and <code>exist</code> methods.</td>
</tr>
<tr>
<td><code>existById(I id)</code></td>
</tr>
<tr>
<td><code>existById(K tablekey, I id)</code></td>
</tr>
<tr>
<td><code>optionalQueryFilter(F filter)</code></td>
</tr>
<tr>
<td><code>optionalDeleteFilter(F filter)</code></td>
</tr>
<tr>
<td><code>optionalRemoveFilter(F filter)</code></td>
</tr>
<tr>
<td><code>optionalOperateFilter(F filter)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateLinkService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>operateAllByLinkIds(Collection&lt;I&gt; linkIdList, OperateType operateType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>operateAllByLinkIds(String tablekey, Collection&lt;I&gt; linkIdList, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateByLinkId(I linkId, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateByLinkId(String tablekey, I linkId, OperateType operateType)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteLinkService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>deleteAllByLinkIds(Collection&lt;I&gt; linkIdList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteAllByLinkIds(String tablekey, Collection&lt;I&gt; linkIdList)</code></td>
</tr>
<tr>
<td><code>deleteByLinkId(I linkId)</code></td>
</tr>
<tr>
<td><code>deleteByLinkId(String tablekey, I linkId)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveLinkService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I, K&gt;</code></td>
<td><code>removeAllByLinkIds(Collection&lt;I&gt; linkIdList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeAllByLinkIds(K tablekey, Collection&lt;I&gt; linkIdList)</code></td>
</tr>
<tr>
<td><code>removeByLinkId(I linkId)</code></td>
</tr>
<tr>
<td><code>removeByLinkId(K tablekey, I linkId)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertFieldService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>alertFieldAll(Collection&lt;I&gt; idList, String field, RestKey&lt;Integer&gt; keyType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertFieldAll(String tablekey, Collection&lt;I&gt; idList, String field, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertFieldById(I id, String field, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertFieldById(String tablekey, I id, String field, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
</table>

* id & info service

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>description</th>
</tr>
<tr>
<td style="vertical-align: middle;"><code>DefaultIdService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends DefaultIdModel&lt;M, E, I&gt;, E extends DefaultIdEntity&lt;E, M, I&gt;, F extends DefaultFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract id service, the <code>id</code> and <code>tablekey</code> type can be any object.</td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>DefaultInfoService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends DefaultInfoModel&lt;M, E, I&gt;, E extends DefaultInfoEntity&lt;E, M, I&gt;, F extends DefaultFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract info service, the <code>id</code> and <code>tablekey</code> type can be any object.</td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>RestIdService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends RestIdModel&lt;M, E&gt;, E extends RestIdEntity&lt;E, M&gt;, F extends RestFilter&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract id service, the <code>id</code> and <code>tablekey</code> type is default <code>String</code>.</td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>RestInfoService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends RestInfoModel&lt;M, E&gt;, E extends RestInfoEntity&lt;E, M&gt;, F extends RestFilter&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract info service, the <code>id</code> and <code>tablekey</code> type is default <code>String</code>.</td>
</tr>
</table>

* examples

```java
public interface SimpleService extends FilterService<SimpleModel, SimpleFilter, String, String>, SingleService<SimpleModel, String, String> {
}
```

### Custom Identity Resolver Usages

* identity for `String` type Resolver examples

```java

@Component
public class DefaultStringIdResolver implements RestIdResolver<String> {

    @Override
    public <M extends RestId<String>> String resolveId(M model) throws RestException {
        return IdentityUtils.generateString();
    }
}
```

### Stereotype & Annotation

* default annotation

|  annotation   |                 target                  |                                               description                                                |
|:-------------:|:---------------------------------------:|:--------------------------------------------------------------------------------------------------------:|
| `RestService` |           `ElementType.TYPE`            |                      the annotation is used to annotate subclass of super service.                       |
|  `RestSkip`   | `ElementType.METHOD`、`ElementType.TYPE` |   the annotation is used to annotate `controller ` or `method` that need to skip login related handle.   |
|  `RestLogin`  |          `ElementType.METHOD`           | the annotation is used to annotate `method` that need to generate authentication information for login.  |
|  `RestCheck`  |          `ElementType.METHOD`           |         the annotation is used to annotate `method` that need to check token prefixes for login.         |
|  `RestAuth`   |          `ElementType.METHOD`           |           the annotation is used to annotate `method` that need to check  auth code for login.           |
| `RestPended`  |          `ElementType.METHOD`           | the annotation is used to annotate `method` that need to precheck  authentication information for login. |
| `RestLogout`  |          `ElementType.METHOD`           |               the annotation is used to annotate `method` that need to discard for login.                |
|  `RestUser`   |         `ElementType.PARAMETER`         |      the annotation is used to annotate `parameter` that need to inject user information of login.       |

* examples

```java

@Slf4j
@CrossOrigin
@RestController
@RestNotelog(loggingKey = "login", notelog = "login controller log")
@RequestMapping("/rest")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RestLogin
    @PostMapping("/login/password")
    @RestUserlog(loggingType = LoggingType.USER_LOGIN, userlog = "password login")
    public RestResult<UserModel> loginWithPassword(RestMap restMap, @RequestBody LoginRequest loginRequest) throws RestException {
        UserModel user = loginService.loginWithPassword(loginRequest);
        return buildLoginResult(restMap, user);
    }

    @GetMapping("/logout")
    @RestUserlog(loggingType = LoggingType.USER_LOGOUT, userlog = "user logout")
    public RestResult<?> logout() throws Exception {
        return RestResult.success();
    }

    private RestResult<UserModel> buildLoginResult(RestMap restMap, UserModel user) {
        restMap.put(UserModel.LOGIN_USER_INFO, JsonUtils.parseJson(user));
        restMap.put(UserModel.LOGIN_USER_ID, user.getId());
        return RestResult.success(user);
    }

}

```

* http request

```http request

POST http://localhost:8080/rest/login/password
Content-Type: application/json

{
  "account": "testUser",
  "password": "123456"
}

> {%
client.global.set("auth_token", response.body.data.token);
%}

###

GET http://localhost:8080/rest/info
Authorization: Bearer {{auth_token}}

###

GET http://localhost:8080/rest/logout
Authorization: Bearer {{auth_token}}

###
```

### Custom Purview

* custom `RestPurview` annotation

```java

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestPurview {

    String key() default "";

    String[] keys() default {};

    long value() default 0L;

    long[] values() default {};

    PurviewType purview() default PurviewType.PURVIEW_ALL;

    PurviewType[] purviews() default {};

    final class Purview {

        public static List<String> keys(@NonNull RestPurview purview) throws RestException {
            Set<String> keySet = new HashSet<>();
            OptionalUtils.ofEmptyable(purview.key()).ifEmptyPresent(keySet::add);
            OptionalUtils.ofEmptyable(purview.keys()).ifEmptyPresent(keys -> keySet.addAll(Arrays.asList(keys)));
            OptionalUtils.ofNullable(purview.purview()).nullFlatMap(value -> OptionalUtils.ofEmptyable(value.getKey())).emptyMap(keySet::add);
            OptionalUtils.ofNullable(purview.purviews()).ifNullPresent(values -> RestStream.stream(values).forEach(value -> OptionalUtils.ofNullable(value).nullFlatMap(module -> OptionalUtils.ofEmptyable(module.getKey())).emptyMap(keySet::add)));
            return new ArrayList<>(keySet);
        }

        public static List<Long> values(@NonNull RestPurview purview) throws RestException {
            Set<Long> valueSet = new HashSet<>();
            OptionalUtils.ofEmptyable(purview.value()).ifEmptyPresent(valueSet::add);
            OptionalUtils.ofEmptyable(purview.values()).ifEmptyPresent(values -> Arrays.stream(values).forEach(valueSet::add));
            OptionalUtils.ofNullable(purview.purview()).nullFlatMap(value -> OptionalUtils.ofEmptyable(value.getValue())).emptyMap(valueSet::add);
            OptionalUtils.ofNullable(purview.purviews()).ifNullPresent(values -> RestStream.stream(values).forEach(value -> OptionalUtils.ofNullable(value).nullFlatMap(module -> OptionalUtils.ofEmptyable(module.getValue())).emptyMap(valueSet::add)));
            return new ArrayList<>(valueSet);
        }

    }
}
```

* custom `PurviewAdvice`  advice

```java

@Slf4j
@Component
public class PurviewAdvice implements DefaultAdvice<RestPurview> {

    private final TokenService tokenService;

    @Autowired
    public PurviewAdvice(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public int order() {
        return 98;
    }

    @Override
    public void doAnnotationHandle(RestHttpRequest request, HttpServletResponse response, HandlerMethod handlerMethod, RestPurview purview) throws RestException {
        UserModel userModel = tokenService.resolveUserInfo(request);
        /* custom user purview */
        userModel.setPurviewType(PurviewType.PURVIEW_1);
        /* custom key mode check*/
        purviewKeysCheck(userModel, RestPurview.Purview.keys(purview));
        /* custom value mode check*/
        purviewValuesCheck(userModel, RestPurview.Purview.values(purview));
    }

    private void purviewKeysCheck(UserModel userModel, List<String> purviewKeys) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewKeys)) {
            OptionalUtils.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            String purviewTypeKey = purviewType.getKey();
            OptionalUtils.falseable(purviewKeys.contains(purviewTypeKey), TokenPermissionException::new);
        }
    }

    private void purviewValuesCheck(UserModel userModel, List<Long> purviewValues) throws RestException {
        PurviewType purviewType = userModel.getPurviewType();
        if (GeneralUtils.isNotEmpty(purviewValues)) {
            OptionalUtils.ofEmptyable(purviewType).emptyElseThrow(TokenPermissionException::new);
            Long annexValue = RestReckon.annexValue(purviewValues);
            Long value = purviewType.getValue();
            OptionalUtils.falseable(RestReckon.reachValue(annexValue, value), TokenPermissionException::new);
        }
    }
}
```

* controller `RestPurview` usages

```java

@Slf4j
@CrossOrigin
@RestController
@RestNotelog(loggingKey = "login", notelog = "login controller log")
@RequestMapping("/rest/purview")
public class PurviewController {

    @GetMapping("/test")
    @RestPurview(purview = PurviewType.PURVIEW_1)
    @RestUserlog(loggingType = LoggingType.TEST, userlog = "purview test")
    public RestResult<?> test() throws RestException {
        return RestResult.success("the purview test successfully");
    }

}
```

## Test Example

[rice-toolkit-example](https://github.com/NicheToolkit/rice-toolkit/tree/master/rice-toolkit-example)

## License

[Apache License](https://www.apache.org/licenses/LICENSE-2.0)

## Dependencies

[Rest-toolkit](https://github.com/NicheToolkit/rest-toolkit)

[Spring Boot](https://github.com/spring-projects/spring-boot)

[Lombok](https://github.com/projectlombok/lombok)

[PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
