package io.github.nichetoolkit.rice;


/**
 * <p>RestEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceIdEntity<E extends RiceIdEntity<E,M>,M extends RiceIdModel<M,E>> extends IdEntity<String> implements RestEntity<String,M> {

    public RiceIdEntity() {
    }

    public RiceIdEntity(String id) {
        super(id);
    }

    public RiceIdEntity(Builder builder) {
        super(builder);
    }

    public static abstract class Builder extends IdEntity.Builder<String> {

        public Builder() {
        }

        @Override
        public RiceIdEntity.Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public abstract RiceIdEntity<?,?> build();
    }
}
