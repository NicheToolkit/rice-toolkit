package io.github.nichetoolkit.mybatis;

import io.github.nichetoolkit.mybatis.table.*;
import io.github.nichetoolkit.mybatis.enums.StyleType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestTable</code>
 * <p>The rest table interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @see  io.github.nichetoolkit.mybatis.table.RestEntity
 * @see  io.github.nichetoolkit.mybatis.table.RestTableStyle
 * @see  io.github.nichetoolkit.mybatis.table.RestIgnores
 * @see  io.github.nichetoolkit.mybatis.table.RestResultMap
 * @see  io.github.nichetoolkit.mybatis.table.RestProperties
 * @see  io.github.nichetoolkit.mybatis.table.RestExcludes
 * @see  io.github.nichetoolkit.mybatis.table.RestUnionKeys
 * @see  io.github.nichetoolkit.mybatis.table.RestLinkKeys
 * @see  io.github.nichetoolkit.mybatis.table.RestUniqueKeys
 * @see  io.github.nichetoolkit.mybatis.table.RestAlertKeys
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Indexed

@RestEntity
@RestTableStyle
@RestIgnores
@RestResultMap
@RestProperties
@RestExcludes
@RestUnionKeys
@RestLinkKeys
@RestUniqueKeys
@RestAlertKeys
public @interface RestTable {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "name")
    String value() default "";

    /**
     * <code>comment</code>
     * <p>The comment method.</p>
     * @return  {@link java.lang.String} <p>The comment return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "comment")
    String comment() default "";

    /**
     * <code>alias</code>
     * <p>The alias method.</p>
     * @return  {@link java.lang.String} <p>The alias return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "alias")
    String alias() default "";

    /**
     * <code>entityType</code>
     * <p>The entity type method.</p>
     * @return  {@link java.lang.Class} <p>The entity type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "entityType")
    Class<?> entityType() default Object.class;

    /**
     * <code>identityType</code>
     * <p>The identity type method.</p>
     * @return  {@link java.lang.Class} <p>The identity type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "identityType")
    Class<?> identityType() default Object.class;

    /**
     * <code>linkageType</code>
     * <p>The linkage type method.</p>
     * @return  {@link java.lang.Class} <p>The linkage type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "linkageType")
    Class<?> linkageType() default Object.class;

    /**
     * <code>alertnessType</code>
     * <p>The alertness type method.</p>
     * @return  {@link java.lang.Class} <p>The alertness type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "alertnessType")
    Class<?> alertnessType() default Object.class;

    /**
     * <code>alertnessType</code>
     * <p>The alertness type method.</p>
     * @return  {@link java.lang.Class} <p>The alertness type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestEntity.class, attribute = "ficklenessType")
    Class<?> ficklenessType() default Object.class;

    /**
     * <code>catalog</code>
     * <p>The catalog method.</p>
     * @return  {@link java.lang.String} <p>The catalog return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestTableStyle.class, attribute = "catalog")
    String catalog() default "";

    /**
     * <code>schema</code>
     * <p>The schema method.</p>
     * @return  {@link java.lang.String} <p>The schema return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestTableStyle.class, attribute = "schema")
    String schema() default "";

    /**
     * <code>styleName</code>
     * <p>The style name method.</p>
     * @return  {@link java.lang.String} <p>The style name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestTableStyle.class, attribute = "name")
    String styleName() default "";

    /**
     * <code>styleType</code>
     * <p>The style type method.</p>
     * @return  {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The style type return object is <code>StyleType</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.enums.StyleType
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestTableStyle.class, attribute = "type")
    StyleType styleType() default StyleType.LOWER_UNDERLINE;

    /**
     * <code>unionKeys</code>
     * <p>The union keys method.</p>
     * @return  {@link java.lang.String} <p>The union keys return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestUnionKeys.class, attribute = "value")
    String[] unionKeys() default {};

    /**
     * <code>alertKeys</code>
     * <p>The alert keys method.</p>
     * @return  {@link java.lang.String} <p>The alert keys return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestAlertKeys.class, attribute = "value")
    String[] alertKeys() default {};

    /**
     * <code>linkKeys</code>
     * <p>The link keys method.</p>
     * @return  {@link java.lang.String} <p>The link keys return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestLinkKeys.class, attribute = "value")
    String[] linkKeys() default {};

    /**
     * <code>uniqueKeys</code>
     * <p>The unique keys method.</p>
     * @return  {@link java.lang.String} <p>The unique keys return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestUniqueKeys.class, attribute = "value")
    String[] uniqueKeys() default {};

    /**
     * <code>excludeFields</code>
     * <p>The exclude fields method.</p>
     * @return  {@link java.lang.String} <p>The exclude fields return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestExcludes.class, attribute = "fields")
    String[] excludeFields() default {};

    /**
     * <code>excludeFieldTypes</code>
     * <p>The exclude field types method.</p>
     * @return  {@link java.lang.Class} <p>The exclude field types return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestExcludes.class, attribute = "fieldTypes")
    Class<?>[] excludeFieldTypes() default {};

    /**
     * <code>excludeSuperClasses</code>
     * <p>The exclude super classes method.</p>
     * @return  {@link java.lang.Class} <p>The exclude super classes return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestExcludes.class, attribute = "superClasses")
    Class<?>[] excludeSuperClasses() default {};

    /**
     * <code>ignoreFields</code>
     * <p>The ignore fields method.</p>
     * @return  {@link java.lang.String} <p>The ignore fields return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestIgnores.class, attribute = "fields")
    String[] ignoreFields() default {};

    /**
     * <code>ignoreFieldTypes</code>
     * <p>The ignore field types method.</p>
     * @return  {@link java.lang.Class} <p>The ignore field types return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestIgnores.class, attribute = "fieldTypes")
    Class<?>[] ignoreFieldTypes() default {};

    /**
     * <code>ignoreSuperClasses</code>
     * <p>The ignore super classes method.</p>
     * @return  {@link java.lang.Class} <p>The ignore super classes return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestIgnores.class, attribute = "superClasses")
    Class<?>[] ignoreSuperClasses() default {};

    /**
     * <code>resultMap</code>
     * <p>The result map method.</p>
     * @return  {@link java.lang.String} <p>The result map return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestResultMap.class, attribute = "name")
    String resultMap() default "";

    /**
     * <code>autoResultMap</code>
     * <p>The auto result map method.</p>
     * @return boolean <p>The auto result map return object is <code>boolean</code> type.</p>
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestResultMap.class, attribute = "autoResultMap")
    boolean autoResultMap() default true;

    /**
     * <code>properties</code>
     * <p>The properties method.</p>
     * @return  {@link io.github.nichetoolkit.mybatis.table.RestProperty} <p>The properties return object is <code>RestProperty</code> type.</p>
     * @see  io.github.nichetoolkit.mybatis.table.RestProperty
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor(annotation = RestProperties.class, attribute = "properties")
    RestProperty[] properties() default {};
}
