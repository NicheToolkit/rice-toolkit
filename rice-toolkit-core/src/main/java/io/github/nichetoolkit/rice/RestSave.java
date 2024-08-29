package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.SaveType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface RestSave {

    default SaveType getSave() {
      return SaveType.NONE;
    }

    void setSave(SaveType save);

    static List<SaveType> build(Integer... saves) {
        if (GeneralUtils.isEmpty(saves)) {
            return Collections.emptyList();
        }
        return Arrays.stream(saves).map(SaveType::parseKey).distinct().collect(Collectors.toList());
    }

    static List<SaveType> build(Collection<Integer> saves) {
        if (GeneralUtils.isEmpty(saves)) {
            return Collections.emptyList();
        }
        return saves.stream().map(SaveType::parseKey).distinct().collect(Collectors.toList());
    }
}
