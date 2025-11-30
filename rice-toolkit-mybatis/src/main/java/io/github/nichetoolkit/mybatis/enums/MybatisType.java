package io.github.nichetoolkit.mybatis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import tools.jackson.databind.JavaType;
import io.github.nichetoolkit.mybatis.fickle.RestFickleType;
import io.github.nichetoolkit.mybatis.holder.RestSqlSessionHolder;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.jspecify.annotations.NonNull;

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

/**
 * <code>MybatisType</code>
 * <p>The mybatis type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.mybatis.fickle.RestFickleType
 * @since Jdk17
 */
public enum MybatisType implements RestFickleType {
    /**
     * <code>OBJECT</code>
     * <p>The object mybatis type field.</p>
     */
    OBJECT(1, JdbcType.JAVA_OBJECT, "object", Object.class),
    /**
     * <code>CHAR</code>
     * <p>The char mybatis type field.</p>
     */
    CHAR(2, JdbcType.CHAR, "string", String.class),
    /**
     * <code>NUMERIC</code>
     * <p>The numeric mybatis type field.</p>
     */
    NUMERIC(3, JdbcType.NUMERIC, "bigdecimal", BigDecimal.class),
    /**
     * <code>DECIMAL</code>
     * <p>The decimal mybatis type field.</p>
     */
    DECIMAL(4, JdbcType.DECIMAL, "decimal", BigDecimal.class),
    /**
     * <code>INTEGER</code>
     * <p>The integer mybatis type field.</p>
     */
    INTEGER(5, JdbcType.INTEGER, "integer", Integer.class),
    /**
     * <code>SMALLINT</code>
     * <p>The smallint mybatis type field.</p>
     */
    SMALLINT(6, JdbcType.SMALLINT, "short", Short.class),
    /**
     * <code>FLOAT</code>
     * <p>The float mybatis type field.</p>
     */
    FLOAT(7, JdbcType.FLOAT, "float",  Float.class),
    /**
     * <code>REAL</code>
     * <p>The real mybatis type field.</p>
     */
    REAL(8, JdbcType.REAL, "bigdecimal", BigDecimal.class),
    /**
     * <code>DOUBLE</code>
     * <p>The double mybatis type field.</p>
     */
    DOUBLE(9, JdbcType.DOUBLE, "double", Double.class),
    /**
     * <code>VARCHAR</code>
     * <p>The varchar mybatis type field.</p>
     */
    VARCHAR(10, JdbcType.VARCHAR, "string", String.class),
    /**
     * <code>BOOLEAN</code>
     * <p>The boolean mybatis type field.</p>
     */
    BOOLEAN(11, JdbcType.BOOLEAN, "boolean", Boolean.class),
    /**
     * <code>DATE</code>
     * <p>The date mybatis type field.</p>
     */
    DATE(12, JdbcType.DATE, "date", Date.class),
    /**
     * <code>TIME</code>
     * <p>The time mybatis type field.</p>
     */
    TIME(13, JdbcType.TIME, "date", Date.class),
    /**
     * <code>TIMESTAMP</code>
     * <p>The timestamp mybatis type field.</p>
     */
    TIMESTAMP(14, JdbcType.TIMESTAMP, "date", Date.class),

    /**
     * <code>ARRAY</code>
     * <p>The array mybatis type field.</p>
     */
    ARRAY(21, JdbcType.ARRAY, "object", Object.class),
    /**
     * <code>BLOB</code>
     * <p>The blob mybatis type field.</p>
     */
    BLOB(22, JdbcType.BLOB, "byte[]", Byte[].class),
    /**
     * <code>CLOB</code>
     * <p>The clob mybatis type field.</p>
     */
    CLOB(23, JdbcType.CLOB, "string", String.class),
    /**
     * <code>SQLXML</code>
     * <p>The sqlxml mybatis type field.</p>
     */
    SQLXML(24, JdbcType.SQLXML, "string", String.class),
    /**
     * <code>NCLOB</code>
     * <p>The nclob mybatis type field.</p>
     */
    NCLOB(25, JdbcType.NCLOB, "string", String.class),
    /**
     * <code>TIME_WITH_TIMEZONE</code>
     * <p>The time with timezone mybatis type field.</p>
     */
    TIME_WITH_TIMEZONE(26, JdbcType.TIME_WITH_TIMEZONE, "timestamp", Date.class),
    /**
     * <code>TIMESTAMP_WITH_TIMEZONE</code>
     * <p>The timestamp with timezone mybatis type field.</p>
     */
    TIMESTAMP_WITH_TIMEZONE(27, JdbcType.TIMESTAMP_WITH_TIMEZONE, "timestamptz", Date.class),

