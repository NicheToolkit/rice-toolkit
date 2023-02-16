package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.purview.RestModule;
import io.github.nichetoolkit.rice.stereotype.purview.RestPurview;
import io.github.nichetoolkit.rice.stereotype.purview.RestRole;
import io.github.nichetoolkit.rice.stereotype.value.RestModuleValue;
import io.github.nichetoolkit.rice.stereotype.value.RestPurviewValue;
import io.github.nichetoolkit.rice.stereotype.value.RestRoleValue;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>RestPurviewHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestPurviewHelper {

    public static List<String> modules(RestModule restModule) {
        List<String> moduleValues = new ArrayList<>();
        String value = restModule.value();
        String module = restModule.module();
        RestModuleValue[] values = restModule.values();
        String[] modules = restModule.modules();
        if (GeneralUtils.isEmpty(value)) {
            moduleValues.add(value);
        } else if (GeneralUtils.isEmpty(module)) {
            moduleValues.add(module);
        } else if (GeneralUtils.isNotEmpty(modules)) {
            moduleValues = Arrays.stream(values).map(RestModuleValue::module).distinct().collect(Collectors.toList());
        } else {
            moduleValues = Arrays.asList(modules);
        }
        return moduleValues;
    }

    public static List<Long> purviews(RestPurview restPurview) {
        List<Long> moduleValues = new ArrayList<>();
        Long value = restPurview.value();
        Long module = restPurview.purview();
        RestPurviewValue[] values = restPurview.values();
        long[] modules = restPurview.purviews();
        if (GeneralUtils.isEmpty(value)) {
            moduleValues.add(value);
        } else if (GeneralUtils.isEmpty(module)) {
            moduleValues.add(module);
        } else if (GeneralUtils.isNotEmpty(values)) {
            moduleValues = Arrays.stream(values).map(RestPurviewValue::purview).distinct().collect(Collectors.toList());
        } else {
            moduleValues = Arrays.asList(ArrayUtils.toObject(modules));
        }
        return moduleValues;
    }

    public static List<Long> roles(RestRole restRole) {
        List<Long> moduleValues = new ArrayList<>();
        Long value = restRole.value();
        Long role = restRole.role();
        RestRoleValue[] values = restRole.values();
        long[] roles = restRole.roles();
        if (GeneralUtils.isEmpty(value)) {
            moduleValues.add(value);
        } else if (GeneralUtils.isEmpty(role)) {
            moduleValues.add(role);
        } else if (GeneralUtils.isNotEmpty(values)) {
            moduleValues = Arrays.stream(values).map(RestRoleValue::role).distinct().collect(Collectors.toList());
        } else {
            moduleValues = Arrays.asList(ArrayUtils.toObject(roles));
        }
        return moduleValues;
    }
}
