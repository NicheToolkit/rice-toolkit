package io.github.nichetoolkit.rice.clazz;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rice.IdModel;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * <code>ClazzUtils</code>
 * <p>The type clazz utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ClazzUtils {

    /**
     * <code>clazz</code>
     * <p>the method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Class} <p>the return object is <code>Class</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     */
    public static Class<?> clazz(Object object) {
        try {
            return ClazzHelper.clazz(object);
        } catch (ClassUnknownException | ClassUnsupportedException exception) {
            exception.printStackTrace();
            log.error("the class type of <I> is unknown",exception);
            return null;
        }
    }

    /**
     * <code>renew</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <T> T renew(Class<T> clazz) {
        try {
            return ClazzHelper.renew(clazz);
        } catch (ClassUnrenewException exception) {
            exception.printStackTrace();
            log.error("the class type of <I> is unrenew",exception);
            return null;
        }
    }

    /**
     * <code>renew</code>
     * <p>the method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz    {@link java.lang.reflect.Constructor} <p>the clazz parameter is <code>Constructor</code> type.</p>
     * @param initargs {@link java.lang.Object} <p>the initargs parameter is <code>Object</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @see java.lang.reflect.Constructor
     * @see java.lang.Object
     */
    public static <T> T renew(Constructor<T> clazz, Object ... initargs) {
        try {
            return ClazzHelper.renew(clazz,initargs);
        } catch (ClassUnrenewException exception) {
            exception.printStackTrace();
            log.error("the class type of <I> is unrenew",exception);
            return null;
        }
    }

    /**
     * <code>generate</code>
     * <p>the method.</p>
     * @param <I>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param model {@link io.github.nichetoolkit.rice.IdModel} <p>the model parameter is <code>IdModel</code> type.</p>
     * @return I <p>the return object is <code>I</code> type.</p>
     * @see io.github.nichetoolkit.rice.IdModel
     */
    public static <I> I generate(IdModel<I> model)  {
        try {
            return ClazzHelper.generate(model);
        } catch (ClassUnknownException exception) {
            exception.printStackTrace();
            log.error("the class type of <I> is unknown",exception);
        } catch (ClassUnsupportedException exception) {
            exception.printStackTrace();
            log.error("the class type of <I> is unsupported",exception);
        }
        return null;
    }
}