    /**
     * <code>LONGVARCHAR</code>
     * <p>The longvarchar mybatis type field.</p>
     */
    LONGVARCHAR(31, JdbcType.LONGVARCHAR, "string", String.class),
    /**
     * <code>BINARY</code>
     * <p>The binary mybatis type field.</p>
     */
    BINARY(32, JdbcType.BINARY, "string", String.class),
    /**
     * <code>VARBINARY</code>
     * <p>The varbinary mybatis type field.</p>
     */
    VARBINARY(33, JdbcType.VARBINARY, "string", String.class),
    /**
     * <code>LONGVARBINARY</code>
     * <p>The longvarbinary mybatis type field.</p>
     */
    LONGVARBINARY(34, JdbcType.LONGVARBINARY, "string", String.class),
    /**
     * <code>BIGINT</code>
     * <p>The bigint mybatis type field.</p>
     */
    BIGINT(35, JdbcType.BIGINT, "biginteger", BigInteger.class),
    /**
     * <code>TINYINT</code>
     * <p>The tinyint mybatis type field.</p>
     */
    TINYINT(36, JdbcType.TINYINT, "byte", Byte.class),
    /**
     * <code>BIT</code>
     * <p>The bit mybatis type field.</p>
     */
    BIT(37, JdbcType.BIT, "byte", Byte.class),
    /**
     * <code>NVARCHAR</code>
     * <p>The nvarchar mybatis type field.</p>
     */
    NVARCHAR(38, JdbcType.NVARCHAR, "string", String.class),
    /**
     * <code>NCHAR</code>
     * <p>The nchar mybatis type field.</p>
     */
    NCHAR(39, JdbcType.NCHAR, "string", String.class),

    /**
     * <code>READER</code>
     * <p>The reader mybatis type field.</p>
     */
    READER(41, JdbcType.LONGNVARCHAR, "string", Reader.class),
    /**
     * <code>STREAM</code>
     * <p>The stream mybatis type field.</p>
     */
    STREAM(42, JdbcType.BLOB, "byte[]", InputStream.class),

    /**
     * <code>INSTANT</code>
     * <p>The instant mybatis type field.</p>
     */
    INSTANT(51, JdbcType.DATE, "date", Instant.class, DATE),
    /**
     * <code>LOCAL_DATE_TIME</code>
     * <p>The local date time mybatis type field.</p>
     */
    LOCAL_DATE_TIME(52, JdbcType.LONGNVARCHAR, "date", LocalDateTime.class, DATE),
    /**
     * <code>LOCAL_DATE</code>
     * <p>The local date mybatis type field.</p>
     */
    LOCAL_DATE(53, JdbcType.LONGNVARCHAR, "date", LocalDate.class, DATE),
    /**
     * <code>LOCAL_TIME</code>
     * <p>The local time mybatis type field.</p>
     */
    LOCAL_TIME(54, JdbcType.LONGNVARCHAR, "date", LocalTime.class, DATE),
    /**
     * <code>OFFSET_DATE_TIME</code>
     * <p>The offset date time mybatis type field.</p>
     */
    OFFSET_DATE_TIME(55, JdbcType.LONGNVARCHAR, "date", OffsetDateTime.class, DATE),
    /**
     * <code>OFFSET_TIME</code>
     * <p>The offset time mybatis type field.</p>
     */
    OFFSET_TIME(56, JdbcType.LONGNVARCHAR, "date", OffsetTime.class, DATE),
    /**
     * <code>ZONED_DATE_TIME</code>
     * <p>The zoned date time mybatis type field.</p>
     */
    ZONED_DATE_TIME(57, JdbcType.LONGNVARCHAR, "date", ZonedDateTime.class, DATE),
    /**
     * <code>MONTH</code>
     * <p>The month mybatis type field.</p>
     */
    MONTH(58, JdbcType.LONGNVARCHAR, "date", Month.class, DATE),
    /**
     * <code>YEAR</code>
     * <p>The year mybatis type field.</p>
     */
    YEAR(59, JdbcType.LONGNVARCHAR, "date", Year.class, DATE),
    /**
     * <code>YEAR_MONTH</code>
     * <p>The year month mybatis type field.</p>
     */
    YEAR_MONTH(60, JdbcType.LONGNVARCHAR, "date", YearMonth.class, DATE),
    /**
     * <code>JAPANESE_DATE</code>
     * <p>The japanese date mybatis type field.</p>
     */
    JAPANESE_DATE(61, JdbcType.LONGNVARCHAR, "date", JapaneseDate.class, DATE),
    /**
     * <code>CHARACTER</code>
     * <p>The character mybatis type field.</p>
     */
    CHARACTER(62, JdbcType.CHAR, "string", Character.class, DATE),

