package io.github.nichetoolkit.rice.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestArithmetic;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>PermitType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum PermitType implements RestArithmetic {
    /** 无操作 */
    DEFAULT_NONE(0L,"无操作",0L),
    /** 数据新增 */
    DATA_CREATE(1L,"数据新增",1L),
    /** 数据更新 */
    DATA_UPDATE(2L,"数据更新",2L),
    /** 数据保存 */
    DATA_SAVE(3L,"数据保存",3L),
    /** 数据删除 */
    DATA_DELETE(4L,"数据删除",4L),
    /** 数据查询 */
    DATA_QUERY(8L,"数据查询",8L),
    /** 文件上传 */
    FILE_UPLOAD(16L,"文件上传",16L),
    /** 文件下载 */
    FILE_DOWNLOAD(32L,"文件下载",32L),
    /** 文件删除 */
    FILE_DELETE(64L,"文件删除",64L),
    ;

    private final Long key;
    private final String value;
    private final Long arithmetic;

    PermitType(Long key, String value, Long arithmetic) {
        this.key = key;
        this.value = value;
        this.arithmetic = arithmetic;
    }

    @JsonValue
    @Override
    public Long getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Long getArithmetic() {
        return this.arithmetic;
    }


    @JsonCreator
    public static PermitType parseKey(Long key) {
        PermitType sortTypeEnum = RestValue.parseKey(PermitType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(PermitType.DEFAULT_NONE);
    }

    public static PermitType parseValue(String value) {
        PermitType sortTypeEnum = RestValue.parseValue(PermitType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(PermitType.DEFAULT_NONE);
    }

    public static PermitType parseArithmetic(Long arithmetic) {
        PermitType typeEnum = RestArithmetic.parseArithmetic(PermitType.class, arithmetic);
        return Optional.ofNullable(typeEnum).orElse(PermitType.DEFAULT_NONE);
    }
}
