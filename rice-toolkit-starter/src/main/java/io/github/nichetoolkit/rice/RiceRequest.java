package io.github.nichetoolkit.rice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.nichetoolkit.rest.userlog.RestRequest;
import io.github.nichetoolkit.rest.util.BeanUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rice.filter.IdFilter;
import io.github.nichetoolkit.rice.pack.IdPack;
import io.github.nichetoolkit.rice.pack.IdsPack;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceRequest extends RestRequest<RiceRequest> {
    public RiceRequest() {
    }

    public RiceRequest(RestRequest request) {
        BeanUtils.copyNonullProperties(request,this);
    }

    public String toUrlId() {
        if (GeneralUtils.isNotEmpty(this.url)) {
            String[] splitUrl = this.url.trim().split("/");
            if (GeneralUtils.isNotEmpty(splitUrl) && GeneralUtils.isNotEmpty(splitUrl[splitUrl.length-1])) {
                return splitUrl[splitUrl.length - 1];
            }
        }
        return null;
    }

    public String toParamId() {
        if (GeneralUtils.isNotEmpty(this.params)) {
            IdPack idPack = JsonUtils.parseBean(this.params, IdPack.class);
            if (GeneralUtils.isNotEmpty(idPack) && GeneralUtils.isNotEmpty(idPack.getId())) {
                return idPack.getId();
            }
        }
        return null;
    }

    public Set<String> toParamIds() {
        if (GeneralUtils.isNotEmpty(this.params)) {
            IdsPack idsPack = JsonUtils.parseBean(this.params, IdsPack.class);
            if (GeneralUtils.isNotEmpty(idsPack) && GeneralUtils.isNotEmpty(idsPack.getIds())) {
                String packIds = idsPack.getIds().trim();
                if (!packIds.startsWith("[")) {
                    packIds = "[".concat(packIds);
                }
                if (packIds.endsWith(",") ){
                    packIds = packIds.substring(0, packIds.length() - 1);
                }
                if (!packIds.endsWith("]")) {
                    packIds = packIds.concat("]");
                }
                List<String> idList = JsonUtils.parseList(packIds, String.class);
                return new HashSet<>(idList);
            }
        }
        return null;
    }

    public Set<String> toListIds() {
        if (GeneralUtils.isNotEmpty(this.body)) {
            List<String> idList = JsonUtils.parseBean(this.body, new TypeReference<List<String>>() {});
            if (GeneralUtils.isNotEmpty(idList)) {
                return new HashSet<>(idList);
            }
        }
        return null;
    }


    public Set<String> toFilterIds() {
        if (GeneralUtils.isNotEmpty(this.body)) {
            IdFilter<String,String> idFilter = JsonUtils.parseBean(this.body, new TypeReference<IdFilter<String,String>>() {});
            if (GeneralUtils.isNotEmpty(idFilter) && GeneralUtils.isNotEmpty(idFilter.toIds())) {
                return new HashSet<>(idFilter.toIds());
            }
        }
        return null;
    }
}
