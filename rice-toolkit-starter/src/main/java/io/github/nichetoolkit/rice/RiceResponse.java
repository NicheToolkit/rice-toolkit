package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.userlog.RestResponse;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.pack.IdPack;
import io.github.nichetoolkit.rice.pack.UserInfoPack;
import io.github.nichetoolkit.rice.pack.UserLoginPack;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>RiceResponse</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceResponse extends RestResponse<RiceResponse> {
    public RiceResponse() {
    }

    public RiceResponse(RestResponse response) {
        BeanUtils.copyNonullProperties(response,this);
    }

    public boolean isSuccess() {
        return RestErrorStatus.SUCCESS.getStatus().equals(this.status);
    }

    public String toDataId() {
        if (GeneralUtils.isNotEmpty(this.data)) {
            IdPack idPack = JsonUtils.parseBean(data, IdPack.class);
            if (GeneralUtils.isNotEmpty(idPack) && GeneralUtils.isNotEmpty(idPack.getId())) {
                return idPack.getId();
            }
        }
        return null;
    }

    public Set<String> toDataIds() {
        if (GeneralUtils.isNotEmpty(this.data)) {
            List<IdPack> idPacks = JsonUtils.parseList(data, IdPack.class);
            if (GeneralUtils.isNotEmpty(idPacks) ) {
                return idPacks.stream().map(IdPack::getId).filter(Objects::nonNull).collect(Collectors.toSet());
            }
        }
        return null;
    }

    public UserInfoPack toDataUserInfo() {
        if (GeneralUtils.isNotEmpty(this.data)) {
            return JsonUtils.parseBean(this.data, UserInfoPack.class);
        }
        return null;
    }

    public UserInfoPack toDataUserLogin() {
        if (GeneralUtils.isNotEmpty(this.data)) {
            UserLoginPack userLoginPack = JsonUtils.parseBean(this.data, UserLoginPack.class);
            if (GeneralUtils.isNotEmpty(userLoginPack)) {
                return userLoginPack.getUser();
            }
        }
        return null;
    }
}