    /**
     * <code>STRING</code>
     * <p>The string mybatis type field.</p>
     */
    STRING(71, JdbcType.VARCHAR, "string", String.class),
    /**
     * <code>LONG</code>
     * <p>The long mybatis type field.</p>
     */
    LONG(72, JdbcType.BIGINT, "long", Long.class),
    /**
     * <code>INT</code>
     * <p>The int mybatis type field.</p>
     */
    INT(73, JdbcType.INTEGER, "int", Integer.class, INTEGER),

    /**
     * <code>LONG_ARRAY</code>
     * <p>The long array mybatis type field.</p>
     */
    LONG_ARRAY(81, JdbcType.ARRAY, "long[]", Long[].class, ARRAY),
    /**
     * <code>SHORT_ARRAY</code>
     * <p>The short array mybatis type field.</p>
     */
    SHORT_ARRAY(82, JdbcType.ARRAY, "short[]", Short[].class, ARRAY),
    /**
     * <code>INT_ARRAY</code>
     * <p>The int array mybatis type field.</p>
     */
    INT_ARRAY(83, JdbcType.ARRAY, "int[]", Integer[].class, ARRAY),
    /**
     * <code>INTEGER_ARRAY</code>
     * <p>The integer array mybatis type field.</p>
     */
    INTEGER_ARRAY(84, JdbcType.ARRAY, "integer[]", Integer[].class, ARRAY),
    /**
     * <code>DOUBLE_ARRAY</code>
     * <p>The double array mybatis type field.</p>
     */
    DOUBLE_ARRAY(85, JdbcType.ARRAY, "double[]", Double[].class, ARRAY),
    /**
     * <code>FLOAT_ARRAY</code>
     * <p>The float array mybatis type field.</p>
     */
    FLOAT_ARRAY(86, JdbcType.ARRAY, "float[]", Float[].class, ARRAY),
    /**
     * <code>BOOLEAN_ARRAY</code>
     * <p>The boolean array mybatis type field.</p>
     */
    BOOLEAN_ARRAY(87, JdbcType.ARRAY, "boolean[]", Boolean[].class, ARRAY),

    /**
     * <code>BASE_BYTE</code>
     * <p>The base byte mybatis type field.</p>
     */
    BASE_BYTE(91, JdbcType.TINYINT, "_byte", Byte.class, TINYINT),
    /**
     * <code>BASE_LONG</code>
     * <p>The base long mybatis type field.</p>
     */
    BASE_LONG(92, JdbcType.BIGINT, "_long", Long.class, BIGINT),
    /**
     * <code>BASE_SHORT</code>
     * <p>The base short mybatis type field.</p>
     */
    BASE_SHORT(93, JdbcType.SMALLINT, "_short", Short.class, SMALLINT),
    /**
     * <code>BASE_INT</code>
     * <p>The base int mybatis type field.</p>
     */
    BASE_INT(94, JdbcType.INTEGER, "_int", Integer.class, INTEGER),
    /**
     * <code>BASE_INTEGER</code>
     * <p>The base integer mybatis type field.</p>
     */
    BASE_INTEGER(95, JdbcType.INTEGER, "_integer", Integer.class, INTEGER),
    /**
     * <code>BASE_DOUBLE</code>
     * <p>The base double mybatis type field.</p>
     */
    BASE_DOUBLE(96, JdbcType.DOUBLE, "_double", Double.class, DOUBLE),
    /**
     * <code>BASE_FLOAT</code>
     * <p>The base float mybatis type field.</p>
     */
    BASE_FLOAT(97, JdbcType.FLOAT, "_float", Float.class, FLOAT),
    /**
     * <code>BASE_BOOLEAN</code>
     * <p>The base boolean mybatis type field.</p>
     */
    BASE_BOOLEAN(98, JdbcType.BOOLEAN, "_boolean", Boolean.class, BOOLEAN),

