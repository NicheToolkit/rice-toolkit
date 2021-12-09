package io.github.nichetoolkit.rice;

import java.util.Date;

/**
 * <p>InfoEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class InfoEntity<I> extends IdEntity<I> implements RestInfo<I> {
    /** 事物名称 */
    protected String name;
    /** 事物描述 */
    protected String description;

    public InfoEntity() {
    }

    public InfoEntity(I id) {
        super(id);
    }

    public InfoEntity(I id, String name) {
        super(id);
        this.name = name;
    }

    public InfoEntity(InfoEntity.Builder<I> builder) {
        super(builder);
        this.name = builder.name;
        this.description = builder.description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder<I> extends IdEntity.Builder<I> {
        protected String name;
        protected String description;
        public Builder() {
        }

        public InfoEntity.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        public InfoEntity.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public InfoEntity.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public InfoEntity.Builder<I> name(String name) {
            this.name = name;
            return this;
        }

        public InfoEntity.Builder<I> description(String description) {
            this.description = description;
            return this;
        }

        public InfoEntity<I> build() {
            return new InfoEntity<>(this);
        }
    }
}
