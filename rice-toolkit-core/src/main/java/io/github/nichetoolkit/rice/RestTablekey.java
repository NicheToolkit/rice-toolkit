package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface RestTablekey<K> {

    @JsonIgnore
    default K getTablekey() {
        return null;
    }

}
