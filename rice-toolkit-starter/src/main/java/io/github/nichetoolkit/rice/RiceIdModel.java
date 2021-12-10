package io.github.nichetoolkit.rice;

/**
 * <p>ChiefModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceIdModel<M extends RiceIdModel<M,E>,E extends RiceIdEntity<E,M>> extends IdModel<String> implements RestModel<String,E> {

    public RiceIdModel() {
    }

    public RiceIdModel(String id) {
        super(id);
    }

    public RiceIdModel(RiceIdModel.Builder builder) {
        super(builder);
    }

    public static abstract class Builder extends IdModel.Builder<String> {

        public Builder() {
        }

        public RiceIdModel.Builder id(String id) {
            this.id = id;
            return this;
        }

        public abstract RiceIdModel<?,?> build();
    }
}
