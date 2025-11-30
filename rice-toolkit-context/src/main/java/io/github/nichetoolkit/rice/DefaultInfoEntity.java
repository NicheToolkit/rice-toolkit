package io.github.nichetoolkit.rice;

import lombok.experimental.SuperBuilder;

/**
 * <code>DefaultInfoEntity</code>
 * <p>The default info entity class.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>The generic parameter is <code>DefaultInfoEntity</code> type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultInfoModel} <p>The generic parameter is <code>DefaultInfoModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultInfoModel
 * @see io.github.nichetoolkit.rice.InfoEntity
 * @see io.github.nichetoolkit.rice.RestEntity
 * @see lombok.experimental.SuperBuilder
 * @since Jdk17
 */
@SuperBuilder(builderMethodName = "ofDefaultInfoBuilder")
public abstract class DefaultInfoEntity<E extends DefaultInfoEntity<E,M,I>,M extends DefaultInfoModel<M,E,I>,I> extends InfoEntity<I> implements RestEntity<I,M> {

    /**
     * <code>DefaultInfoEntity</code>
     * <p>Instantiates a new default info entity.</p>
     */
    public DefaultInfoEntity() {
    }

    /**
     * <code>DefaultInfoEntity</code>
     * <p>Instantiates a new default info entity.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public DefaultInfoEntity(I id) {
        super(id);
    }

    /**
     * <code>DefaultInfoEntity</code>
     * <p>Instantiates a new default info entity.</p>
     * @param id   I <p>The id parameter is <code>I</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DefaultInfoEntity(I id, String name) {
        super(id, name);
    }

}
