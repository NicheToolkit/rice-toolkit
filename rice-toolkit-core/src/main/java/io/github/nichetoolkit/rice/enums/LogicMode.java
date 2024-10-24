package io.github.nichetoolkit.rice.enums;

import io.github.nichetoolkit.rest.util.GeneralUtils;

/**
 * <code>LogicMode</code>
 * <p>The logic mode enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum LogicMode {
    /**
     * <code>CONFIG</code>
     * <p>The config logic mode field.</p>
     */
    CONFIG,
    /**
     * <code>AUTO</code>
     * <p>The auto logic mode field.</p>
     */
    AUTO,
    ;

    /**
     * <code>isPresentOfMarkType</code>
     * <p>The is present of mark type method.</p>
     * @param configMark   {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The config mark parameter is <code>ConfigMark</code> type.</p>
     * @param valueOfLogic {@link java.lang.Object} <p>The value of logic parameter is <code>Object</code> type.</p>
     * @return boolean <p>The is present of mark type return object is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.ConfigMark
     * @see java.lang.Object
     */
    public static boolean isPresentOfMarkType(ConfigMark configMark, Object valueOfLogic) {
        return GeneralUtils.isNotEmpty(valueOfLogic) && configMark.getKey().isAssignableFrom(valueOfLogic.getClass());
    }

    /**
     * <code>valueOfConfigMark</code>
     * <p>The value of config mark method.</p>
     * @param configMark  {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The config mark parameter is <code>ConfigMark</code> type.</p>
     * @param valueOfMark {@link java.lang.Object} <p>The value of mark parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>The value of config mark return object is <code>Object</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.ConfigMark
     * @see java.lang.Object
     */
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

    /**
     * <code>valueOfConfigUnmark</code>
     * <p>The value of config unmark method.</p>
     * @param configMark    {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The config mark parameter is <code>ConfigMark</code> type.</p>
     * @param valueOfUnmark {@link java.lang.Object} <p>The value of unmark parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Object} <p>The value of config unmark return object is <code>Object</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.ConfigMark
     * @see java.lang.Object
     */
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
