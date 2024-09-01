package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;
import io.github.nichetoolkit.rice.clazz.ClazzHelper;

import java.lang.reflect.Array;
import java.util.List;

public class IdArrayHelper {

    @SuppressWarnings("unchecked")
    public static<I,M extends RestId<I>> I id(M model, int index, I... idArray) {
        I id = null;
        /* 优先通过 ResponseBody 关联id */
        if (GeneralUtils.isNotEmpty(model)) {
            id = model.getId();
        } else if (idArray.length > index) {
            id = idArray[index];
        }
        return id;
    }

    @SuppressWarnings("unchecked")
    public static<I,M extends RestId<I>> I id(List<M> models, int index, I... idArray) {
        I id = null;
        if (GeneralUtils.isNotEmpty(models) && models.size() == 1 ) {
            M model = models.stream().findFirst().get();
            id = id(model,index,idArray);
        }
        return id;
    }

    @SuppressWarnings("unchecked")
    public static <I,M extends RestId<I>>  void idArray(List<M> models, int index, I... idArray) throws ClassUnknownException, ClassUnsupportedException {
        /* 当models的数据有且仅有一条不为空的数据 将model的id反向关联 */
        if (GeneralUtils.isNotEmpty(models) && models.size() == 1) {
            M model = models.stream().findFirst().get();
            idArray(model,index,idArray);
        }
    }

    @SuppressWarnings("unchecked")
    public static <I,M extends RestId<I>> void idArray(M model, int index, I... idArray) throws ClassUnknownException, ClassUnsupportedException {
        if (GeneralUtils.isNotEmpty(model)) {
            /*  将model的id反向关联 */
            if (idArray.length <= index) {
                Class<?> clazz = ClazzHelper.clazz(model);
                I[] copyIdArray = (I[]) Array.newInstance(clazz, index + 1);
                System.arraycopy(idArray, 0, copyIdArray, 0, idArray.length);
                idArray = copyIdArray;
            }
            /* idArray length默认值为1，idArray[0] = null */
            idArray[index] = model.getId();
        }
    }
}
