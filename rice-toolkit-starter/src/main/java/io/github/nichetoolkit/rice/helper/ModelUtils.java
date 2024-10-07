package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * <code>ModelUtils</code>
 * <p>The type model utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ModelUtils {

    /**
     * <code>genericType</code>
     * <p>The type method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Class} <p>The type return object is <code>Class</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     */
    public static Class<?> genericType(Object object) {
        try {
            return ModelHelper.genericType(object);
        } catch (ClassUnknownException | ClassUnsupportedException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the class type of <I> is unknown",exception);
            return null;
        }
    }

    /**
     * <code>newInstance</code>
     * <p>The instance method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The instance return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return ModelHelper.newInstance(clazz);
        } catch (ClassUnrenewException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> is unrenew",exception);
            return null;
        }
    }

    /**
     * <code>newInstance</code>
     * <p>The instance method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.reflect.Constructor} <p>The clazz parameter is <code>Constructor</code> type.</p>
     * @param args  {@link java.lang.Object} <p>The args parameter is <code>Object</code> type.</p>
     * @return T <p>The instance return object is <code>T</code> type.</p>
     * @see java.lang.reflect.Constructor
     * @see java.lang.Object
     */
    public static <T> T newInstance(Constructor<T> clazz, Object ... args) {
        try {
            return ModelHelper.newInstance(clazz,args);
        } catch (ClassUnrenewException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> is unrenew",exception);
            return null;
        }
    }

    /**
     * <code>generate</code>
     * <p>The method.</p>
     * @param <I>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param model {@link io.github.nichetoolkit.rice.RestId} <p>The model parameter is <code>RestId</code> type.</p>
     * @return I <p>The return object is <code>I</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     */
    public static <I> I generate(RestId<I> model)  {
        try {
            return ModelHelper.generate(model);
        } catch (ClassUnknownException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> is unknown",exception);
        } catch (ClassUnsupportedException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> is unsupported",exception);
        }
        return null;
    }
}
