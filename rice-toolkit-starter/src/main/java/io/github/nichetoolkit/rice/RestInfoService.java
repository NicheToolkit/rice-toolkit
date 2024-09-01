package io.github.nichetoolkit.rice;

public abstract class RestInfoService<M extends RestInfoModel<M, E>, E extends RestInfoEntity<E, M>, F extends RestFilter> extends DefaultInfoService<String, String, M, E, F> {
}
