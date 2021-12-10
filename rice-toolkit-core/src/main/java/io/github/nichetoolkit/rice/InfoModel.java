package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.common.JsonUtils;

/**
 * <p>InfoModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("WeakerAccess")
public class InfoModel<I> extends IdModel<I> implements RestInfo<I> {
    /** 名称 */
    protected String name;
    /** 描述信息 */
    protected String description;

    public InfoModel() {
    }

    public InfoModel(I id) {
        super(id);
    }

    public InfoModel(InfoModel.Builder<I> builder) {
        super(builder);
        this.name = builder.name;
        this.description = builder.description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    public static class Builder<I> extends IdModel.Builder<I> {
        protected String name;
        protected String description;

        public Builder() {
        }

        @Override
        public InfoModel.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        public InfoModel.Builder<I> name(String name) {
            this.name = name;
            return this;
        }

        public InfoModel.Builder<I> description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public InfoModel<I> build() {
            return new InfoModel<>(this);
        }
    }
}
