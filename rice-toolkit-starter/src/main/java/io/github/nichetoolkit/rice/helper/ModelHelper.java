package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.reflect.RestGenericTypes;
import io.github.nichetoolkit.rice.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unused")
public class ModelHelper {

    public static Class<?> clazzOfModel(Object model) throws ClassUnknownException, ClassUnsupportedException {
        if (model instanceof RestIdModel || model instanceof RestInfoModel) {
            return String.class;
        } else if (model instanceof IdModel) {
            return RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    IdModel.class.getTypeParameters()[0], model.getClass(), IdModel.class));
        } else if (model instanceof RestId) {
            return RestGenericTypes.resolveClass(RestGenericTypes.resolveType(
                    RestId.class.getTypeParameters()[0], model.getClass(), RestId.class));
        } else {
            throw new ClassUnsupportedException("the model must extends 'RiceIdModel' or 'IdModel': " + model.getClass().getName());
        }
    }

    public static <T> T newInstance(Class<T> clazz) throws ClassUnrenewException {
        T renew;
        try {
            renew = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new ClassUnrenewException(exception.getMessage());
        }
        return renew;
    }

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

    @SuppressWarnings(value = "unchecked")
    public static <S extends P, P> S supper(P parent, Class<S> clazz) throws ClassUnknownException {
        if (parent == null) {
            return null;
        } else if (parent.getClass() != clazz) {
            throw new ClassUnknownException();
        }
        return (S) parent;
    }

    @SuppressWarnings(value = "unchecked")
    public static <I> I generate(RestId<I> model) throws ClassUnsupportedException, ClassUnknownException {
        Class<?> clazz = clazzOfModel(model);
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
