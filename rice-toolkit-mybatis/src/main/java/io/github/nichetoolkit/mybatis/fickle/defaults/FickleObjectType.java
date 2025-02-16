package io.github.nichetoolkit.mybatis.fickle.defaults;

import io.github.nichetoolkit.mybatis.fickle.FickleType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.lang.NonNull;

/**
 * <code>FickleObjectType</code>
 * <p>The fickle object type class.</p>
 * @see  io.github.nichetoolkit.mybatis.fickle.FickleType
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class FickleObjectType implements FickleType {
    /**
     * <code>key</code>
     * <p>The <code>key</code> field.</p>
     */
    private int key = 9999;

    @Override
    public String getAlias() {
        return "";
    }

    @Override
    public JdbcType getJdbcType() {
        return JdbcType.JAVA_OBJECT;
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }

    @Override
    public String name() {
        return "EmptyType";
    }

    @NonNull
    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public void setKey(@NonNull Integer key) {
        this.key = key;
    }
}
