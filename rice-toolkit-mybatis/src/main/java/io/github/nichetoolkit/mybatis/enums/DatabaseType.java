package io.github.nichetoolkit.mybatis.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.mybatis.consts.DriverConstants;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>DatabaseType</code>
 * <p>The database type enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestValue
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum DatabaseType implements RestValue<String, String> {
    /**
     * <code>POSTGRESQL</code>
     * <p>The postgresql database type field.</p>
     */
    POSTGRESQL("postgresql", DriverConstants.POSTGRESQL),
    /**
     * <code>GAUSSDB</code>
     * <p>The gaussdb database type field.</p>
     */
    GAUSSDB("gaussdb", DriverConstants.POSTGRESQL),
    /**
     * <code>MYSQL</code>
     * <p>The mysql database type field.</p>
     */
    MYSQL("mysql", DriverConstants.MYSQL),
    /**
     * <code>SQLITE</code>
     * <p>The sqlite database type field.</p>
     */
    SQLITE("sqlite", DriverConstants.SQLITE),
    /**
     * <code>MARIADB</code>
     * <p>The mariadb database type field.</p>
     */
    MARIADB("mariadb", DriverConstants.MARIADB),
    /**
     * <code>SQLSEVER</code>
     * <p>The sqlsever database type field.</p>
     */
    SQLSEVER("sqlsever", DriverConstants.SQLSEVER),
    /**
     * <code>ORACLE</code>
     * <p>The oracle database type field.</p>
     */
    ORACLE("oracle", DriverConstants.ORACLE),
    /**
     * <code>H2</code>
     * <p>The h 2 database type field.</p>
     */
    H2("h2", DriverConstants.H2),
    /**
     * <code>HSQLDB</code>
     * <p>The hsqldb database type field.</p>
     */
    HSQLDB("hsqldb", DriverConstants.HSQLDB),
    /**
     * <code>REDSHIFT</code>
     * <p>The redshift database type field.</p>
     */
    REDSHIFT("redshift", DriverConstants.REDSHIFT),
    /**
     * <code>CASSANDRA</code>
     * <p>The cassandra database type field.</p>
     */
    CASSANDRA("cassandra", DriverConstants.CASSANDRA),
    ;
    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see  java.lang.String
     */
    private final String key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see  java.lang.String
     */
    private final String value;

    /**
     * <code>DatabaseType</code>
     * <p>Instantiates a new database type.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    DatabaseType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.mybatis.enums.DatabaseType} <p>The parse key return object is <code>DatabaseType</code> type.</p>
     */
    @JsonCreator
    public static DatabaseType parseKey(String key) {
        DatabaseType datasourceDriver = RestValue.parseKey(DatabaseType.class, key);
        return Optional.ofNullable(datasourceDriver).orElse(DatabaseType.POSTGRESQL);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return  {@link io.github.nichetoolkit.mybatis.enums.DatabaseType} <p>The parse value return object is <code>DatabaseType</code> type.</p>
     */
    public static DatabaseType parseValue(String value) {
        DatabaseType datasourceDriver = RestValue.parseValue(DatabaseType.class, value);
        return Optional.ofNullable(datasourceDriver).orElse(DatabaseType.POSTGRESQL);
    }

}
