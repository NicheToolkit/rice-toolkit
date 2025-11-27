package io.github.nichetoolkit.rice.jsonb;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <code>PropertyUtils</code>
 * <p>The property utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class PropertyUtils {


    /**
     * <code>toPropertiesJson</code>
     * <p>The to properties json method.</p>
     * @param properties {@link java.util.Collection} <p>The properties parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.String} <p>The to properties json return object is <code>String</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     */
    public static String toPropertiesJson(Collection<Property> properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            Map<String, Map<String, Object>> propertiesMap = properties.stream().filter(Objects::nonNull)
                    .collect(Collectors.toMap(Property::getName,
                            property -> {
                                Map<String, Object> container = new LinkedHashMap<>(1);
                                container.put(Property.VALUE, property.getValue());
                                return container;
                            }, (oldValue, newValue) -> newValue, LinkedHashMap::new));
            return JsonUtils.parseJson(propertiesMap);
        } else {
            return JsonUtils.parseJson(Collections.emptyList());
        }
    }

    /**
     * <code>toPropertiesJson</code>
     * <p>The to properties json method.</p>
     * @param properties {@link java.util.Map} <p>The properties parameter is <code>Map</code> type.</p>
     * @return {@link java.lang.String} <p>The to properties json return object is <code>String</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     */
    public static String toPropertiesJson(Map<String,Object> properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            Map<String, Map<String, Object>> propertiesMap = properties.entrySet().stream()
                    .filter(entry -> GeneralUtils.isNotEmpty(entry.getKey()) && GeneralUtils.isNotNull(entry.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            entry -> {
                                Map<String, Object> container = new LinkedHashMap<>(1);
                                container.put(Property.VALUE, entry.getValue());
                                return container;
                            }, (oldValue, newValue) -> newValue, LinkedHashMap::new));
            return JsonUtils.parseJson(propertiesMap);
        } else {
            return JsonUtils.parseJson(Collections.emptyList());
        }
    }

    /**
     * <code>toPropertiesList</code>
     * <p>The to properties list method.</p>
     * @param properties {@link java.lang.String} <p>The properties parameter is <code>String</code> type.</p>
     * @return {@link java.util.List} <p>The to properties list return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     */
    public static List<Property> toPropertiesList(String properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            Map<String, Map<String, Object>> propertiesMap = JsonUtils.parseMapMap(properties, String.class, String.class, Object.class);
            return propertiesMap.entrySet().stream().map(entry -> Property.builder().name(entry.getKey())
                    .value(entry.getValue().get(Property.VALUE))
                    .build()).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * <code>toPropertiesList</code>
     * <p>The to properties list method.</p>
     * @param properties {@link java.util.Map} <p>The properties parameter is <code>Map</code> type.</p>
     * @return {@link java.util.List} <p>The to properties list return object is <code>List</code> type.</p>
     * @see java.util.Map
     * @see java.util.List
     */
    public static List<Property> toPropertiesList(Map<String,Object> properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            return properties.entrySet().stream().map(entry -> Property.builder().name(entry.getKey())
                    .value(entry.getValue())
                    .build()).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * <code>toPropertiesMap</code>
     * <p>The to properties map method.</p>
     * @param properties {@link java.util.Collection} <p>The properties parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.Map} <p>The to properties map return object is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.Map
     */
    public static Map<String,Object> toPropertiesMap(Collection<Property> properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            return properties.stream().filter(Objects::nonNull)
                    .collect(Collectors.toMap(Property::getName,Property::getValue,
                            (oldValue, newValue) -> newValue, LinkedHashMap::new));
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * <code>toPropertiesMap</code>
     * <p>The to properties map method.</p>
     * @param properties {@link java.lang.String} <p>The properties parameter is <code>String</code> type.</p>
     * @return {@link java.util.Map} <p>The to properties map return object is <code>Map</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     */
    public static Map<String,Object> toPropertiesMap(String properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            Map<String, Map<String, Object>> propertiesMap = JsonUtils.parseMapMap(properties, String.class, String.class, Object.class);
            return propertiesMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,entry -> entry.getValue().get(Property.VALUE)));
        } else {
            return Collections.emptyMap();
        }
    }

}
