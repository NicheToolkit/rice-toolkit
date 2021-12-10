package io.github.nichetoolkit.rice.clazz;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rice.IdModel;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>RestIdAccessUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class ClazzUtils {

    public static Class<?> clazz(Object object) {
        try {
            return ClazzHelper.clazz(object);
        } catch (ClassUnknownException exception) {
            exception.printStackTrace();
            log.error("the class type of <I> is unknown",exception);
            return null;
        }
    }
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
