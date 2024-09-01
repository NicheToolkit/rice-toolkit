package io.github.nichetoolkit.rice;


public abstract class RestIdEntity<E extends RestIdEntity<E,M>,M extends RestIdModel<M,E>> extends DefaultIdEntity<E,M,String> {

    public RestIdEntity() {
    }

    public RestIdEntity(String id) {
        super(id);
    }

    public RestIdEntity(Builder<E,M> builder) {
        super(builder);
    }

    public static abstract class Builder<E extends RestIdEntity<E,M>,M extends RestIdModel<M,E>> extends DefaultIdEntity.Builder<E,M,String> {

        public Builder() {
        }

        @Override
        public RestIdEntity.Builder<E,M> id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public abstract RestIdEntity<E,M> build();
    }
}
