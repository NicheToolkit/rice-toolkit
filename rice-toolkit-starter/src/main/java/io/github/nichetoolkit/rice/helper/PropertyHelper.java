package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.util.DateUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.jsonb.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>PropertyHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
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

    public static Double transform(Long value, Integer scale) {
        return value.doubleValue() / scale.doubleValue();
    }

    public static Long transform(Double value, Integer scale) {
        return (long) (value * scale.longValue());
    }

    public static Double toDouble(Object value) {
        if (GeneralUtils.isNotEmpty(value)) {
            String trim = value.toString().trim();
            if (isDouble(trim)) {
                return Double.valueOf(trim);
            }
        }
        return null;
    }

    public static Integer toInteger(Object value) {
        if (GeneralUtils.isNotEmpty(value)) {
            String trim = value.toString().trim();
            if (isInteger(trim)) {
                return Integer.valueOf(trim);
            }
        }
        return null;
    }

    public static Long toLong(Object value) {
        if (GeneralUtils.isNotEmpty(value)) {
            String trim = value.toString().trim();
            if (isLong(trim)) {
                return Long.valueOf(trim);
            }
        }
        return null;
    }

    public static Long toTime(Object value) {
        if (GeneralUtils.isNotEmpty(value)) {
            String trim = value.toString().trim();
            if (isLong(trim)) {
                return Long.valueOf(trim);
            } else {
                Date date = DateUtils.parseDateTime(trim);
                return Optional.ofNullable(date).map(Date::getTime).orElse(null);
            }
        }
        return null;
    }

    public static boolean isInteger(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent integer fail！", value);
            return false;
        }
    }

    public static boolean isLong(String value) {
        try {
            Long.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent long fail！", value);
            return false;
        }
    }

    public static boolean isDouble(String value) {
        try {
            Double.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent double fail！", value);
            return false;
        }
    }
}
