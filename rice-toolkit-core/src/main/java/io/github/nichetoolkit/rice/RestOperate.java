package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.OperateType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface RestOperate<O> extends Serializable {

    O getOperate();

    void setOperate(O operate);

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
