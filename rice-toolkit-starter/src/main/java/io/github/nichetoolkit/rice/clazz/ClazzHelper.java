package io.github.nichetoolkit.rice.clazz;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.IdModel;
import io.github.nichetoolkit.rice.RiceIdModel;
import io.github.nichetoolkit.rice.RiceInfoModel;

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
 * <p>RestIdAccessUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
public class ClazzHelper {

    @SuppressWarnings("WeakerAccess")
    public static Type[] types(Object object) throws ClassUnknownException {
        Type genericSuperclass;
        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            Optional first = collection.stream().findFirst();
            if (first.isPresent()) {
                genericSuperclass = first.get().getClass().getGenericSuperclass();
            } else {
                throw new ClassUnknownException();
            }
        } else if (object instanceof Map) {
            Map map = (Map) object;
            Optional first = map.values().stream().findFirst();
            if (first.isPresent()) {
                genericSuperclass = first.get().getClass().getGenericSuperclass();
            } else {
                throw new ClassUnknownException();
            }
        } else if (object instanceof Iterator) {
            Iterator iterator = (Iterator) object;
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
            genericSuperclass = ((Class) genericSuperclass).getGenericSuperclass();
            if (genericSuperclass == null) {
                throw new ClassUnknownException();
            }
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        return parameterizedType.getActualTypeArguments();
    }

    public static Class<?> clazz(Object object) throws ClassUnknownException, ClassUnsupportedException {
        if (object instanceof RiceIdModel || object instanceof RiceInfoModel) {
            return String.class;
        } else if (object instanceof IdModel) {
            Type[] actualTypes = types(object);
            Optional<Type> first = Stream.of(actualTypes).findFirst();
            return first.map(Class.class::cast).orElseThrow(ClassUnknownException::new);
        } else {
            throw new ClassUnsupportedException("the model must extends 'RiceIdModel' or 'IdModel': " + object.getClass().getName());
        }
    }

    public static <T> T renew(Class<T> clazz) throws ClassUnrenewException {
        T renew;
        try {
            renew = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new ClassUnrenewException(exception.getMessage());
        }
        return renew;
    }

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

    @SuppressWarnings(value = "unchecked")
    public static <S extends P,P> S supper(P parent, Class<S> clazz) throws ClassUnknownException{
        if (parent == null) {
            return null;
        } else if (parent.getClass() != clazz) {
            throw new ClassUnknownException();
        }
        return (S) parent;
    }

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
