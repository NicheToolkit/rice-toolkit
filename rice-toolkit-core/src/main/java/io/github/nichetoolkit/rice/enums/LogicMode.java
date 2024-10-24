package io.github.nichetoolkit.rice.enums;

import io.github.nichetoolkit.rest.util.GeneralUtils;

public enum LogicMode {
    CONFIG,
    AUTO,
    ;

    public static boolean isPresentOfMarkType(ConfigMark configMark, Object valueOfLogic) {
        return GeneralUtils.isNotEmpty(valueOfLogic) && configMark.getKey().isAssignableFrom(valueOfLogic.getClass());
    }

    public static Object valueOfConfigMark(ConfigMark configMark, Object valueOfMark) {
        if (isPresentOfMarkType(configMark,valueOfMark)) {
            return valueOfMark;
        }
        Object markValue;
        switch (configMark) {
            case STRING:
                markValue = ConfigMark.STRING_MARK;
                break;
            case BOOLEAN:
                markValue = ConfigMark.BOOLEAN_MARK;
                break;
            case NUMBER:
            default:
                markValue = ConfigMark.NUMBER_MARK;
        }
        return markValue;
    }

    public static Object valueOfConfigUnmark(ConfigMark configMark, Object valueOfUnmark) {
        if (isPresentOfMarkType(configMark,valueOfUnmark)) {
            return valueOfUnmark;
        }
        Object unmarkValue;
        switch (configMark) {
            case STRING:
                unmarkValue = ConfigMark.STRING_UNMARK;
                break;
            case BOOLEAN:
                unmarkValue = ConfigMark.BOOLEAN_UNMARK;
                break;
            case NUMBER:
            default:
                unmarkValue = ConfigMark.NUMBER_UNMARK;
        }
        return unmarkValue;
    }
}
