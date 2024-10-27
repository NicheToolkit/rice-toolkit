# Model

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

# Entity

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

# Filter

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