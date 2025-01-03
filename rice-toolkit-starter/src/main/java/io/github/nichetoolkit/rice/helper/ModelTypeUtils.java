package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * <code>ModelTypeUtils</code>
 * <p>The model type utils class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class ModelTypeUtils {

    /**
     * <code>identityType</code>
     * <p>The identity type method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  java.lang.Class
     * @return  {@link java.lang.Class} <p>The identity type return object is <code>Class</code> type.</p>
     */
    public static Class<?> identityType(Object object) {
        try {
            return ModelTypeHelper.identityType(object);
        } catch (ClassUnknownException | ClassUnsupportedException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the class type of <I> is unknown",exception);
            return null;
        }
    }

    /**
     * <code>newInstance</code>
     * <p>The new instance method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @return T <p>The new instance return object is <code>T</code> type.</p>
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return ModelTypeHelper.newInstance(clazz);
        } catch (ClassUnrenewException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> new instance is error",exception);
            return null;
        }
    }

    /**
     * <code>newInstance</code>
     * <p>The new instance method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.reflect.Constructor} <p>The clazz parameter is <code>Constructor</code> type.</p>
     * @param args {@link java.lang.Object} <p>The args parameter is <code>Object</code> type.</p>
     * @see  java.lang.reflect.Constructor
     * @see  java.lang.Object
     * @return T <p>The new instance return object is <code>T</code> type.</p>
     */
    public static <T> T newInstance(Constructor<T> clazz, Object ... args) {
        try {
            return ModelTypeHelper.newInstance(clazz,args);
        } catch (ClassUnrenewException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> new instance is error",exception);
            return null;
        }
    }

    /**
     * <code>generateIdentity</code>
     * <p>The generate identity method.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param model {@link io.github.nichetoolkit.rice.RestId} <p>The model parameter is <code>RestId</code> type.</p>
     * @see  io.github.nichetoolkit.rice.RestId
     * @return I <p>The generate identity return object is <code>I</code> type.</p>
     */
    public static <I> I generateIdentity(RestId<I> model)  {
        try {
            return ModelTypeHelper.generateIdentity(model);
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
