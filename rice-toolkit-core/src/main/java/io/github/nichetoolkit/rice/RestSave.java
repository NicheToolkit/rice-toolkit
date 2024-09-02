package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.SaveType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <code>RestSave</code>
 * <p>The type rest save interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestSave {

    /**
     * <code>getSave</code>
     * <p>the save getter method.</p>
     * @return {@link io.github.nichetoolkit.rice.enums.SaveType} <p>the save return object is <code>SaveType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     */
    default SaveType getSave() {
      return SaveType.NONE;
    }

    /**
     * <code>setSave</code>
     * <p>the save setter method.</p>
     * @param save {@link io.github.nichetoolkit.rice.enums.SaveType} <p>the save parameter is <code>SaveType</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.SaveType
     */
    void setSave(SaveType save);

    /**
     * <code>build</code>
     * <p>the method.</p>
     * @param saves {@link java.lang.Integer} <p>the saves parameter is <code>Integer</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Integer
     * @see java.util.List
     */
    static List<SaveType> build(Integer... saves) {
        if (GeneralUtils.isEmpty(saves)) {
            return Collections.emptyList();
        }
        return Arrays.stream(saves).map(SaveType::parseKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>build</code>
     * <p>the method.</p>
     * @param saves {@link java.util.Collection} <p>the saves parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     */
    static List<SaveType> build(Collection<Integer> saves) {
        if (GeneralUtils.isEmpty(saves)) {
            return Collections.emptyList();
        }
        return saves.stream().map(SaveType::parseKey).distinct().collect(Collectors.toList());
    }
}
