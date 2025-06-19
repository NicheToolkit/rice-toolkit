package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rice.enums.OperateType;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <code>RestInfoEntity</code>
 * <p>The rest info entity class.</p>
 * @param <E>  {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>The generic parameter is <code>RestInfoEntity</code> type.</p>
 * @param <M>  {@link io.github.nichetoolkit.rice.RestInfoModel} <p>The generic parameter is <code>RestInfoModel</code> type.</p>
 * @see  io.github.nichetoolkit.rice.RestInfoModel
 * @see  io.github.nichetoolkit.rice.DefaultInfoEntity
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofRestInfoBuilder")
public abstract class RestInfoEntity<E extends RestInfoEntity<E, M>, M extends RestInfoModel<M, E>> extends DefaultInfoEntity<E, M, String> {

    /**
     * <code>RestInfoEntity</code>
     * <p>Instantiates a new rest info entity.</p>
     */
    public RestInfoEntity() {
    }

    /**
     * <code>RestInfoEntity</code>
     * <p>Instantiates a new rest info entity.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public RestInfoEntity(String id) {
        super(id);
    }

    /**
     * <code>RestInfoEntity</code>
     * <p>Instantiates a new rest info entity.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public RestInfoEntity(String id, String name) {
        super(id, name);
    }

}
