package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.jsonb.Property;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class PropertyHelper {

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
