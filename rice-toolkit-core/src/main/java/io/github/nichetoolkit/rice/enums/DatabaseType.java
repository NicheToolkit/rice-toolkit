package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rice.consts.DatabaseConst;

import java.util.Optional;

/**
 * <p>DatabaseType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum DatabaseType implements RestKey<String> {
    /** postgresql 数据库 */
    POSTGRESQL(DatabaseConst.POSTGRESQL),
    /** mysql 数据库 */
    MYSQL(DatabaseConst.MYSQL),
    /** opengauss 数据库 */
    OPENGAUSS(DatabaseConst.OPENGAUSS),
    ;

    private final String key;

    DatabaseType(String key) {
        this.key = key;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @JsonCreator
    public static DatabaseType parseKey(String key) {
        DatabaseType typeEnum = RestKey.parseKey(DatabaseType.class, key);
        return Optional.ofNullable(typeEnum).orElse(DatabaseType.POSTGRESQL);
    }

}
