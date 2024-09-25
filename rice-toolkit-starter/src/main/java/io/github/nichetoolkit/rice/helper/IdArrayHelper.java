package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.error.ClassUnknownException;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.RestId;

import java.lang.reflect.Array;
import java.util.List;

/**
 * <code>IdArrayHelper</code>
 * <p>The type id array helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class IdArrayHelper {

    /**
     * <code>id</code>
     * <p>the method.</p>
     * @param <I>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param index   int <p>the index parameter is <code>int</code> type.</p>
     * @param idArray I <p>the id array parameter is <code>I</code> type.</p>
     * @return I <p>the return object is <code>I</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.lang.SuppressWarnings
     */
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

    /**
     * <code>id</code>
     * <p>the method.</p>
     * @param <I>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param models  {@link java.util.List} <p>the models parameter is <code>List</code> type.</p>
     * @param index   int <p>the index parameter is <code>int</code> type.</p>
     * @param idArray I <p>the id array parameter is <code>I</code> type.</p>
     * @return I <p>the return object is <code>I</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    public static<I,M extends RestId<I>> I id(List<M> models, int index, I... idArray) {
        I id = null;
        if (GeneralUtils.isNotEmpty(models) && models.size() == 1 ) {
            M model = models.stream().findFirst().get();
            id = id(model,index,idArray);
        }
        return id;
    }

    /**
     * <code>idArray</code>
     * <p>the array method.</p>
     * @param <I>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param models  {@link java.util.List} <p>the models parameter is <code>List</code> type.</p>
     * @param index   int <p>the index parameter is <code>int</code> type.</p>
     * @param idArray I <p>the id array parameter is <code>I</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>the class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>the class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     * @see io.github.nichetoolkit.rest.error.ClassUnsupportedException
     */
    @SuppressWarnings("unchecked")
    public static <I,M extends RestId<I>>  void idArray(List<M> models, int index, I... idArray) throws ClassUnknownException, ClassUnsupportedException {
        /* 当models的数据有且仅有一条不为空的数据 将model的id反向关联 */
        if (GeneralUtils.isNotEmpty(models) && models.size() == 1) {
            M model = models.stream().findFirst().get();
            idArray(model,index,idArray);
        }
    }

    /**
     * <code>idArray</code>
     * <p>the array method.</p>
     * @param <I>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>the generic parameter is <code>RestId</code> type.</p>
     * @param model   M <p>the model parameter is <code>M</code> type.</p>
     * @param index   int <p>the index parameter is <code>int</code> type.</p>
     * @param idArray I <p>the id array parameter is <code>I</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>the class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>the class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
     * @see io.github.nichetoolkit.rice.RestId
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.ClassUnknownException
     * @see io.github.nichetoolkit.rest.error.ClassUnsupportedException
     */
    @SuppressWarnings("unchecked")
    public static <I,M extends RestId<I>> void idArray(M model, int index, I... idArray) throws ClassUnknownException, ClassUnsupportedException {
        if (GeneralUtils.isNotEmpty(model)) {
            /*  将model的id反向关联 */
            if (idArray.length <= index) {
                Class<?> clazz = ModelHelper.clazzOfModel(model);
                I[] copyIdArray = (I[]) Array.newInstance(clazz, index + 1);
                System.arraycopy(idArray, 0, copyIdArray, 0, idArray.length);
                idArray = copyIdArray;
            }
            /* idArray length默认值为1，idArray[0] = null */
            idArray[index] = model.getId();
        }
    }
}