    /**
     * <code>BASE_BYTE_ARRAY</code>
     * <p>The base byte array mybatis type field.</p>
     */
    BASE_BYTE_ARRAY(101, JdbcType.ARRAY, "_byte[]", Byte[].class, ARRAY),
    /**
     * <code>BASE_LONG_ARRAY</code>
     * <p>The base long array mybatis type field.</p>
     */
    BASE_LONG_ARRAY(102, JdbcType.ARRAY, "_long[]", Long[].class, ARRAY),
    /**
     * <code>BASE_SHORT_ARRAY</code>
     * <p>The base short array mybatis type field.</p>
     */
    BASE_SHORT_ARRAY(103, JdbcType.ARRAY, "_short[]", Short[].class, ARRAY),
    /**
     * <code>BASE_INT_ARRAY</code>
     * <p>The base int array mybatis type field.</p>
     */
    BASE_INT_ARRAY(104, JdbcType.ARRAY, "_int[]", Integer[].class, ARRAY),
    /**
     * <code>BASE_INTEGER_ARRAY</code>
     * <p>The base integer array mybatis type field.</p>
     */
    BASE_INTEGER_ARRAY(105, JdbcType.ARRAY, "_integer[]", Integer[].class, ARRAY),
    /**
     * <code>BASE_DOUBLE_ARRAY</code>
     * <p>The base double array mybatis type field.</p>
     */
    BASE_DOUBLE_ARRAY(106, JdbcType.ARRAY, "_double[]", Double[].class, ARRAY),
    /**
     * <code>BASE_FLOAT_ARRAY</code>
     * <p>The base float array mybatis type field.</p>
     */
    BASE_FLOAT_ARRAY(107, JdbcType.ARRAY, "_float[]", Float[].class, ARRAY),
    /**
     * <code>BASE_BOOLEAN_ARRAY</code>
     * <p>The base boolean array mybatis type field.</p>
     */
    BASE_BOOLEAN_ARRAY(108, JdbcType.ARRAY, "_boolean[]", Boolean[].class, ARRAY),

    /**
     * <code>DATE_ARRAY</code>
     * <p>The date array mybatis type field.</p>
     */
    DATE_ARRAY(111, JdbcType.ARRAY, "date[]", Date[].class, ARRAY),
    /**
     * <code>DECIMAL_ARRAY</code>
     * <p>The decimal array mybatis type field.</p>
     */
    DECIMAL_ARRAY(112, JdbcType.ARRAY, "decimal[]", BigDecimal[].class, ARRAY),
    /**
     * <code>BIGDECIMAL_ARRAY</code>
     * <p>The bigdecimal array mybatis type field.</p>
     */
    BIGDECIMAL_ARRAY(113, JdbcType.ARRAY, "bigdecimal[]", BigDecimal[].class, ARRAY),
    /**
     * <code>BIGINTEGER_ARRAY</code>
     * <p>The biginteger array mybatis type field.</p>
     */
    BIGINTEGER_ARRAY(114, JdbcType.ARRAY, "biginteger[]", BigInteger[].class, ARRAY),
    /**
     * <code>OBJECT_ARRAY</code>
     * <p>The object array mybatis type field.</p>
     */
    OBJECT_ARRAY(115, JdbcType.ARRAY, "object[]", Object[].class, ARRAY),

    /**
     * <code>MAP</code>
     * <p>The map mybatis type field.</p>
     */
    MAP(121, JdbcType.OTHER, "map", Map.class, OBJECT),
    /**
     * <code>HASHMAP</code>
     * <p>The hashmap mybatis type field.</p>
     */
    HASHMAP(122, JdbcType.OTHER, "hashmap", HashMap.class, OBJECT),
    /**
     * <code>LIST</code>
     * <p>The list mybatis type field.</p>
     */
    LIST(123, JdbcType.OTHER, "list", List.class, OBJECT),
    /**
     * <code>ARRAY_LIST</code>
     * <p>The array list mybatis type field.</p>
     */
    ARRAY_LIST(124, JdbcType.OTHER, "arraylist", ArrayList.class, OBJECT),
    /**
     * <code>SET</code>
     * <p>The set mybatis type field.</p>
     */
    SET(125, JdbcType.OTHER, "set", ArrayList.class, OBJECT),
    /**
     * <code>HASHSET</code>
     * <p>The hashset mybatis type field.</p>
     */
    HASHSET(126, JdbcType.OTHER, "hashset", ArrayList.class, OBJECT),
    /**
     * <code>COLLECTION</code>
     * <p>The collection mybatis type field.</p>
     */
    COLLECTION(127, JdbcType.OTHER, "collection", Collection.class, OBJECT),
    /**
     * <code>ITERATOR</code>
     * <p>The iterator mybatis type field.</p>
     */
    ITERATOR(128, JdbcType.OTHER, "iterator", Iterator.class, OBJECT),

