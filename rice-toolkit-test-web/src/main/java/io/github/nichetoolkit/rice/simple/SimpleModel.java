package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RiceInfoModel;

import java.util.Date;
import java.util.Optional;

/**
 * <p>SimpleModel</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class SimpleModel extends RiceInfoModel<SimpleModel,SimpleEntity> {
    private Date time;

    public SimpleModel() {
    }

    public SimpleModel(String id) {
        super(id);
    }

    public SimpleModel(Builder builder) {
        super(builder);
        this.time = builder.time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public SimpleEntity toEntity(Long... idArray) {
        SimpleEntity entity = new SimpleEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        entity.setTime(Optional.ofNullable(this.getTime()).map(Date::getTime).orElse(null));
        return entity;
    }

    public static class Builder extends RiceInfoModel.Builder {
        protected Date time;

        public Builder() {
        }

        public Builder time(Long time) {
            this.time = new Date(time);
            return this;
        }

        public Builder time(Date time) {
            this.time = time;
            return this;
        }

        @Override
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public SimpleModel build() {
            return new SimpleModel(this);
        }
    }
}
