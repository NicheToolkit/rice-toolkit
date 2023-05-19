package io.github.nichetoolkit.rice;


/**
 * <p>RiceIdEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceIdEntity<E extends RiceIdEntity<E,M>,M extends RiceIdModel<M,E>> extends RestIdEntity<E,M,String> {

    public RiceIdEntity() {
    }

    public RiceIdEntity(String id) {
        super(id);
    }

    public RiceIdEntity(Builder<E,M> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends RiceIdEntity<E,M>,M extends RiceIdModel<M,E>> extends RestIdEntity.Builder<E,M,String> {

        public Builder() {
        }

        @Override
        public RiceIdEntity.Builder<E,M> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public abstract RiceIdEntity<E,M> build();
    }
}
