package io.github.nichetoolkit.rice;


import io.github.nichetoolkit.rest.RestFitter;

/**
 * <code>RestServiceFitter</code>
 * <p>The rest service fitter interface.</p>
 * @param <F> {@link io.github.nichetoolkit.rice.RestServiceFitter} <p>The generic parameter is <code>RestServiceFitter</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestFitter
 * @since Jdk1.8
 */
public interface RestServiceFitter<F extends RestServiceFitter<F>> extends RestFitter<F> {

}
