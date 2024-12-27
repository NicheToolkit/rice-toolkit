package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.configure.RiceServiceProperties;
import io.github.nichetoolkit.rice.enums.AutoMark;
import io.github.nichetoolkit.rice.enums.ConfigMark;
import io.github.nichetoolkit.rice.enums.LogicMode;

/**
 * <code>DefaultLogicMark</code>
 * <p>The default logic mark class.</p>
 * @see  io.github.nichetoolkit.rice.RestLogicMark
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public abstract class DefaultLogicMark implements RestLogicMark {
    /**
     * <code>logicMode</code>
     * {@link io.github.nichetoolkit.rice.enums.LogicMode} <p>The <code>logicMode</code> field.</p>
     * @see  io.github.nichetoolkit.rice.enums.LogicMode
     */
    protected final LogicMode logicMode;
    /**
     * <code>configMark</code>
     * {@link io.github.nichetoolkit.rice.enums.ConfigMark} <p>The <code>configMark</code> field.</p>
     * @see  io.github.nichetoolkit.rice.enums.ConfigMark
     */
    protected final ConfigMark configMark;
    /**
     * <code>autoMark</code>
     * {@link io.github.nichetoolkit.rice.enums.AutoMark} <p>The <code>autoMark</code> field.</p>
     * @see  io.github.nichetoolkit.rice.enums.AutoMark
     */
    protected final AutoMark autoMark;
    /**
     * <code>valueOfMark</code>
     * {@link java.lang.Object} <p>The <code>valueOfMark</code> field.</p>
     * @see  java.lang.Object
     */
    protected final Object valueOfMark;
    /**
     * <code>valueOfUnmark</code>
     * {@link java.lang.Object} <p>The <code>valueOfUnmark</code> field.</p>
     * @see  java.lang.Object
     */
    protected final Object valueOfUnmark;

    /**
     * <code>DefaultLogicMark</code>
     * <p>Instantiates a new default logic mark.</p>
     * @param serviceProperties {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The service properties parameter is <code>RiceServiceProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rice.configure.RiceServiceProperties
     */
    public DefaultLogicMark(RiceServiceProperties serviceProperties) {
        this.logicMode = serviceProperties.getLogicMode();
        this.configMark = serviceProperties.getConfigMark();
        this.autoMark = serviceProperties.getAutoMark();
        this.valueOfMark = serviceProperties.markOfConfig();
        this.valueOfUnmark = serviceProperties.unmarkOfConfig();
    }

    /**
     * <code>getAutoMark</code>
     * <p>The get auto mark getter method.</p>
     * @return  {@link java.lang.Object} <p>The get auto mark return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    abstract public Object getAutoMark() throws RestException;

    /**
     * <code>getAutoUnmark</code>
     * <p>The get auto unmark getter method.</p>
     * @return  {@link java.lang.Object} <p>The get auto unmark return object is <code>Object</code> type.</p>
     * @see  java.lang.Object
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    abstract public Object getAutoUnmark() throws RestException;

    @Override
    public Object getLogicMark() throws RestException {
        if (this.logicMode == LogicMode.CONFIG) {
            return LogicMode.valueOfConfigMark(this.configMark, this.valueOfMark);
        } else {
            Object resolveLogic = DefaultLogicResolver.resolveLogic(this.autoMark);
            if (GeneralUtils.isNotEmpty(resolveLogic)) {
                return resolveLogic;
            } else {
                return getAutoMark();
            }
        }
    }

    @Override
    public Object getLogicUnmark() throws RestException {
        if (this.logicMode == LogicMode.CONFIG) {
            return LogicMode.valueOfConfigUnmark(this.configMark, this.valueOfUnmark);
        } else {
            return getAutoUnmark();
        }
    }
}
