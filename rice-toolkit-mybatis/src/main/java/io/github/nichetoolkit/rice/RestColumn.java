package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.SortType;
import io.github.nichetoolkit.rice.column.*;
import io.github.nichetoolkit.rice.table.RestProperties;
import io.github.nichetoolkit.rice.table.RestProperty;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestColumn</code>
 * <p>The rest column interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @see  io.github.nichetoolkit.rice.column.RestName
 * @see  io.github.nichetoolkit.rice.column.RestOrder
 * @see  io.github.nichetoolkit.rice.column.RestSelect
 * @see  io.github.nichetoolkit.rice.column.RestInsert
 * @see  io.github.nichetoolkit.rice.column.RestUpdate
 * @see  io.github.nichetoolkit.rice.column.RestSortType
 * @see  io.github.nichetoolkit.rice.column.RestJdbcType
 * @see  io.github.nichetoolkit.rice.column.RestForceInsert
 * @see  io.github.nichetoolkit.rice.column.RestForceUpdate
 * @see  io.github.nichetoolkit.rice.table.RestProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Indexed

@RestName
@RestOrder
@RestSelect
@RestInsert
@RestUpdate
@RestSortType
@RestJdbcType
@RestForceInsert
@RestForceUpdate
@RestProperties
public @interface RestColumn {

    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestName.class, attribute = "name")
    String value() default "";

    /**
     * <code>comment</code>
     * <p>The comment method.</p>
     * @return  {@link java.lang.String} <p>The comment return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestName.class, attribute = "comment")
    String comment() default "";

    /**
     * <code>order</code>
     * <p>The order method.</p>
     * @return int <p>The order return object is <code>int</code> type.</p>
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestOrder.class, attribute = "value")
    int order() default 0;

    /**
     * <code>sortType</code>
     * <p>The sort type method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.SortType} <p>The sort type return object is <code>SortType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.SortType
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestSortType.class, attribute = "type")
    SortType sortType() default SortType.NONE;

    /**
     * <code>priority</code>
     * <p>The priority method.</p>
     * @return int <p>The priority return object is <code>int</code> type.</p>
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestSortType.class, attribute = "priority")
    int priority() default 0;

    /**
     * <code>forceInsert</code>
     * <p>The force insert method.</p>
     * @return  {@link java.lang.String} <p>The force insert return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestForceInsert.class, attribute = "value")
    String forceInsert() default "";

    /**
     * <code>forceUpdate</code>
     * <p>The force update method.</p>
     * @return  {@link java.lang.String} <p>The force update return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestForceUpdate.class, attribute = "value")
    String forceUpdate() default "";

    /**
     * <code>jdbcType</code>
     * <p>The jdbc type method.</p>
     * @return  {@link org.apache.ibatis.type.JdbcType} <p>The jdbc type return object is <code>JdbcType</code> type.</p>
     * @see  org.apache.ibatis.type.JdbcType
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestJdbcType.class, attribute = "jdbcType")
    JdbcType jdbcType() default JdbcType.UNDEFINED;

    /**
     * <code>typeHandler</code>
     * <p>The type handler method.</p>
     * @return  {@link java.lang.Class} <p>The type handler return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestJdbcType.class, attribute = "typeHandler")
    Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;

    /**
     * <code>numericScale</code>
     * <p>The numeric scale method.</p>
     * @return  {@link java.lang.String} <p>The numeric scale return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestJdbcType.class, attribute = "numericScale")
    String numericScale() default "";

    /**
     * <code>properties</code>
     * <p>The properties method.</p>
     * @return  {@link io.github.nichetoolkit.rice.table.RestProperty} <p>The properties return object is <code>RestProperty</code> type.</p>
     * @see  io.github.nichetoolkit.rice.table.RestProperty
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.table.RestProperties.class, attribute = "properties")
    RestProperty[] properties() default {};

    /**
     * <code>select</code>
     * <p>The select method.</p>
     * @return boolean <p>The select return object is <code>boolean</code> type.</p>
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestSelect.class, attribute = "value")
    boolean select() default true;

    /**
     * <code>insert</code>
     * <p>The insert method.</p>
     * @return boolean <p>The insert return object is <code>boolean</code> type.</p>
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestInsert.class, attribute = "value")
    boolean insert() default true;

    /**
     * <code>update</code>
     * <p>The update method.</p>
     * @return boolean <p>The update return object is <code>boolean</code> type.</p>
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = io.github.nichetoolkit.rice.column.RestUpdate.class, attribute = "value")
    boolean update() default true;

    /**
     * <code>exclude</code>
     * <p>The exclude method.</p>
     * @return boolean <p>The exclude return object is <code>boolean</code> type.</p>
     */
    boolean exclude() default false;

    /**
     * <code>identityKey</code>
     * <p>The identity key method.</p>
     * @return boolean <p>The identity key return object is <code>boolean</code> type.</p>
     */
    boolean identityKey() default false;

    /**
     * <code>primaryKey</code>
     * <p>The primary key method.</p>
     * @return boolean <p>The primary key return object is <code>boolean</code> type.</p>
     */
    boolean primaryKey() default false;

    /**
     * <code>linkKey</code>
     * <p>The link key method.</p>
     * @return boolean <p>The link key return object is <code>boolean</code> type.</p>
     */
    boolean linkKey() default false;

    /**
     * <code>alertKey</code>
     * <p>The alert key method.</p>
     * @return boolean <p>The alert key return object is <code>boolean</code> type.</p>
     */
    boolean alertKey() default false;

    /**
     * <code>operateKey</code>
     * <p>The operate key method.</p>
     * @return boolean <p>The operate key return object is <code>boolean</code> type.</p>
     */
    boolean operateKey() default false;

    /**
     * <code>uniqueKey</code>
     * <p>The unique key method.</p>
     * @return boolean <p>The unique key return object is <code>boolean</code> type.</p>
     */
    boolean uniqueKey() default false;

    /**
     * <code>unionKey</code>
     * <p>The union key method.</p>
     * @return boolean <p>The union key return object is <code>boolean</code> type.</p>
     */
    boolean unionKey() default false;

    /**
     * <code>logicKey</code>
     * <p>The logic key method.</p>
     * @return boolean <p>The logic key return object is <code>boolean</code> type.</p>
     */
    boolean logicKey() default false;
}
