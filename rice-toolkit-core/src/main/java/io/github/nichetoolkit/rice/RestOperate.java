package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.OperateType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>RestOperate</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestOperate {

    default OperateType getOperate() {
      return OperateType.NONE;
    }

    void setOperate(OperateType operate);

    static List<OperateType> build(Integer... operates) {
        if (GeneralUtils.isEmpty(operates)) {
            return Collections.emptyList();
        }
        return Arrays.stream(operates).map(OperateType::parseKey).distinct().collect(Collectors.toList());
    }

    static List<OperateType> build(Collection<Integer> operates) {
        if (GeneralUtils.isEmpty(operates)) {
            return Collections.emptyList();
        }
        return operates.stream().map(OperateType::parseKey).distinct().collect(Collectors.toList());
    }
}
