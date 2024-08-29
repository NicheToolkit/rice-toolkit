package io.github.nichetoolkit.rice.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

@Getter
public enum TableErrorStatus implements RestStatus {
    TABLE_UNKNOWN_ERROR(11310,"the table has unknown error!"),
    TABLE_NAME_IS_NULL(11311, "the table name can not be null!"),
    TABLE_IS_NOT_EXIST(11312, "the table structure is not exist!"),
    TABLE_ALREADY_EXIST(11313, "the table structure already exists!"),
    ;

    private final Integer status;
    private final String message;

    TableErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getName() {
        return this.name().toLowerCase().replace("_", " ");
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
