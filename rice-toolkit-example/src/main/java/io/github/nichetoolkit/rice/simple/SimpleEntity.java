package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RestInfoEntity;
import io.mybatis.provider.Entity;

import java.util.Date;


/**
 * <code>SimpleEntity</code>
 * <p>The type simple entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoEntity
 * @see io.mybatis.provider.Entity.Table
 * @since Jdk1.8
 */
@Entity.Table(value = "ntr_simple")
public class SimpleEntity extends RestInfoEntity<SimpleEntity, SimpleModel> {

    /**
     * <code>time</code>
     * {@link java.util.Date} <p>The <code>time</code> field.</p>
     * @see java.util.Date
     */
    private Date time;

    /**
     * <code>SimpleEntity</code>
     * <p>Instantiates a new simple entity.</p>
     */
    public SimpleEntity() {
    }

    /**
     * <code>SimpleEntity</code>
     * <p>Instantiates a new simple entity.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public SimpleEntity(String id) {
        super(id);
    }

    /**
     * <code>getTime</code>
     * <p>The time getter method.</p>
     * @return {@link java.util.Date} <p>The time return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public Date getTime() {
        return time;
    }

    /**
     * <code>setTime</code>
     * <p>The time setter method.</p>
     * @param time {@link java.util.Date} <p>The time parameter is <code>Date</code> type.</p>
     * @see java.util.Date
     */
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
