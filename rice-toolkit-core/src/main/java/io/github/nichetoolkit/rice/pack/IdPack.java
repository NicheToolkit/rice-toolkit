package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdPack implements Serializable {
    protected String id;

    public IdPack() {
    }

    public IdPack(String id) {
        this.id = id;
    }
}
