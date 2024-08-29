package io.github.nichetoolkit.rice;

public abstract class RiceIdService<M extends RiceIdModel<M, E>, E extends RiceIdEntity<E, M>, F extends RiceFilter> extends RestIdService<String, String, M, E, F> {
}
