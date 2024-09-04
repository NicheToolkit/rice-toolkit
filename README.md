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

|             value             |     type     | defaultValue |                                                             description                                                             |
|:-----------------------------:|:------------:|:------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|
|    `model.unique-enabled`     |  `Boolean`   |   `false`    |                                   the switch of unique model check on rest service saving handle.                                   |
| `model.dynamic-table-enabled` |  `Boolean`   |   `false`    |                                         the switch of dynamic table on rest service handle.                                         |
|     `name.unique-enabled`     |  `Boolean`   |    `true`    |                      the switch of unique name check on rest service saving handle.(model name must be unique)                      |
|    `name.nonnull-enabled`     |  `Boolean`   |    `true`    |                     the switch of nonnull name check on rest service saving handle.(model name can not be null)                     |
|     `key.invade-enabled`      |  `Boolean`   |   `false`    |                  the switch of invade model key on rest service saving handle.(the model id can be external input)                  |
|      `key.exist-enabled`      |  `Boolean`   |    `true`    | the switch of model key exist check on rest service updating handle.(the model of key not exist maybe to save when updating handle) |
|    `partition.query-size`     |  `Integer`   |    `2000`    |          the partition size of `in` sql usage on rest service query handle.(the size of sql param: in (p1,p2,p3...p2000))           |
|     `partition.save-size`     |  `Integer`   |    `500`     |                                      the partition size of  model on rest service save handle.                                      |
|    `partition.delete-size`    |  `Integer`   |    `1000`    |          the partition size of `in` sql usage on rest service delete handle.(the size of sql param: in (p1,p2,p3...p1000))          |
|     `delete.delete-mode`      | `DeleteMode` |    `none`    |                   the delete mode of data on rest service delete handle.(the logical delete implementation mode)                    |
|     `delete.remove-mode`      | `RemoveMode` |   `number`   |                   the remove mode of data on rest service remove handle.(the logical remove implementation mode)                   |
|     `delete.before-skip`      |  `Boolean`   |    `true`    |                               the switch of skip delete before handle on rest service delete handle.                                |
|      `delete.after-skip`      |  `Boolean`   |    `true`    |                                the switch of skip delete after handle on rest service delete handle.                                |
|     `delete.pinpoint-sign`     |  `Boolean`   |   `false`    |                               the switch of remove data with pinpoint sign on rest service query handle.                               |
|     `delete.boolean-sign`     |  `Boolean`   |    `true`    |                                   the sign of delete data when logical remove model is `boolean`.                                   |
|    `delete.boolean-value`     |  `Boolean`   |   `false`    |                                  the value of default data when logical remove model is `boolean`.                                  |
|     `delete.number-sign`      |  `Integer`   |     `2`      |                                   the sign of delete data when logical remove model is `number`.                                    |
|     `delete.number-value`     |  `Integer`   |     `1`      |                                  the value of default data when logical remove model is `number`.                                   |

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
nichetoolkit.rice.bean.delete.pinpoint-sign=false
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

|       name       |                                    typeParams                                    |                                                        description                                                        |
|:----------------:|:--------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------:|
|    SaveModel     |                                                                                  |                               the model is used to keep `SaveType` and `logicSign` fields.                                |
|   OperateModel   |                                                                                  |                                      the model is used to keep `OperateType` field.                                       |
|    TimeModel     |                                                                                  |                              the model is used to keep `createTime` and `updateTime` field.                               |
|     IdModel      |                                      `<I>`                                       |                         the model is used to keep `id` field，and the `id` type can be any object.                         |
|    InfoModel     |                                      `<I>`                                       |                                the model is used to keep `name` and `description` fields.                                 |
|  DefaultIdModel  |      `<M extends DefaultIdModel<M,E,I>,E extends DefaultIdEntity<E,M,I>,I>`      |  the children models of  `DefaultIdModel` must be implement the `toEntity()` method，and the `id` type can be any object.  |
| DefaultInfoModel | `<M extends DefaultInfoModel<M, E, I>, E extends DefaultInfoEntity<E, M, I>, I>` | the children models of  `DefaultInfoModel` must be implement the `toEntity()` method，and the `id` type can be any object. |
|   RestIdModel    |          `<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>>`           |  the children models of  `RestIdModel` must be implement the `toEntity()` method，and the `id` type is default `String`.   |
|  RestInfoModel   |        `<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>>`         | the children models of  `RestInfoModel` must be implement the `toEntity()` method，and the `id` type is default `String`.  |

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

|       name        |                                 typeParams                                 |                                                         description                                                         |
|:-----------------:|:--------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|
|   OperateEntity   |                                                                            |                                the entity is used to keep `operate` and `logicSign` fields.                                 |
|    TimeEntity     |                                                                            |                              the entity is used to keep `createTime` and `updateTime` fields.                               |
|     IdEntity      |                                   `<I>`                                    |                         the entity is used to keep `id` field，and the `id` type can be any object.                          |
|    InfoEntity     |                                   `<I>`                                    |                                 the entity is used to keep `name` and `description` fields.                                 |
|  DefaultIdEntity  |  `<E extends DefaultIdEntity<E,M,I>, M extends DefaultIdModel<M,E,I>,I>`   |  the children entities of  `DefaultIdEntity` must be implement the `toModel()` method，and the `id` type can be any object.  |
| DefaultInfoEntity | `<E extends DefaultInfoEntity<E,M,I>,M extends DefaultInfoModel<M,E,I>,I>` | the children entities of  `DefaultInfoEntity` must be implement the `toModel()` method，and the `id` type can be any object. |
|   RestIdEntity    |         `<E extends RestIdEntity<E,M>,M extends RestIdModel<M,E>>`         |  the children entities of  `RestIdEntity` must be implement the `toModel()` method，and the `id` type is default `String`.   |
|  RestInfoEntity   |     `<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>>`      | the children entities of  `RestInfoEntity` must be implement the `toModel()` method，and the `id` type is default `String`.  |

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

