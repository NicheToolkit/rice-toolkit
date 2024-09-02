package io.github.nichetoolkit.rice.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rice.InfoModel;
import io.github.nichetoolkit.rice.RestUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <code>UserInfoPack</code>
 * <p>The type user info pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.InfoModel
 * @see io.github.nichetoolkit.rice.RestUserInfo
 * @see lombok.Data
 * @see lombok.EqualsAndHashCode
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoPack extends InfoModel<String> implements RestUserInfo<String> {
    /**
     * <code>username</code>
     * {@link java.lang.String} <p>the <code>username</code> field.</p>
     * @see java.lang.String
     */
    protected String username;
}
