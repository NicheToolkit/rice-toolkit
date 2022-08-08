package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>IdPack</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdsPack implements Serializable {
    protected String ids;

    public IdsPack() {
    }

    public IdsPack(String ids) {
        this.ids = ids;
    }
}
