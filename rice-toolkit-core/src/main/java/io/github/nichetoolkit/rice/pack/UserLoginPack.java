package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.InfoModel;
import io.github.nichetoolkit.rice.RestUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>UserLoginPack</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginPack implements Serializable {
    protected UserInfoPack user;
}