    /**
     * <code>RESULT_SET</code>
     * <p>The result set mybatis type field.</p>
     */
    RESULT_SET(131, JdbcType.OTHER, "ResultSet", ResultSet.class, OBJECT),

    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>alias</code>
     * {@link java.lang.String} <p>The <code>alias</code> field.</p>
     * @see java.lang.String
     */
    private final String alias;
    /**
     * <code>jdbcType</code>
     * {@link org.apache.ibatis.type.JdbcType} <p>The <code>jdbcType</code> field.</p>
     * @see org.apache.ibatis.type.JdbcType
     */
    private final JdbcType jdbcType;
    /**
     * <code>type</code>
     * {@link java.lang.Class} <p>The <code>type</code> field.</p>
     * @see java.lang.Class
     */
    private final Class<?> type;
    /**
     * <code>reference</code>
     * {@link io.github.nichetoolkit.mybatis.enums.MybatisType} <p>The <code>reference</code> field.</p>
     */
    private final MybatisType reference;

    /**
     * <code>MybatisType</code>
     * <p>Instantiates a new mybatis type.</p>
     * @param key      {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param jdbcType {@link org.apache.ibatis.type.JdbcType} <p>The jdbc type parameter is <code>JdbcType</code> type.</p>
     * @param alias    {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param type     {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @see java.lang.Integer
     * @see org.apache.ibatis.type.JdbcType
     * @see java.lang.String
     * @see java.lang.Class
     */
    MybatisType(Integer key, JdbcType jdbcType, String alias, Class<?> type) {
        this.key = jdbcType.TYPE_CODE;
        this.alias = alias;
        this.jdbcType = jdbcType;
        this.type = type;
        this.reference = null;
    }

    /**
     * <code>MybatisType</code>
     * <p>Instantiates a new mybatis type.</p>
     * @param key       {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param jdbcType  {@link org.apache.ibatis.type.JdbcType} <p>The jdbc type parameter is <code>JdbcType</code> type.</p>
     * @param alias     {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param type      {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @param reference {@link io.github.nichetoolkit.mybatis.enums.MybatisType} <p>The reference parameter is <code>MybatisType</code> type.</p>
     * @see java.lang.Integer
     * @see org.apache.ibatis.type.JdbcType
     * @see java.lang.String
     * @see java.lang.Class
     */
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

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.enums.MybatisType} <p>The parse key return object is <code>MybatisType</code> type.</p>
     * @see java.lang.Integer
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static MybatisType parseKey(Integer key) {
        MybatisType typeEnum = RestKey.parseKey(MybatisType.class, key);
        return Optional.ofNullable(typeEnum).orElse(MybatisType.OBJECT);
    }


    /**
     * <code>parseClazz</code>
     * <p>The parse clazz method.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.enums.MybatisType} <p>The parse clazz return object is <code>MybatisType</code> type.</p>
     * @see java.lang.Class
     */
    public static MybatisType parseClazz(Class<?> clazz) {
        MybatisType typeEnum = null;
        if (clazz != null) {
            Map<Class<?>, MybatisType> keyEnumMap = Stream.of(values()).collect(Collectors.toMap(MybatisType::getType, Function.identity(), (oldValue, newValue) -> oldValue, HashMap::new));
            typeEnum = keyEnumMap.get(clazz);
        }
        return Optional.ofNullable(typeEnum).orElse(MybatisType.OBJECT);
    }

    /**
     * <code>parseJdbcType</code>
     * <p>The parse jdbc type method.</p>
     * @param jdbcType {@link org.apache.ibatis.type.JdbcType} <p>The jdbc type parameter is <code>JdbcType</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.enums.MybatisType} <p>The parse jdbc type return object is <code>MybatisType</code> type.</p>
     * @see org.apache.ibatis.type.JdbcType
     */
    public static MybatisType parseJdbcType(JdbcType jdbcType) {
        MybatisType typeEnum = null;
        if (jdbcType != null) {
            Map<JdbcType, MybatisType> keyEnumMap = Stream.of(values()).collect(Collectors.toMap(MybatisType::getJdbcType, Function.identity(), (oldValue, newValue) -> oldValue, HashMap::new));
            typeEnum = keyEnumMap.get(jdbcType);
        }
        return Optional.ofNullable(typeEnum).orElse(MybatisType.OBJECT);
    }

    /**
     * <code>parseAlias</code>
     * <p>The parse alias method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.mybatis.enums.MybatisType} <p>The parse alias return object is <code>MybatisType</code> type.</p>
     * @see java.lang.String
     */
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
