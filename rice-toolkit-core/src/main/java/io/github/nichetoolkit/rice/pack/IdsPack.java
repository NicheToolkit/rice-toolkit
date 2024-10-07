package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <code>IdsPack</code>
 * <p>The type ids pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Data
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdsPack implements Serializable {
    /**
     * <code>ids</code>
     * {@link java.lang.String} <p>The <code>ids</code> field.</p>
     * @see java.lang.String
     */
    protected String ids;

    /**
     * <code>IdsPack</code>
     * <p>Instantiates a new ids pack.</p>
     */
    public IdsPack() {
    }

    /**
     * <code>IdsPack</code>
     * <p>Instantiates a new ids pack.</p>
     * @param ids {@link java.lang.String} <p>The ids parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdsPack(String ids) {
        this.ids = ids;
    }
}
