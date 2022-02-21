package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RiceInfoEntity;

import javax.persistence.Table;


/**
 * <p>SimpleEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Table(name = "ntr_simple")
public class SimpleEntity extends RiceInfoEntity<SimpleEntity,SimpleModel> {

    private Long time;

    public SimpleEntity() {
    }

    public SimpleEntity(String id) {
        super(id);
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
