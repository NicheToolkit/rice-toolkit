package io.github.nichetoolkit.rice;

import lombok.experimental.SuperBuilder;

/**
 * <code>DefaultInfoModel</code>
 * <p>The default info model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.DefaultInfoModel} <p>The generic parameter is <code>DefaultInfoModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.DefaultInfoEntity} <p>The generic parameter is <code>DefaultInfoEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultInfoEntity
 * @see io.github.nichetoolkit.rice.InfoModel
 * @see io.github.nichetoolkit.rice.RestModel
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofDefaultInfoBuilder")
public abstract class DefaultInfoModel<M extends DefaultInfoModel<M, E, I>, E extends DefaultInfoEntity<E, M, I>, I> extends InfoModel<I> implements RestModel<I, E> {

    /**
     * <code>DefaultInfoModel</code>
     * <p>Instantiates a new default info model.</p>
     */
    public DefaultInfoModel() {
    }

    /**
     * <code>DefaultInfoModel</code>
     * <p>Instantiates a new default info model.</p>
     * @param id I <p>The id parameter is <code>I</code> type.</p>
     */
    public DefaultInfoModel(I id) {
        super(id);
    }

    /**
     * <code>DefaultInfoModel</code>
     * <p>Instantiates a new default info model.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param description {@link java.lang.String} <p>The description parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DefaultInfoModel(String name, String description) {
        super(name, description);
    }

}
