package io.github.nichetoolkit.rice;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.enums.OperateType;
import io.github.nichetoolkit.rice.stereotype.mybatis.RestIdentity;
import org.springframework.lang.NonNull;

import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * <p>IdEntity</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdEntity<I> extends TimeEntity implements RestId<I> {
    @Id
    @TableId
    @RestIdentity
    protected I id;

    public IdEntity() {
    }

    public IdEntity(I id) {
        this.id = id;
    }

    public IdEntity(IdEntity.Builder<I> builder) {
        super(builder);
        this.id = builder.id;
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof IdEntity)) return false;
        IdEntity<?> idEntity = (IdEntity<?>) o;
        return Objects.equals(id, idEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return JsonUtils.parseJson(this);
    }

    public static class Builder<I> extends TimeEntity.Builder {
        protected I id;

        public Builder() {
        }

        public IdEntity.Builder<I> id(I id) {
            this.id = id;
            return this;
        }

        @Override
        public IdEntity.Builder<I> createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        @Override
        public IdEntity.Builder<I> updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        public IdEntity.Builder operate(Integer operate) {
            this.operate = operate;
            return this;
        }

        @Override
        public IdEntity.Builder operate(@NonNull OperateType operate) {
            this.operate = operate.getKey();
            return this;
        }

        @Override
        public IdEntity.Builder logicSign(String logicSign) {
            this.logicSign = logicSign;
            return this;
        }

        @Override
        public IdEntity<I> build() {
            return new IdEntity<>(this);
        }
    }
}
