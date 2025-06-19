package io.github.nichetoolkit.rice;

import lombok.experimental.SuperBuilder;

/**
 * <code>DefaultIdModel</code>
 * <p>The default id model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultIdModel} <p>The generic parameter is <code>DefaultIdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>The generic parameter is <code>DefaultIdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultIdEntity
 * @see io.github.nichetoolkit.rice.IdModel
 * @see io.github.nichetoolkit.rice.RestModel
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofDefaultIdBuilder")
public abstract class DefaultIdModel<M extends DefaultIdModel<M,E,I>,E extends DefaultIdEntity<E,M,I>,I> extends IdModel<I> implements RestModel<I,E> {

    /**
     * <code>DefaultIdModel</code>
     * <p>Instantiates a new default id model.</p>
     */
    public DefaultIdModel() {
    }

    /**
     * <code>DefaultIdModel</code>
     * <p>Instantiates a new default id model.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public DefaultIdModel(I id) {
        super(id);
    }

}
