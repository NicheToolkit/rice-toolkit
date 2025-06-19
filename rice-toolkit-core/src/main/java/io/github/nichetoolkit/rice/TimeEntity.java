package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.mybatis.consts.ScriptConstants;
import io.github.nichetoolkit.mybatis.column.RestForceInsert;
import io.github.nichetoolkit.mybatis.column.RestForceUpdate;
import io.github.nichetoolkit.mybatis.column.RestUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * <code>TimeEntity</code>
 * <p>The time entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.OperateEntity
 * @see lombok.Setter
 * @see lombok.Getter
 * @see java.lang.SuppressWarnings
 * @see lombok.experimental.SuperBuilder
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Setter
@Getter
@SuppressWarnings("WeakerAccess")
@SuperBuilder(builderMethodName = "ofTimeBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeEntity extends OperateEntity {
    /**
     * <code>createTime</code>
     * {@link java.util.Date} <p>The <code>createTime</code> field.</p>
     * @see java.util.Date
     * @see io.github.nichetoolkit.mybatis.column.RestUpdate
     * @see io.github.nichetoolkit.mybatis.column.RestForceInsert
     */
    @RestUpdate(false)
    @RestForceInsert(ScriptConstants.NOW)
    protected Date createTime;
    /**
     * <code>updateTime</code>
     * {@link java.util.Date} <p>The <code>updateTime</code> field.</p>
     * @see java.util.Date
     * @see io.github.nichetoolkit.mybatis.column.RestForceInsert
     * @see io.github.nichetoolkit.mybatis.column.RestForceUpdate
     */
    @RestForceInsert(ScriptConstants.NOW)
    @RestForceUpdate(ScriptConstants.NOW)
    protected Date updateTime;

    /**
     * <code>TimeEntity</code>
     * <p>Instantiates a new time entity.</p>
     */
    public TimeEntity() {
    }

}
