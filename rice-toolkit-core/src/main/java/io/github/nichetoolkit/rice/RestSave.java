package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.enums.SaveType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <code>RestSave</code>
 * <p>The rest save interface.</p>
 * @see  java.io.Serializable
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestSave extends Serializable {

    /**
     * <code>getSave</code>
     * <p>The get save getter method.</p>
     * @return  {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The get save return object is <code>SaveType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.SaveType
     */
    SaveType getSave();

    /**
     * <code>setSave</code>
     * <p>The set save setter method.</p>
     * @param save {@link io.github.nichetoolkit.rice.enums.SaveType} <p>The save parameter is <code>SaveType</code> type.</p>
     * @see  io.github.nichetoolkit.rice.enums.SaveType
     */
    void setSave(SaveType save);

    /**
     * <code>build</code>
     * <p>The build method.</p>
     * @param saves {@link java.lang.Integer} <p>The saves parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The build return object is <code>List</code> type.</p>
     */
    static List<SaveType> build(Integer... saves) {
        if (GeneralUtils.isEmpty(saves)) {
            return Collections.emptyList();
        }
        return Arrays.stream(saves).map(SaveType::parseKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>build</code>
     * <p>The build method.</p>
     * @param saves {@link java.util.Collection} <p>The saves parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The build return object is <code>List</code> type.</p>
     */
    static List<SaveType> build(Collection<Integer> saves) {
        if (GeneralUtils.isEmpty(saves)) {
            return Collections.emptyList();
        }
        return saves.stream().map(SaveType::parseKey).distinct().collect(Collectors.toList());
    }
}
