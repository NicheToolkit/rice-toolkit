package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.InfoModel;
import io.github.nichetoolkit.rice.RestUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>UserInfoPack</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoPack extends InfoModel<String> implements RestUserInfo<String> {
    protected String username;
}
