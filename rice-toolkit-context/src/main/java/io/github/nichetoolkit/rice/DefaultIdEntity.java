package io.github.nichetoolkit.rice;


import lombok.experimental.SuperBuilder;

/**
 * <code>DefaultIdEntity</code>
 * <p>The default id entity class.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultIdEntity} <p>The generic parameter is <code>DefaultIdEntity</code> type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultIdModel} <p>The generic parameter is <code>DefaultIdModel</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultIdModel
 * @see io.github.nichetoolkit.rice.IdEntity
 * @see io.github.nichetoolkit.rice.RestEntity
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofDefaultIdBuilder")
public abstract class DefaultIdEntity<E extends DefaultIdEntity<E,M,I>, M extends DefaultIdModel<M,E,I>,I> extends IdEntity<I> implements RestEntity<I,M> {

    /**
     * <code>DefaultIdEntity</code>
     * <p>Instantiates a new default id entity.</p>
     */
    public DefaultIdEntity() {
    }

    /**
     * <code>DefaultIdEntity</code>
     * <p>Instantiates a new default id entity.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public DefaultIdEntity(I id) {
        super(id);
    }

}
