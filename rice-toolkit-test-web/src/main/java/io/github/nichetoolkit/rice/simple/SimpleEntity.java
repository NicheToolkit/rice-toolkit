package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RestInfoEntity;
import io.mybatis.provider.Entity;

import java.util.Date;


@Entity.Table(value = "ntr_simple")
public class SimpleEntity extends RestInfoEntity<SimpleEntity,SimpleModel> {

    private Date time;

    public SimpleEntity() {
    }

    public SimpleEntity(String id) {
        super(id);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public SimpleModel toModel() {
        SimpleModel.Builder builder = new SimpleModel.Builder();
        builder.id(this.id)
                .name(this.name)
                .description(this.description)
                .time(this.time);
        return new SimpleModel(builder);
    }

}
