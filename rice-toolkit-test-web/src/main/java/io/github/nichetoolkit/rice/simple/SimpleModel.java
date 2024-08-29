package io.github.nichetoolkit.rice.simple;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.nichetoolkit.rice.RestTablekey;
import io.github.nichetoolkit.rice.RiceInfoModel;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.enums.SaveType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Date;

public class SimpleModel extends RiceInfoModel<SimpleModel,SimpleEntity> implements RestTablekey {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    public SimpleEntity toEntity() {
        SimpleEntity entity = new SimpleEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        return entity;
    }

    @Override
    public String getTablekey() {
        return "_dynamic";
    }

    public static class Builder extends RiceInfoModel.Builder {
        protected Date time;

        public Builder() {
        }

        public SimpleModel.Builder time(Long time) {
            this.time = new Date(time);
            return this;
        }

        public SimpleModel.Builder time(Date time) {
            this.time = time;
            return this;
        }

        @Override
        public SimpleModel.Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public SimpleModel.Builder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public SimpleModel.Builder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public SimpleModel.Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public SimpleModel.Builder createTime(@NonNull Long createTime) {
            this.createTime = new Date(createTime);
            return this;
        }

        @Override
        public SimpleModel.Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public SimpleModel.Builder updateTime(@NonNull Long updateTime) {
            this.updateTime = new Date(updateTime);
            return this;
        }

        @Override
        public SimpleModel.Builder operate(OperateType operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public SimpleModel.Builder operate(Integer operate) {
            this.operate = OperateType.parseKey(operate);
            return this;
        }

        @Override
        public SimpleModel.Builder save(SaveType save) {
            this.save = save;
            return this;
        }
        @Override
        public SimpleModel.Builder save(Integer save) {
            this.save = SaveType.parseKey(save);
            return this;
        }

        @Override
        public SimpleModel build() {
            return new SimpleModel(this);
        }
    }
}
