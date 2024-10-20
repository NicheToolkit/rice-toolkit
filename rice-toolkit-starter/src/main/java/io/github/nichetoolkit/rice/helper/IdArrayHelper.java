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
     * <p>The method.</p>
     * @param <I>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param model   M <p>The model parameter is <code>M</code> type.</p>
     * @param index   int <p>The index parameter is <code>int</code> type.</p>
     * @param idArray I <p>The id array parameter is <code>I</code> type.</p>
     * @return I <p>The return object is <code>I</code> type.</p>
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
     * <p>The method.</p>
     * @param <I>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param models  {@link java.util.List} <p>The models parameter is <code>List</code> type.</p>
     * @param index   int <p>The index parameter is <code>int</code> type.</p>
     * @param idArray I <p>The id array parameter is <code>I</code> type.</p>
     * @return I <p>The return object is <code>I</code> type.</p>
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
     * <p>The array method.</p>
     * @param <I>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param models  {@link java.util.List} <p>The models parameter is <code>List</code> type.</p>
     * @param index   int <p>The index parameter is <code>int</code> type.</p>
     * @param idArray I <p>The id array parameter is <code>I</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>The class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>The class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
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
     * <p>The array method.</p>
     * @param <I>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <M>     {@link io.github.nichetoolkit.rice.RestId} <p>The generic parameter is <code>RestId</code> type.</p>
     * @param model   M <p>The model parameter is <code>M</code> type.</p>
     * @param index   int <p>The index parameter is <code>int</code> type.</p>
     * @param idArray I <p>The id array parameter is <code>I</code> type.</p>
     * @throws ClassUnknownException     {@link io.github.nichetoolkit.rest.error.ClassUnknownException} <p>The class unknown exception is <code>ClassUnknownException</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>The class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
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
                Class<?> identityType = ModelHelper.identityType(model);
                I[] copyIdArray = (I[]) Array.newInstance(identityType, index + 1);
                System.arraycopy(idArray, 0, copyIdArray, 0, idArray.length);
                idArray = copyIdArray;
            }
            /* idArray length默认值为1，idArray[0] = null */
            idArray[index] = model.getId();
        }
    }
}
