package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnrenewException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

@Slf4j
public class ModelUtils {

    public static Class<?> clazzOfModel(Object object) {
        try {
            return ModelHelper.clazzOfModel(object);
        } catch (ClassUnknownException | ClassUnsupportedException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the class type of <I> is unknown",exception);
            return null;
        }
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return ModelHelper.newInstance(clazz);
        } catch (ClassUnrenewException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> is unrenew",exception);
            return null;
        }
    }

    public static <T> T newInstance(Constructor<T> clazz, Object ... args) {
        try {
            return ModelHelper.newInstance(clazz,args);
        } catch (ClassUnrenewException exception) {
            GeneralUtils.printStackTrace(exception,false);
            log.error("the model type of <I> is unrenew",exception);
            return null;
        }
    }

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
