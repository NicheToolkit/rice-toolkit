package io.github.nichetoolkit.rice.simple;

import io.github.nichetoolkit.rice.RestInfoEntity;
import io.mybatis.provider.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;


/**
 * <code>SimpleEntity</code>
 * <p>The simple entity class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.RestInfoEntity
 * @see lombok.Setter
 * @see lombok.Getter
 * @see lombok.experimental.SuperBuilder
 * @see io.mybatis.provider.Entity.Table
 * @since Jdk17
 */
@Setter
@Getter
@SuperBuilder
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

    @Override
    public SimpleModel toModel() {
        return SimpleModel.builder().id(this.id)
                .name(this.name)
                .description(this.description)
                .time(this.time).build();
    }

}
