package io.github.nichetoolkit.rice.clazz;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RestIdModel;
import io.github.nichetoolkit.rice.RestInfoModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <code>ClazzHelper</code>
 * <p>The type clazz helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class ClazzHelper {

    /**
     * <code>types</code>
     * <p>the method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.reflect.Type} <p>the return object is <code>Type</code> type.</p>
     * @throws ClassUnknownException {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>the class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.reflect.Type
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     */
    @SuppressWarnings("WeakerAccess")
    public static Type[] types(Object object) throws ClassUnknownException {
        Type genericSuperclass;
        if (object instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) object;
            Optional<?> first = collection.stream().findFirst();
            if (first.isPresent()) {
                genericSuperclass = first.get().getClass().getGenericSuperclass();
            } else {
                throw new ClassUnknownException();
            }
        } else if (object instanceof Map) {
            Map<?,?> map = (Map<?,?>) object;
            Optional<?> first = map.values().stream().findFirst();
            if (first.isPresent()) {
                genericSuperclass = first.get().getClass().getGenericSuperclass();
            } else {
                throw new ClassUnknownException();
            }
        } else if (object instanceof Iterator) {
            Iterator<?> iterator = (Iterator<?>) object;
            if (iterator.hasNext()) {
                genericSuperclass = iterator.next().getClass().getGenericSuperclass();
            } else {
                throw new ClassUnknownException();
            }
        } else if (object.getClass().isArray()) {
            Object first = Optional.of(object).orElseThrow(ClassUnknownException::new);
            genericSuperclass = first.getClass().getGenericSuperclass();
        } else  {
            genericSuperclass =  object.getClass().getGenericSuperclass();
        }
        while (!(genericSuperclass instanceof ParameterizedType)) {
            genericSuperclass = ((Class<?>) genericSuperclass).getGenericSuperclass();
            if (genericSuperclass == null) {
                throw new ClassUnknownException();
            }
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        return parameterizedType.getActualTypeArguments();
    }

    /**
     * <code>clazz</code>
     * <p>the method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Class} <p>the return object is <code>Class</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>the class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>the class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     * @see io.github.nichetoolkit.rest.error.ClassUnsupportedException
     */
    public static Class<?> clazz(Object object) throws ClassUnknownException, ClassUnsupportedException {
        if (object instanceof RestIdModel || object instanceof RestInfoModel) {
            return String.class;
        } else if (object instanceof IdModel) {
            Type[] actualTypes = types(object);
            Optional<Type> first = Stream.of(actualTypes).findFirst();
            return first.map(Class.class::cast).orElseThrow(ClassUnknownException::new);
        } else {
            throw new ClassUnsupportedException("the model must extends 'RiceIdModel' or 'IdModel': " + object.getClass().getName());
        }
    }

    /**
     * <code>renew</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @throws ClassUnrenewException {@link io.github.nichetoolkit.rest.error.ClassUnrenewException} <p>the class unrenew exception is <code>ClassUnrenewException</code> type.</p>
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.ClassUnrenewException
     */
    public static <T> T renew(Class<T> clazz) throws ClassUnrenewException {
        T renew;
        try {
            renew = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new ClassUnrenewException(exception.getMessage());
        }
        return renew;
    }

    /**
     * <code>renew</code>
     * <p>the method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz    {@link java.lang.reflect.Constructor} <p>the clazz parameter is <code>Constructor</code> type.</p>
     * @param initargs {@link java.lang.Object} <p>the initargs parameter is <code>Object</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @throws ClassUnrenewException {@link io.github.nichetoolkit.rest.error.ClassUnrenewException} <p>the class unrenew exception is <code>ClassUnrenewException</code> type.</p>
     * @see java.lang.reflect.Constructor
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.error.ClassUnrenewException
     */
    public static <T> T renew(Constructor<T> clazz, Object ... initargs) throws ClassUnrenewException {
        T renew;
        try {
            renew = clazz.newInstance(initargs);
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException exception) {
            throw new ClassUnrenewException(exception.getMessage());
        }
        return renew;
    }

    /**
     * <code>supper</code>
     * <p>the method.</p>
     * @param <S>    P <p>the generic parameter is <code>P</code> type.</p>
     * @param <P>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param parent P <p>the parent parameter is <code>P</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return S <p>the return object is <code>S</code> type.</p>
     * @throws ClassUnknownException {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>the class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @see P
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     */
    @SuppressWarnings(value = "unchecked")
    public static <S extends P,P> S supper(P parent, Class<S> clazz) throws ClassUnknownException{
        if (parent == null) {
            return null;
        } else if (parent.getClass() != clazz) {
            throw new ClassUnknownException();
        }
        return (S) parent;
    }

    /**
     * <code>generate</code>
     * <p>the method.</p>
     * @param <I>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param model {@link io.github.nichetoolkit.rice.IdModel} <p>the model parameter is <code>IdModel</code> type.</p>
     * @return I <p>the return object is <code>I</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>the class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>the class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @see io.github.nichetoolkit.rice.IdModel
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.ClassUnsupportedException
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     */
    @SuppressWarnings(value = "unchecked")
    public static <I> I generate(IdModel<I> model) throws ClassUnsupportedException, ClassUnknownException {
        Class<?> clazz = clazz(model);
        Long id = IdentityUtils.generateLong();

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
