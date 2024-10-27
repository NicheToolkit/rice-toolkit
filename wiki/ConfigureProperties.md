# Bean configuration

* prefix

>
> nichetoolkit.rice.service
>

* values

|          value          |     type     | defaultValue |                                                               description                                                                |
|:-----------------------:|:------------:|:------------:|:----------------------------------------------------------------------------------------------------------------------------------------:|
|     `unique-model`      |  `Boolean`   |   `false`    |                                     the switch of unique model check on rest service saving handle.                                      |
|     `dynamic-table`     |  `Boolean`   |   `false`    |                                           the switch of dynamic table on rest service handle.                                            |
|      `unique-name`      |  `Boolean`   |    `true`    |                        the switch of unique name check on rest service saving handle.(model name must be unique)                         |
|     `nonnull-name`      |  `Boolean`   |    `true`    |                       the switch of nonnull name check on rest service saving handle.(model name can not be null)                        |
|    `identity-invade`    |  `Boolean`   |   `false`    |                  the switch of invade model identity on rest service saving handle.(the model id can be external input)                  |
|    `identity-check`     |  `Boolean`   |    `true`    | the switch of model identity exist check on rest service updating handle.(the model of key not exist maybe to save when updating handle) |
|      `delete-mode`      | `DeleteMode` |   `remove`   |                      the delete mode of data on rest service delete handle.(the logical delete implementation mode)                      |
|      `before-skip`      |  `Boolean`   |    `true`    |                                     the switch of skip before handle on rest service delete handle.                                      |
|      `after-skip`       |  `Boolean`   |    `true`    |                                      the switch of skip after handle on rest service delete handle.                                      |
| `partition.query-size`  |  `Integer`   |    `2000`    |             the partition size of `in` sql usage on rest service query handle.(the size of sql param: in (p1,p2,p3...p2000))             |
|  `partition.save-size`  |  `Integer`   |    `500`     |                                        the partition size of  model on rest service save handle.                                         |
| `partition.delete-size` |  `Integer`   |    `1000`    |            the partition size of `in` sql usage on rest service delete handle.(the size of sql param: in (p1,p2,p3...p1000))             |
| `delete.accurate-judge` |  `Boolean`   |   `false`    |                             the switch of remove data with accurate judge sign on rest service query handle.                             |
|   `delete.logic-mode`   | `LogicMode`  |   `config`   |                      the logic mode of data on rest service remove handle.(the logical remove implementation mode)                       |
|  `delete.config-mark`   | `ConfigMark` |   `number`   |                                          the config mode of data on rest service logic handle.                                           |
|   `delete.auto-mark`    |  `AutoMark`  |  `identity`  |                                           the auto mode of data on rest service logic handle.                                            |
|  `delete.unmark-value`  |   `Object`   |     `1`      |                                           the unmark of normal data when logical remove model.                                           |
|   `delete.mark-value`   |   `Object`   |     `2`      |                                            the mark of delete data when logical remove model.                                            |

* properties

```properties
nichetoolkit.rice.service.unique-model=false
nichetoolkit.rice.service.dynamic-table=false
nichetoolkit.rice.service.identity-invade=false
nichetoolkit.rice.service.identity-check=true
nichetoolkit.rice.service.unique-name=true
nichetoolkit.rice.service.nonnull-name=true
nichetoolkit.rice.service.partition.query-size=2000
nichetoolkit.rice.service.partition.save-size=500
nichetoolkit.rice.service.partition.delete-size=1000
nichetoolkit.rice.service.before-skip=true
nichetoolkit.rice.service.after-skip=true
nichetoolkit.rice.service.delete-mode=remove
nichetoolkit.rice.service.delete.logic-mode=config
nichetoolkit.rice.service.delete.auto-mark=identity
nichetoolkit.rice.service.delete.config-mark=number
nichetoolkit.rice.service.delete.accurate-judge=true
nichetoolkit.rice.service.delete.unmark-value=
nichetoolkit.rice.service.delete.mark-value=
```

# Login configuration

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

# Serialize configuration

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