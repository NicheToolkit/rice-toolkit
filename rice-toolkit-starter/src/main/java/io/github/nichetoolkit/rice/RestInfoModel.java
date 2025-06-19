package io.github.nichetoolkit.rice;

import lombok.experimental.SuperBuilder;

/**
 * <code>RestInfoModel</code>
 * <p>The rest info model class.</p>
 * @param <M> {@link io.github.nichetoolkit.rice.RestInfoModel} <p>The generic parameter is <code>RestInfoModel</code> type.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.RestInfoEntity} <p>The generic parameter is <code>RestInfoEntity</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoEntity
 * @see io.github.nichetoolkit.rice.DefaultInfoModel
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofRestInfoBuilder")
public abstract class RestInfoModel<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>> extends DefaultInfoModel<M, E, String> {

    /**
     * <code>RestInfoModel</code>
     * <p>Instantiates a new rest info model.</p>
     */
    public RestInfoModel() {
    }

    /**
     * <code>RestInfoModel</code>
     * <p>Instantiates a new rest info model.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestInfoModel(String id) {
        super(id);
    }

    /**
     * <code>RestInfoModel</code>
     * <p>Instantiates a new rest info model.</p>
     * @param name        {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param description {@link java.lang.String} <p>The description parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestInfoModel(String name, String description) {
        super(name, description);
    }
}
