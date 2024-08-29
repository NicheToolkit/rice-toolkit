package io.github.nichetoolkit.rice;

public abstract class RiceInfoService<M extends RiceInfoModel<M, E>, E extends RiceInfoEntity<E, M>, F extends RiceFilter> extends RestInfoService<String, String, M, E, F> {
}
