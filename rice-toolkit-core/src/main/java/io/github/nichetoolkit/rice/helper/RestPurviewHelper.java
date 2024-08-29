package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.stereotype.purview.RestModule;
import io.github.nichetoolkit.rice.stereotype.purview.RestPurview;
import io.github.nichetoolkit.rice.stereotype.purview.RestRole;
import io.github.nichetoolkit.rice.stereotype.purview.RestWidget;
import io.github.nichetoolkit.rice.stereotype.value.RestModuleValue;
import io.github.nichetoolkit.rice.stereotype.value.RestPurviewValue;
import io.github.nichetoolkit.rice.stereotype.value.RestRoleValue;
import io.github.nichetoolkit.rice.stereotype.value.RestWidgetValue;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        } else if (GeneralUtils.isNotEmpty(values)) {
            moduleValues = Arrays.stream(values).map(RestModuleValue::module).distinct().collect(Collectors.toList());
        } else {
            moduleValues = Arrays.asList(modules);
        }
        return moduleValues;
    }

    public static List<Long> purviews(RestPurview restPurview) {
        List<Long> purviewValues = new ArrayList<>();
        Long value = restPurview.value();
        Long module = restPurview.purview();
        RestPurviewValue[] values = restPurview.values();
        long[] modules = restPurview.purviews();
        if (GeneralUtils.isEmpty(value)) {
            purviewValues.add(value);
        } else if (GeneralUtils.isEmpty(module)) {
            purviewValues.add(module);
        } else if (GeneralUtils.isNotEmpty(values)) {
            purviewValues = Arrays.stream(values).map(RestPurviewValue::purview).distinct().collect(Collectors.toList());
        } else {
            purviewValues = Arrays.asList(ArrayUtils.toObject(modules));
        }
        return purviewValues;
    }

    public static List<String> widgets(RestWidget restWidget) {
        List<String> widgetValues = new ArrayList<>();
        String value = restWidget.value();
        String widget = restWidget.widget();
        RestWidgetValue[] values = restWidget.values();
        String[] widgets = restWidget.widgets();
        if (GeneralUtils.isEmpty(value)) {
            widgetValues.add(value);
        } else if (GeneralUtils.isEmpty(widget)) {
            widgetValues.add(widget);
        } else if (GeneralUtils.isNotEmpty(values)) {
            widgetValues = Arrays.stream(values).map(RestWidgetValue::widget).distinct().collect(Collectors.toList());
        } else {
            widgetValues = Arrays.asList(widgets);
        }
        return widgetValues;
    }

    public static List<Long> roles(RestRole restRole) {
        List<Long> roleValues = new ArrayList<>();
        Long value = restRole.value();
        Long role = restRole.role();
        RestRoleValue[] values = restRole.values();
        long[] roles = restRole.roles();
        if (GeneralUtils.isEmpty(value)) {
            roleValues.add(value);
        } else if (GeneralUtils.isEmpty(role)) {
            roleValues.add(role);
        } else if (GeneralUtils.isNotEmpty(values)) {
            roleValues = Arrays.stream(values).map(RestRoleValue::role).distinct().collect(Collectors.toList());
        } else {
            roleValues = Arrays.asList(ArrayUtils.toObject(roles));
        }
        return roleValues;
    }
}
