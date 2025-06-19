package io.github.nichetoolkit.rice;


import lombok.experimental.SuperBuilder;

/**
 * <code>RestIdEntity</code>
 * <p>The rest id entity class.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestIdEntity} <p>The generic parameter is <code>RestIdEntity</code> type.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestIdModel} <p>The generic parameter is <code>RestIdModel</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestIdModel
 * @see io.github.nichetoolkit.rice.DefaultIdEntity
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofRestIdBuilder")
public abstract class RestIdEntity<E extends RestIdEntity<E, M>, M extends RestIdModel<M, E>> extends DefaultIdEntity<E, M, String> {

    /**
     * <code>RestIdEntity</code>
     * <p>Instantiates a new rest id entity.</p>
     */
    public RestIdEntity() {
    }

    /**
     * <code>RestIdEntity</code>
     * <p>Instantiates a new rest id entity.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestIdEntity(String id) {
        super(id);
    }

}
