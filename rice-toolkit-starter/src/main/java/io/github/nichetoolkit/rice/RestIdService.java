package io.github.nichetoolkit.rice;

public abstract class RestIdService<M extends RestIdModel<M, E>, E extends RestIdEntity<E, M>, F extends RestFilter> extends DefaultIdService<String, String, M, E, F> {
}
