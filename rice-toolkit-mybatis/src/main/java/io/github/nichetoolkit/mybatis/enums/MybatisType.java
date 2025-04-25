package io.github.nichetoolkit.mybatis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JavaType;
import io.github.nichetoolkit.mybatis.fickle.RestFickleType;
import io.github.nichetoolkit.mybatis.holder.RestSqlSessionHolder;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.lang.NonNull;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.time.*;
import java.time.chrono.JapaneseDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MybatisType implements RestFickleType {
    OBJECT(1, JdbcType.JAVA_OBJECT, "object", Object.class),
    CHAR(2, JdbcType.CHAR, "string", String.class),
    NUMERIC(3, JdbcType.NUMERIC, "bigdecimal", BigDecimal.class),
    DECIMAL(4, JdbcType.DECIMAL, "decimal", BigDecimal.class),
    INTEGER(5, JdbcType.INTEGER, "integer", Integer.class),
    SMALLINT(6, JdbcType.SMALLINT, "short", Short.class),
    FLOAT(7, JdbcType.FLOAT, "float",  Float.class),
    REAL(8, JdbcType.REAL, "bigdecimal", BigDecimal.class),
    DOUBLE(9, JdbcType.DOUBLE, "double", Double.class),
    VARCHAR(10, JdbcType.VARCHAR, "string", String.class),
    BOOLEAN(11, JdbcType.BOOLEAN, "boolean", Boolean.class),
    DATE(12, JdbcType.DATE, "date", Date.class),
    TIME(13, JdbcType.TIME, "date", Date.class),
    TIMESTAMP(14, JdbcType.TIMESTAMP, "date", Date.class),

    ARRAY(21, JdbcType.ARRAY, "object", Object.class),
    BLOB(22, JdbcType.BLOB, "byte[]", Byte[].class),
    CLOB(23, JdbcType.CLOB, "string", String.class),
    SQLXML(24, JdbcType.SQLXML, "string", String.class),
    NCLOB(25, JdbcType.NCLOB, "string", String.class),
    TIME_WITH_TIMEZONE(26, JdbcType.TIME_WITH_TIMEZONE, "timestamp", Date.class),
    TIMESTAMP_WITH_TIMEZONE(27, JdbcType.TIMESTAMP_WITH_TIMEZONE, "timestamptz", Date.class),

    LONGVARCHAR(31, JdbcType.LONGVARCHAR, "string", String.class),
    BINARY(32, JdbcType.BINARY, "string", String.class),
    VARBINARY(33, JdbcType.VARBINARY, "string", String.class),
    LONGVARBINARY(34, JdbcType.LONGVARBINARY, "string", String.class),
    BIGINT(35, JdbcType.BIGINT, "biginteger", BigInteger.class),
    TINYINT(36, JdbcType.TINYINT, "byte", Byte.class),
    BIT(37, JdbcType.BIT, "byte", Byte.class),
    NVARCHAR(38, JdbcType.NVARCHAR, "string", String.class),
    NCHAR(39, JdbcType.NCHAR, "string", String.class),

    READER(41, JdbcType.LONGNVARCHAR, "string", Reader.class),
    STREAM(42, JdbcType.BLOB, "byte[]", InputStream.class),

    INSTANT(51, JdbcType.DATE, "date", Instant.class, DATE),
    LOCAL_DATE_TIME(52, JdbcType.LONGNVARCHAR, "date", LocalDateTime.class, DATE),
    LOCAL_DATE(53, JdbcType.LONGNVARCHAR, "date", LocalDate.class, DATE),
    LOCAL_TIME(54, JdbcType.LONGNVARCHAR, "date", LocalTime.class, DATE),
    OFFSET_DATE_TIME(55, JdbcType.LONGNVARCHAR, "date", OffsetDateTime.class, DATE),
    OFFSET_TIME(56, JdbcType.LONGNVARCHAR, "date", OffsetTime.class, DATE),
    ZONED_DATE_TIME(57, JdbcType.LONGNVARCHAR, "date", ZonedDateTime.class, DATE),
    MONTH(58, JdbcType.LONGNVARCHAR, "date", Month.class, DATE),
    YEAR(59, JdbcType.LONGNVARCHAR, "date", Year.class, DATE),
    YEAR_MONTH(60, JdbcType.LONGNVARCHAR, "date", YearMonth.class, DATE),
    JAPANESE_DATE(61, JdbcType.LONGNVARCHAR, "date", JapaneseDate.class, DATE),
    CHARACTER(62, JdbcType.CHAR, "string", Character.class, DATE),

    STRING(71, JdbcType.VARCHAR, "string", String.class),
    LONG(72, JdbcType.BIGINT, "long", Long.class),
    INT(73, JdbcType.INTEGER, "int", Integer.class, INTEGER),

    LONG_ARRAY(81, JdbcType.ARRAY, "long[]", Long[].class, ARRAY),
    SHORT_ARRAY(82, JdbcType.ARRAY, "short[]", Short[].class, ARRAY),
    INT_ARRAY(83, JdbcType.ARRAY, "int[]", Integer[].class, ARRAY),
    INTEGER_ARRAY(84, JdbcType.ARRAY, "integer[]", Integer[].class, ARRAY),
    DOUBLE_ARRAY(85, JdbcType.ARRAY, "double[]", Double[].class, ARRAY),
    FLOAT_ARRAY(86, JdbcType.ARRAY, "float[]", Float[].class, ARRAY),
    BOOLEAN_ARRAY(87, JdbcType.ARRAY, "boolean[]", Boolean[].class, ARRAY),

    BASE_BYTE(91, JdbcType.TINYINT, "_byte", Byte.class, TINYINT),
    BASE_LONG(92, JdbcType.BIGINT, "_long", Long.class, BIGINT),
    BASE_SHORT(93, JdbcType.SMALLINT, "_short", Short.class, SMALLINT),
    BASE_INT(94, JdbcType.INTEGER, "_int", Integer.class, INTEGER),
    BASE_INTEGER(95, JdbcType.INTEGER, "_integer", Integer.class, INTEGER),
    BASE_DOUBLE(96, JdbcType.DOUBLE, "_double", Double.class, DOUBLE),
    BASE_FLOAT(97, JdbcType.FLOAT, "_float", Float.class, FLOAT),
    BASE_BOOLEAN(98, JdbcType.BOOLEAN, "_boolean", Boolean.class, BOOLEAN),

    BASE_BYTE_ARRAY(101, JdbcType.ARRAY, "_byte[]", Byte[].class, ARRAY),
    BASE_LONG_ARRAY(102, JdbcType.ARRAY, "_long[]", Long[].class, ARRAY),
    BASE_SHORT_ARRAY(103, JdbcType.ARRAY, "_short[]", Short[].class, ARRAY),
    BASE_INT_ARRAY(104, JdbcType.ARRAY, "_int[]", Integer[].class, ARRAY),
    BASE_INTEGER_ARRAY(105, JdbcType.ARRAY, "_integer[]", Integer[].class, ARRAY),
    BASE_DOUBLE_ARRAY(106, JdbcType.ARRAY, "_double[]", Double[].class, ARRAY),
    BASE_FLOAT_ARRAY(107, JdbcType.ARRAY, "_float[]", Float[].class, ARRAY),
    BASE_BOOLEAN_ARRAY(108, JdbcType.ARRAY, "_boolean[]", Boolean[].class, ARRAY),

    DATE_ARRAY(111, JdbcType.ARRAY, "date[]", Date[].class, ARRAY),
    DECIMAL_ARRAY(112, JdbcType.ARRAY, "decimal[]", BigDecimal[].class, ARRAY),
    BIGDECIMAL_ARRAY(113, JdbcType.ARRAY, "bigdecimal[]", BigDecimal[].class, ARRAY),
    BIGINTEGER_ARRAY(114, JdbcType.ARRAY, "biginteger[]", BigInteger[].class, ARRAY),
    OBJECT_ARRAY(115, JdbcType.ARRAY, "object[]", Object[].class, ARRAY),

    MAP(121, JdbcType.OTHER, "map", Map.class, OBJECT),
    HASHMAP(122, JdbcType.OTHER, "hashmap", HashMap.class, OBJECT),
    LIST(123, JdbcType.OTHER, "list", List.class, OBJECT),
    ARRAY_LIST(124, JdbcType.OTHER, "arraylist", ArrayList.class, OBJECT),
    SET(125, JdbcType.OTHER, "set", ArrayList.class, OBJECT),
    HASHSET(126, JdbcType.OTHER, "hashset", ArrayList.class, OBJECT),
    COLLECTION(127, JdbcType.OTHER, "collection", Collection.class, OBJECT),
    ITERATOR(128, JdbcType.OTHER, "iterator", Iterator.class, OBJECT),

    RESULT_SET(131, JdbcType.OTHER, "ResultSet", ResultSet.class, OBJECT),

    ;

    private final Integer key;
    private final String alias;
    private final JdbcType jdbcType;
    private final Class<?> type;
    private final MybatisType reference;

    MybatisType(Integer key, JdbcType jdbcType, String alias, Class<?> type) {
        this.key = jdbcType.TYPE_CODE;
        this.alias = alias;
        this.jdbcType = jdbcType;
        this.type = type;
        this.reference = null;
    }

    MybatisType(Integer key, JdbcType jdbcType, String alias, Class<?> type, MybatisType reference) {
        this.key = jdbcType.TYPE_CODE;
        this.alias = alias;
        this.jdbcType = jdbcType;
        this.type = type;
        this.reference = reference;
    }

    @NonNull
    @JsonValue
    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public JdbcType getJdbcType() {
        return this.jdbcType;
    }

    @Override
    public JavaType getJavaType() {
        return RestFickleType.super.getJavaType();
    }

    @Override
    public TypeHandler<?> getJdbcTypeHandler() {
        TypeHandlerRegistry typeHandlerRegistry = RestSqlSessionHolder.typeHandlerRegistry();
        if (typeHandlerRegistry != null) {
            if (this.reference != null) {
                MybatisType reference = this.reference;
                if (reference == ARRAY) {
                    return typeHandlerRegistry.getTypeHandler(ARRAY.getType());
                } else if (reference == OBJECT) {
                    return typeHandlerRegistry.getTypeHandler(OBJECT.getType());
                } else if (reference == DATE) {
                    return typeHandlerRegistry.getTypeHandler(this.getType());
                } else {
                    return typeHandlerRegistry.getTypeHandler(reference.getType());
                }
            } else {
                return typeHandlerRegistry.getTypeHandler(this.type);
            }
        }
        return null;
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }

    @JsonCreator
    public static MybatisType parseKey(Integer key) {
        MybatisType typeEnum = RestValue.parseKey(MybatisType.class, key);
        return Optional.ofNullable(typeEnum).orElse(MybatisType.OBJECT);
    }


    public static MybatisType parseClazz(Class<?> clazz) {
        MybatisType typeEnum = null;
        if (clazz != null) {
            Map<Class<?>, MybatisType> keyEnumMap = Stream.of(values()).collect(Collectors.toMap(MybatisType::getType, Function.identity(), (oldValue, newValue) -> oldValue, HashMap::new));
            typeEnum = keyEnumMap.get(clazz);
        }
        return Optional.ofNullable(typeEnum).orElse(MybatisType.OBJECT);
    }

    public static MybatisType parseJdbcType(JdbcType jdbcType) {
        MybatisType typeEnum = null;
        if (jdbcType != null) {
            Map<JdbcType, MybatisType> keyEnumMap = Stream.of(values()).collect(Collectors.toMap(MybatisType::getJdbcType, Function.identity(), (oldValue, newValue) -> oldValue, HashMap::new));
            typeEnum = keyEnumMap.get(jdbcType);
        }
        return Optional.ofNullable(typeEnum).orElse(MybatisType.OBJECT);
    }

    public static MybatisType parseAlias(String alias) {
        MybatisType typeEnum = null;
        if (GeneralUtils.isNotEmpty(alias)) {
            alias = alias.toLowerCase();
            Map<String, MybatisType> keyEnumMap = Stream.of(values()).collect(Collectors.toMap(MybatisType::getAlias, Function.identity(), (oldValue, newValue) -> oldValue, HashMap::new));
            typeEnum = keyEnumMap.get(alias);
        }
        return Optional.ofNullable(typeEnum).orElse(MybatisType.OBJECT);
    }


}
