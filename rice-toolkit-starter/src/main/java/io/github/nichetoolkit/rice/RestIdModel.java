package io.github.nichetoolkit.rice;

import lombok.experimental.SuperBuilder;

/**
 * <code>RestIdModel</code>
 * <p>The rest id model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>The generic parameter is <code>RestIdModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>The generic parameter is <code>RestIdEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdEntity
 * @see io.github.nichetoolkit.rice.DefaultIdModel
 * @see lombok.experimental.SuperBuilder
 * @since Jdk17
 */
@SuperBuilder(builderMethodName = "ofRestIdBuilder")
public abstract class RestIdModel<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>> extends DefaultIdModel<M, E, String> {

    /**
     * <code>RestIdModel</code>
     * <p>Instantiates a new rest id model.</p>
     */
    public RestIdModel() {
    }

    /**
     * <code>RestIdModel</code>
     * <p>Instantiates a new rest id model.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestIdModel(String id) {
        super(id);
    }

}
