package io.github.nichetoolkit.rice;

/**
 * <p>ChiefModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceInfoModel<M extends RiceInfoModel<M,E>,E extends RiceInfoEntity<E,M>> extends InfoModel<String> implements RestModel<String,E> {

    public RiceInfoModel() {
    }

    public RiceInfoModel(String id) {
        super(id);
    }

    public RiceInfoModel(RiceInfoModel.Builder builder) {
        super(builder);
    }

    public static abstract class Builder extends InfoModel.Builder<String> {

        public Builder() {
        }

        public RiceInfoModel.Builder id(String id) {
            this.id = id;
            return this;
        }

        public RiceInfoModel.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RiceInfoModel.Builder description(String description) {
            this.description = description;
            return this;
        }

        public abstract RiceInfoModel<?,?> build();
    }
}
