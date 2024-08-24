package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;
import io.github.nichetoolkit.rice.consts.DriverConst;

import java.util.Optional;

/**
 * <p>DatabaseDriver</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum DatabaseType implements RestValue<String, String> {
    /** PostgreSQL OpenGuss GaussDB */
    POSTGRESQL("postgresql", DriverConst.POSTGRESQL),
    /** MySQL */
    MYSQL("mysql", DriverConst.MYSQL),
    /** MariaDB */
    MARIADB("mariadb", DriverConst.MARIADB),
    /** Microsoft SQL Server */
    SQLSEVER("sqlsever", DriverConst.SQLSEVER),
    /** MariaDB */
    ORACLE("oracle", DriverConst.ORACLE),
    /** SQLite */
    SQLITE("sqlite", DriverConst.SQLITE),
    /** H2 */
    H2("h2", DriverConst.H2),
    /** HSQLDB */
    HSQLDB("hsqldb", DriverConst.HSQLDB),
    /** Amazon Redshift */
    REDSHIFT("redshift", DriverConst.REDSHIFT),
    /** Apache Cassandra */
    CASSANDRA("cassandra", DriverConst.CASSANDRA),
    ;
    private final String key;
    private final String value;

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

    @JsonCreator
    public static DatabaseType parseKey(String key) {
        DatabaseType datasourceDriver = RestValue.parseKey(DatabaseType.class, key);
        return Optional.ofNullable(datasourceDriver).orElse(DatabaseType.POSTGRESQL);
    }

    public static DatabaseType parseValue(String value) {
        DatabaseType datasourceDriver = RestValue.parseValue(DatabaseType.class, value);
        return Optional.ofNullable(datasourceDriver).orElse(DatabaseType.POSTGRESQL);
    }

}
