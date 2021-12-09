package io.github.nichetoolkit.rice;

/**
 * <p>RestEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RiceInfoEntity<E extends RiceInfoEntity<E,M>,M extends RiceInfoModel<M,E>> extends InfoEntity<String> implements RestEntity<String,M> {

    public RiceInfoEntity() {
    }

    public RiceInfoEntity(String id) {
        super(id);
    }

    public RiceInfoEntity(String id, String name) {
        super(id, name);
    }

    public RiceInfoEntity(Builder builder) {
        super(builder);
    }

    public static abstract class Builder extends InfoEntity.Builder<String> {

        public Builder() {
        }

        public RiceInfoEntity.Builder id(String id) {
            this.id = id;
            return this;
        }

        public RiceInfoEntity.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RiceInfoEntity.Builder description(String description) {
            this.description = description;
            return this;
        }

        public abstract RiceInfoEntity<?,?> build();
    }
}
