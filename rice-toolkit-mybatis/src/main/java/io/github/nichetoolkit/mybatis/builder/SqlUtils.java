package io.github.nichetoolkit.mybatis.builder;

import io.github.nichetoolkit.mybatis.RestStyle;
import io.github.nichetoolkit.mybatis.consts.SQLConstants;
import io.github.nichetoolkit.mybatis.enums.StyleType;
import io.github.nichetoolkit.rest.RestReckon;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <code>SqlUtils</code>
 * <p>The sql utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class SqlUtils {

    /**
     * <code>reverseArray</code>
     * <p>The reverse array method.</p>
     * @param array {@link java.lang.Object} <p>The array parameter is <code>Object</code> type.</p>
     * @see  java.lang.Object
     */
    public static void reverseArray(Object[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            Object temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    /**
     * <code>fieldsOfType</code>
     * <p>The fields of type method.</p>
     * @param declaredType {@link java.lang.Class} <p>The declared type parameter is <code>Class</code> type.</p>
     * @param excludeFields {@link java.util.List} <p>The exclude fields parameter is <code>List</code> type.</p>
     * @param excludeSuperClasses {@link java.util.List} <p>The exclude super classes parameter is <code>List</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.List
     * @return  {@link java.util.List} <p>The fields of type return object is <code>List</code> type.</p>
     */
    public static List<Field> fieldsOfType(Class<?> declaredType, List<String> excludeFields, List<Class<?>> excludeSuperClasses) {
        List<Field> fieldList = new ArrayList<>();
        /* 未处理的需要获取字段 */
        Class<?> declaredClass = declaredType;
        boolean isSuperclass = true;
        while (declaredClass != null && declaredClass != Object.class) {
            Field[] declaredFields = declaredClass.getDeclaredFields();
            reverseArray(declaredFields);
            for (Field declaredField : declaredFields) {
                int modifiers = declaredField.getModifiers();
                /* 排除 static 和 transient 修饰的字段 */
                if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                    /* 是否需要排除字段 */
                    if (GeneralUtils.isNotEmpty(excludeFields) && excludeFields.contains(declaredField.getName())) {
                        continue;
                    }
                    declaredField.setAccessible(true);
                    fieldList.add(declaredField);
                }
            }
            /* 排除父类,迭代获取父类 */
            do {
                declaredClass = declaredClass.getSuperclass();
            } while (GeneralUtils.isNotEmpty(excludeSuperClasses) && excludeSuperClasses.contains(declaredClass) && declaredClass != Object.class);
        }
        return fieldList;
    }

    /**
     * <code>sliceOfType</code>
     * <p>The slice of type method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param typeList {@link java.util.List} <p>The type list parameter is <code>List</code> type.</p>
     * @param fieldList {@link java.util.List} <p>The field list parameter is <code>List</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @return  {@link java.util.Map} <p>The slice of type return object is <code>Map</code> type.</p>
     */
    public static <T> Map<Integer, List<T>> sliceOfType(List<T> typeList, List<Field> fieldList) {
        return typeList.stream()
                .collect(Collectors.groupingBy(id -> {
                    int indexValue = 0;
                    for (int index = 0; index < fieldList.size(); index++) {
                        Field field = fieldList.get(index);
                        Object object = null;
                        try {
                            object = field.get(id);
                        } catch (IllegalAccessException ignored) {
                        }
                        if (GeneralUtils.isValid(object)) {
                            indexValue = indexValue | -(-1 << index);
                        }
                    }
                    return indexValue;
                }));
    }

    /**
     * <code>valueOfType</code>
     * <p>The value of type method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param type T <p>The type parameter is <code>T</code> type.</p>
     * @param fieldList {@link java.util.List} <p>The field list parameter is <code>List</code> type.</p>
     * @see  java.util.List
     * @return boolean <p>The value of type return object is <code>boolean</code> type.</p>
     */
    public static <T> boolean valueOfType(T type, List<Field> fieldList) {
        Optional<Boolean> logicalOrOptional = fieldList.stream().map(field -> {
            try {
                Object fieldValue = field.get(type);
                return GeneralUtils.isNotEmpty(fieldValue);
            } catch (IllegalAccessException ignored) {
                return false;
            }
        }).reduce(Boolean::logicalOr);
        return logicalOrOptional.orElse(false);
    }

    /**
     * <code>whereSqlOfTypes</code>
     * <p>The where sql of types method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param types {@link java.util.Collection} <p>The types parameter is <code>Collection</code> type.</p>
     * @param idType {@link java.lang.Class} <p>The id type parameter is <code>Class</code> type.</p>
     * @param styleType {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The style type parameter is <code>StyleType</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.mybatis.enums.StyleType
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The where sql of types return object is <code>String</code> type.</p>
     */
    public static <T> String whereSqlOfTypes(Collection<T> types, Class<T> idType, StyleType styleType) {
        return whereSqlOfTypes(null,types, idType, styleType);
    }

    /**
     * <code>whereSqlOfTypes</code>
     * <p>The where sql of types method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param types {@link java.util.Collection} <p>The types parameter is <code>Collection</code> type.</p>
     * @param type {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @param styleType {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The style type parameter is <code>StyleType</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Collection
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.mybatis.enums.StyleType
     * @return  {@link java.lang.String} <p>The where sql of types return object is <code>String</code> type.</p>
     */
    public static <T> String whereSqlOfTypes(String prefix, Collection<T> types, Class<T> type, StyleType styleType) {
        List<Field> fieldList = fieldsOfType(type, Collections.emptyList(), Collections.emptyList());
        if (GeneralUtils.isEmpty(fieldList)) {
            return SqlBuilder.EMPTY;
        }
        List<T> typeList = types.stream().filter(typeValue -> valueOfType(typeValue, fieldList)).collect(Collectors.toList());
        Map<Integer, List<T>> typesOfMap = sliceOfType(typeList, fieldList);
        return whereSqlOfTypes(prefix,typesOfMap, fieldList, styleType);
    }

    /**
     * <code>whereSqlOfTypes</code>
     * <p>The where sql of types method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param typesOfMap {@link java.util.Map} <p>The types of map parameter is <code>Map</code> type.</p>
     * @param fieldList {@link java.util.List} <p>The field list parameter is <code>List</code> type.</p>
     * @param styleType {@link io.github.nichetoolkit.mybatis.enums.StyleType} <p>The style type parameter is <code>StyleType</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Map
     * @see  java.util.List
     * @see  io.github.nichetoolkit.mybatis.enums.StyleType
     * @see  java.lang.SuppressWarnings
     * @return  {@link java.lang.String} <p>The where sql of types return object is <code>String</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public static <T> String whereSqlOfTypes(String prefix, Map<Integer, List<T>> typesOfMap, List<Field> fieldList, StyleType styleType) {
        if (GeneralUtils.isEmpty(typesOfMap)) {
            return SqlBuilder.EMPTY;
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.and().braceLt();
        for (Map.Entry<Integer, List<T>> entry : typesOfMap.entrySet()) {
            Integer key = entry.getKey();
            List<T> valueList = entry.getValue();
            if (GeneralUtils.isNotEmpty(key) && GeneralUtils.isNotEmpty(valueList)) {
                List<Number> indices = RestReckon.denexNumber(key);
                if (GeneralUtils.isNotEmpty(indices)) {
                    List<Field> denexFields = indices.stream()
                            .map(index -> fieldList.get(index.intValue()))
                            .collect(Collectors.toList());
                    RestStyle tableStyle = RestStyle.style(styleType);
                    boolean isMultiColumns = denexFields.size() > 1;
                    boolean isSingleValue = valueList.size() == 1;
                    if (isSingleValue) {
                        if (isMultiColumns) {
                            sqlBuilder.braceLt();
                        }
                        boolean isNotFirst = false;
                        T value = valueList.get(0);
                        for (Field field : denexFields) {
                            try {
                                Object fieldValue = field.get(value);
                                String columnName = tableStyle.columnName(field);
                                if (GeneralUtils.isNotEmpty(prefix)) {
                                    columnName = prefix + columnName;
                                }
                                sqlBuilder.eq(columnName, fieldValue, isNotFirst ? true : null);
                                isNotFirst = true;
                            } catch (IllegalAccessException ignored) {
                            }
                        }
                        if (isMultiColumns) {
                            sqlBuilder.braceGt();
                        }
                    } else {
                        String fieldSql = denexFields.stream().map(field -> {
                            String columnName = tableStyle.columnName(field);
                            if (GeneralUtils.isNotEmpty(prefix)) {
                                columnName = prefix + columnName;
                            }
                            return columnName;
                        }).collect(Collectors.joining(SQLConstants.COMMA));
                        if (isMultiColumns) {
                            sqlBuilder.braceLt().append(fieldSql).braceGt().in().braceLt();
                        } else {
                            sqlBuilder.append(fieldSql).in().braceLt();
                        }
                        valueList.forEach(value -> {
                            if (isMultiColumns) {
                                sqlBuilder.braceLt();
                            }
                            denexFields.forEach(field -> {
                                try {
                                    Object indexValue = field.get(value);
                                    sqlBuilder.value(indexValue).comma();
                                } catch (IllegalAccessException ignored) {
                                }
                            });
                            sqlBuilder.deleteLastChar();
                            if (isMultiColumns) {
                                sqlBuilder.braceGt().comma();
                            } else {
                                sqlBuilder.comma();
                            }
                        });
                        sqlBuilder.deleteLastChar().braceGt();
                    }

                }
                sqlBuilder.or();
            }
        }
        sqlBuilder.delete(sqlBuilder.length() - 4).braceGt();
        return sqlBuilder.toString();
    }
}
