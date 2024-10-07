package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <code>IdPack</code>
 * <p>The type id pack class.</p>
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
public class IdPack implements Serializable {
    /**
     * <code>id</code>
     * {@link java.lang.String} <p>The <code>id</code> field.</p>
     * @see java.lang.String
     */
    protected String id;

    /**
     * <code>IdPack</code>
     * <p>Instantiates a new id pack.</p>
     */
    public IdPack() {
    }

    /**
     * <code>IdPack</code>
     * <p>Instantiates a new id pack.</p>
     * @param id {@link java.lang.String} <p>The id parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdPack(String id) {
        this.id = id;
    }
}
