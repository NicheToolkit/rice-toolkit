package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rice.*;
import io.github.nichetoolkit.rice.enums.OperateType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <code>ModelHelper</code>
 * <p>The type model helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class ModelHelper {

    /**
     * <code>genericType</code>
     * <p>The type method.</p>
     * @param model {@link java.lang.Object} <p>The model parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Class} <p>The type return object is <code>Class</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>The class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>The class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     * @see io.github.nichetoolkit.rest.error.ClassUnsupportedException
     */
    public static Class<?> identityType(Object model) throws ClassUnknownException, ClassUnsupportedException {
        if (model instanceof RestIdModel || model instanceof RestInfoModel) {
            return String.class;
        } else if (model instanceof IdModel) {
            return RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    IdModel.class.getTypeParameters()[0], model.getClass(), IdModel.class));
        } else if (model instanceof RestId) {
            return RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestId.class.getTypeParameters()[0], model.getClass(), RestId.class));
        } else {
            throw new ClassUnsupportedException("the model must extends 'RestId': " + model.getClass().getName());
        }
    }

    public static Class<?> operateType(Object model) throws ClassUnknownException, ClassUnsupportedException {
        if (model instanceof OperateModel) {
            return OperateType.class;
        } else if (model instanceof RestOperate) {
            return RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestOperate.class.getTypeParameters()[0], model.getClass(), RestOperate.class));
        } else {
            throw new ClassUnsupportedException("the model must extends 'RestOperate': " + model.getClass().getName());
        }
    }

    /**
     * <code>newInstance</code>
     * <p>The instance method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The instance return object is <code>T</code> type.</p>
     * @throws ClassUnrenewException {@link io.github.nichetoolkit.rest.error.ClassUnrenewException} <p>The class unrenew exception is <code>ClassUnrenewException</code> type.</p>
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.ClassUnrenewException
     */
    public static <T> T newInstance(Class<T> clazz) throws ClassUnrenewException {
        T renew;
        try {
            renew = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new ClassUnrenewException(exception.getMessage());
        }
        return renew;
    }

    /**
     * <code>newInstance</code>
     * <p>The instance method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.reflect.Constructor} <p>The clazz parameter is <code>Constructor</code> type.</p>
     * @param args  {@link java.lang.Object} <p>The args parameter is <code>Object</code> type.</p>
     * @return T <p>The instance return object is <code>T</code> type.</p>
     * @throws ClassUnrenewException {@link io.github.nichetoolkit.rest.error.ClassUnrenewException} <p>The class unrenew exception is <code>ClassUnrenewException</code> type.</p>
     * @see java.lang.reflect.Constructor
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.error.ClassUnrenewException
     */
    public static <T> T newInstance(Constructor<T> clazz, Object... args) throws ClassUnrenewException {
        T instance;
        try {
            instance = clazz.newInstance(args);
        } catch (InstantiationException | IllegalAccessException
                 | IllegalArgumentException | InvocationTargetException exception) {
            throw new ClassUnrenewException(exception.getMessage());
        }
        return instance;
    }

    /**
     * <code>supper</code>
     * <p>The method.</p>
     * @param <S>    P <p>The generic parameter is <code>P</code> type.</p>
     * @param <P>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param parent P <p>The parent parameter is <code>P</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return S <p>The return object is <code>S</code> type.</p>
     * @throws ClassUnknownException {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>The class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @see P
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     */
    @SuppressWarnings(value = "unchecked")
    public static <S extends P, P> S supper(P parent, Class<S> clazz) throws ClassUnknownException {
        if (parent == null) {
            return null;
        } else if (parent.getClass() != clazz) {
            throw new ClassUnknownException();
        }
        return (S) parent;
    }

    /**
     * <code>generate</code>
     * <p>The method.</p>
     * @param <I>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param model {@link io.github.nichetoolkit.rice.RestId} <p>The model parameter is <code>RestId</code> type.</p>
     * @return I <p>The return object is <code>I</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>The class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>The class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.ClassUnsupportedException
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     */
    @SuppressWarnings(value = "unchecked")
    public static <I> I generateIdentity(RestId<I> model) throws ClassUnsupportedException, ClassUnknownException {
        Class<?> clazz = identityType(model);
        Long id = IdentityUtils.valueOfLong();
        if (String.class.equals(clazz)) {
            return (I) String.valueOf(id);
        } else if (Long.class.equals(clazz)) {
            return (I) id;
        } else if (Integer.class.equals(clazz)) {
            return (I) ((Integer) id.intValue());
        } else if (Double.class.equals(clazz)) {
            return (I) ((Double) id.doubleValue());
        } else if (Float.class.equals(clazz)) {
            return (I) ((Float) id.floatValue());
        } else if (Short.class.equals(clazz)) {
            return (I) ((Short) id.shortValue());
        } else if (Byte.class.equals(clazz)) {
            return (I) ((Byte) id.byteValue());
        } else {
            throw new ClassUnsupportedException("invalid <I> java type: " + clazz);
        }
    }
}
