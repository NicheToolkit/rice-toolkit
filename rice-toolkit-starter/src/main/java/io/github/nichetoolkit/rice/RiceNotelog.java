package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestNotelog;
import io.github.nichetoolkit.rest.util.BeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>RiceLog</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceNotelog extends RestNotelog<RiceNotelog> {
    public RiceNotelog() {
    }

    public RiceNotelog(RestNotelog notelog) {
        BeanUtils.copyNonullProperties(notelog,this);
    }
}
