package io.github.nichetoolkit.mybatis.fickle.defaults;

import io.github.nichetoolkit.mybatis.fickle.FickleField;
import io.github.nichetoolkit.mybatis.fickle.FickleType;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * <code>FickleEmptyField</code>
 * <p>The fickle empty field class.</p>
 * @see  io.github.nichetoolkit.mybatis.fickle.FickleField
 * @see  lombok.Data
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Data
public class FickleEmptyField implements FickleField<Object> {
    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see  java.lang.String
     */
    private String key;

    /**
     * <code>FickleEmptyField</code>
     * <p>Instantiates a new fickle empty field.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public FickleEmptyField(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public void setKey(@NonNull String key) {
        this.key = key;
    }

    @Override
    public String getAlias() {
        return "";
    }

    @Override
    public String getName() {
        return this.key;
    }

    @NonNull
    @Override
    public FickleType getType() {
        return new FickleObjectType();
    }

    @Override
    public String getComment() {
        return "";
    }

    @Override
    public Object getValue() {
        return null;
    }
}
