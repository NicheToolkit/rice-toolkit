package io.github.nichetoolkit.rice.stereotype.mybatis;

import io.github.nichetoolkit.rice.enums.SortType;
import io.github.nichetoolkit.rice.stereotype.mybatis.column.*;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <p>RestColumn</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Indexed
@RestName
@RestExclude
@RestInsert
@RestUpdate
@RestSelect
@RestPrimaryKey
@RestUnionKey
@RestLinkKey
@RestAlertKey
@RestUniqueKey
@RestSortType
@RestJdbcType
@RestProperties
public @interface RestColumn {
    /**
     * 列名，默认空时使用字段名
     */
    @AliasFor(annotation = RestName.class, attribute = "name")
    String value() default "";

    /**
     * 备注，仅用于在注解上展示，不用于任何其他处理
     */
    @AliasFor(annotation = RestName.class, attribute = "remark")
    String remark() default "";

    /**
     * 标记字段是否 用于主键字段
     */
    @AliasFor(annotation = RestPrimaryKey.class, attribute = "value")
    boolean primaryKey() default false;

    /**
     * 标记字段是否 用于联合主键字段
     */
    @AliasFor(annotation = RestLinkKey.class, attribute = "value")
    boolean linkKey() default false;


    /**
     * 标记字段是否 用于联合主键字段
     */
    @AliasFor(annotation = RestAlertKey.class, attribute = "value")
    boolean alertKey() default false;

    /**
     * 标记字段是否 用于联合主键字段
     */
    @AliasFor(annotation = RestUnionKey.class, attribute = "value")
    boolean unionKey() default false;

    /**
     * 用于联合主键的顺序，数值越小优先级越高
     */
    @AliasFor(annotation = RestUnionKey.class, attribute = "index")
    int unionIndex() default 0;

    /**
     * 排序方式
     */
    @AliasFor(annotation = RestSortType.class, attribute = "type")
    SortType sortType() default SortType.NONE;

    /**
     * 排序的优先级，数值越小优先级越高
     */
    @AliasFor(annotation = RestSortType.class, attribute = "priority")
    int priority() default 0;

    /**
     * 排除字段 选项
     */
    @AliasFor(annotation = RestExclude.class, attribute = "exclude")
    boolean exclude() default false;

    /**
     * 可查询
     */
    @AliasFor(annotation = RestSelect.class, attribute = "select")
    boolean select() default true;

    /**
     * 可插入
     */
    @AliasFor(annotation = RestInsert.class, attribute = "insert")
    boolean insert() default true;

    /**
     * 可更新
     */
    @AliasFor(annotation = RestUpdate.class, attribute = "update")
    boolean update() default true;

    /**
     * 数据库类型 {, jdbcType=VARCHAR}
     */
    @AliasFor(annotation = RestJdbcType.class, attribute = "jdbcType")
    JdbcType jdbcType() default JdbcType.UNDEFINED;

    /**
     * 类型处理器 {, typeHandler=XXTypeHandler}
     */
    @AliasFor(annotation = RestJdbcType.class, attribute = "typeHandler")
    Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;

    /**
     * 小数位数 {, numericScale=2}
     */
    @AliasFor(annotation = RestJdbcType.class, attribute = "numericScale")
    String numericScale() default "";

    /**
     * 属性配置
     */
    @AliasFor(annotation = RestProperties.class, attribute = "properties")
    RestProperty[] properties() default {};
}
