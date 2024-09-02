package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.OperateType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <code>RestOperate</code>
 * <p>The type rest operate interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestOperate {

    /**
     * <code>getOperate</code>
     * <p>the operate getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate return object is <code>OperateType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    default OperateType getOperate() {
      return OperateType.NONE;
    }

    /**
     * <code>setOperate</code>
     * <p>the operate setter method.</p>
     * @param operate {@link io.github.nichetoolkit.rice.enums.OperateType} <p>the operate parameter is <code>OperateType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.OperateType
     */
    void setOperate(OperateType operate);

    /**
     * <code>build</code>
     * <p>the method.</p>
     * @param operates {@link java.lang.Integer} <p>the operates parameter is <code>Integer</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Integer
     * @see java.util.List
     */
    static List<OperateType> build(Integer... operates) {
        if (GeneralUtils.isEmpty(operates)) {
            return Collections.emptyList();
        }
        return Arrays.stream(operates).map(OperateType::parseKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>build</code>
     * <p>the method.</p>
     * @param operates {@link java.util.Collection} <p>the operates parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     */
    static List<OperateType> build(Collection<Integer> operates) {
        if (GeneralUtils.isEmpty(operates)) {
            return Collections.emptyList();
        }
        return operates.stream().map(OperateType::parseKey).distinct().collect(Collectors.toList());
    }
}
