package io.github.nichetoolkit.rice;

import java.util.LinkedHashMap;

public class RestMap extends LinkedHashMap<String, Object> {

    private static final String AUTH_KEY = "_AUTH_KEY_";

    public void setAuthValue(String authValue) {
        this.put(AUTH_KEY, authValue);
    }

    public Object getAuthValue() {
        return this.get(AUTH_KEY);
    }
}