|     name      | typeParams |                                                                                             description                                                                                              |
|:-------------:|:----------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|  PageFilter   |            |                                the filter is used to receive `pageNum` and `pageSize` fields.(the `loadLastPage` is used to load the last page data of query handle)                                 |
|  SortFilter   |            |                                                the filter is used to receive `sorts` field.(the `RestSort` is used to sort the data of query handle)                                                 |
| OperateFilter |            |                                 the filter is used to receive `operate` and `operates` fields.(the `remove` is used to load the logical delete data of query handle)                                 |
|  TableFilter  |   `<K>`    |                 the filter is used to receive `tablekey` field，and the `tablekey` type can be any object.(the `tablekey` is used to load the data of query handle on dynamic table )                 |
|   IdFilter    |  `<I, K>`  |                                                       the filter is used to receive `id` and `ids` fields.and the `id` type can be any object.                                                       |
|  TimeFilter   |  `<I, K>`  |                                                                   the filter is used to receive `startTime` and `endTime` fields.                                                                    |
|  JsonbFilter  |  `<I, K>`  | the filter is used to receive `contrasts`、`ranges`、`equals`  and `contains` fields.(the `ContrastRule`、`RangeRule`、`EqualRule` and `ContainRule` is used to filter the `jsonb` data of query handle) |
|  NameFilter   |  `<I, K>`  |                                                                       the filter is used to receive `name` and `names` fields.                                                                       |
| DefaultFilter |  `<I, K>`  |                                              the children filters of  `DefaultFilter` can build `SQL` with `toRemoveSql()` and `toQuerySql()` methods.                                               |
|  RestFilter   |            |                                                                           the `id` and `tablekey`type is default `String`.                                                                           |

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
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,E extends IdEntity&lt;I&gt;&gt;</code></td>
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
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,E extends IdEntity&lt;I&gt;&gt;</code></td>
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
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,E extends IdEntity&lt;I&gt;&gt;</code></td>
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
<td rowspan=8 style="vertical-align: middle;"><code>&lt;I,M extends IdModel&lt;I&gt;&gt;</code></td>
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
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,M extends IdModel&lt;I&gt;, E extends IdEntity&lt;I&gt;&gt;</code></td>
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
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>FilterAdvice</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;K, I, F extends IdFilter&lt;I, K&gt;&gt;</code></td>
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
          return filter.toTimeSql("time").addSorts("id").toSql();
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
<td><code>removeById(@Param("id") I id, @Param("sign") String sign)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>removeAll(@Param("idList") Collection&lt;I&gt; idList, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>removeDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>removeAllByWhere(@Param("whereSql") String whereSql, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>removeDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql,@Param("sign") String sign)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>SaveMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;,I&gt;</code></td>
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
<td rowspan=6 style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;,I&gt;</code></td>
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
<td style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;,I&gt;</code></td>
<td></td>
<td style="vertical-align: middle;">the mapper is default super mapper.</td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>InfoMapper</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;,I&gt;</code></td>
<td><code>findByName(@Param("name") String name, @Param("sign") String sign)</code></td>
<td rowspan=8 style="vertical-align: middle;">the mapper is used to handle <code>find</code> and <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByName(@Param("tablename") String tablename, @Param("name") String name, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>findByNameAndNotId(@Param("name") String name, @Param("id") I id, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>findByEntity(@Param("entity") E entity, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>findDynamicByEntity(@Param("tablename") String tablename, @Param("entity") E entity, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>findByEntityAndNotId(@Param("entity") E entity, @Param("id") I id, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>findDynamicByEntityAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id, @Param("sign") String sign)</code></td>
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
<td><code>removeByLinkId(@Param("linkId") I linkId, @Param("sign") String sign)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") I linkId, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>removeAllByLinkIds(@Param("linkIdList") Collection&lt;I&gt; linkIdList, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>removeDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;I&gt; linkIdList, @Param("sign") String sign)</code></td>
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
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertBiFieldMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>alertBiFieldById(@Param("id") I id, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertDynamicBiFieldById(@Param("tablename") String tablename, @Param("id") I id, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key)</code></td>
</tr>
<tr>
<td><code>alertBiFieldAll(@Param("idList") Collection&lt;I&gt; idList, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key)</code></td>
</tr>
<tr>
<td><code>alertDynamicBiFieldAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("field") String field, @Param("biField") String biField, @Param("key") Integer key)</code></td>
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
<td rowspan=4 style="vertical-align: middle;"><code>LoadMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;, I&gt;</code></td>
<td><code>findByLoadId(@Param("id") I id, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>find</code> and <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByLoadId(@Param("tablename") String tablename, @Param("id") I id, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findAllLoad(@Param("idList") Collection&lt;I&gt; idList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findDynamicAllLoad(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>LoadFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;, I&gt;</code></td>
<td><code>findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicAllByLoadWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>FilterMapper</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;,F extends IdFilter&lt;I, K&gt;,I,K&gt;</code></td>
<td><code>operateAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate)</code></td>
<td rowspan=8 style="vertical-align: middle;">the mapper is used to handle <code>operateAll</code>、<code>removeAll</code>、<code>findAll</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>operateDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>removeAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>removeDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("sign") String sign)</code></td>
</tr>
<tr>
<td><code>findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td><code>findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td><code>deleteAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td><code>deleteDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>FieldFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends IdEntity&lt;I&gt;, I&gt;</code></td>
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
  public interface SimpleMapper extends RestInfoMapper<SimpleEntity>, Mapper<SimpleEntity,String> {
  }
  ```
#### Service

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