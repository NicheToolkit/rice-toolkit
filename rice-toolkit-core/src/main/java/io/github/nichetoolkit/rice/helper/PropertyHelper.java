package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.jsonb.Property;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <code>PropertyHelper</code>
 * <p>The property helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class PropertyHelper {

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
                                Map<String, Object> container = new LinkedHashMap<>();
                                container.put(Property.VALUE, property.getValue());
                                return container;
                            }, (oldValue, newValue) -> newValue, LinkedHashMap::new));
            return JsonUtils.parseJson(propertiesMap);
        } else {
            return JsonUtils.parseJson(Collections.emptyList());
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
    public static Map<String,String> toPropertiesMap(Collection<Property> properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            return properties.stream().filter(Objects::nonNull)
                    .collect(Collectors.toMap(Property::getName,
                            property -> {
                                Object value = property.getValue();
                                if (GeneralUtils.isNotEmpty(value)) {
                                    return String.valueOf(value);
                                } else {
                                    return "";
                                }
                            }, (oldValue, newValue) -> newValue, LinkedHashMap::new));
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
    public static Map<String,String> toPropertiesMap(String properties) {
        if (GeneralUtils.isNotEmpty(properties)) {
            Map<String, Map<String, Object>> propertiesMap = JsonUtils.parseMapMap(properties, String.class, String.class, Object.class);
            return propertiesMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,entry -> {
                Object value = entry.getValue().get(Property.VALUE);
                if (GeneralUtils.isNotEmpty(value)) {
                    return String.valueOf(value);
                } else {
                    return "";
                }
            }));
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * <code>toPropertiesList</code>
     * <p>The to properties list method.</p>
     * @param propertiesMap {@link java.util.Map} <p>The properties map parameter is <code>Map</code> type.</p>
     * @return {@link java.util.List} <p>The to properties list return object is <code>List</code> type.</p>
     * @see java.util.Map
     * @see java.util.List
     */
    public static List<Property> toPropertiesList(Map<String,String> propertiesMap) {
        if (GeneralUtils.isNotEmpty(propertiesMap)) {
            return propertiesMap.entrySet().stream().map(entry -> Property.builder().name(entry.getKey())
                    .value(entry.getValue())
                    .build()).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
