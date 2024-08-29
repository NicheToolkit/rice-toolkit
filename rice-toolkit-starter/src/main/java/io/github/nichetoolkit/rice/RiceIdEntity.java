package io.github.nichetoolkit.rice;


public abstract class RiceIdEntity<E extends RiceIdEntity<E,M>,M extends RiceIdModel<M,E>> extends BaseIdEntity<E,M,String> {

    public RiceIdEntity() {
    }

    public RiceIdEntity(String id) {
        super(id);
    }

    public RiceIdEntity(Builder<E,M> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends RiceIdEntity<E,M>,M extends RiceIdModel<M,E>> extends BaseIdEntity.Builder<E,M,String> {

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
