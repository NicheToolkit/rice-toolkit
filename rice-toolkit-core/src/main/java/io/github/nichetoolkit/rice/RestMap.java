package io.github.nichetoolkit.rice;

import java.util.LinkedHashMap;

/**
 * <p>RestMap</p>
 * 登录模块模型数据存储容器，用于在具体接口中传递数据给拦截器
 * 此类型参数由自定义参数解析器生成，非前端请求参数
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestMap extends LinkedHashMap<String, Object> {

    private static final String AUTH_KEY = "_AUTH_KEY_";

    public void setAuthValue(String authValue) {
        this.put(AUTH_KEY, authValue);
    }

    public Object getAuthValue() {
        return this.get(AUTH_KEY);
    }
}
