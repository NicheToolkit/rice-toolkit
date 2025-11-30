package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.DefaultLogicMark;
import io.github.nichetoolkit.rice.configure.RiceServiceProperties;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * <code>DefaultAutoLogicMark</code>
 * <p>The default auto logic mark class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultLogicMark
 * @see lombok.experimental.SuperBuilder
 * @since Jdk17
 */
@SuperBuilder
public class DefaultAutoLogicMark extends DefaultLogicMark {

    /**
     * <code>DefaultAutoLogicMark</code>
     * <p>Instantiates a new default auto logic mark.</p>
     * @param serviceProperties {@link io.github.nichetoolkit.rice.configure.RiceServiceProperties} <p>The service properties parameter is <code>RiceServiceProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceServiceProperties
     */
    public DefaultAutoLogicMark(RiceServiceProperties serviceProperties) {
        super(serviceProperties);
    }

    @Override
    public Object getAutoMark() throws RestException {
        Object autoMark;
        switch (this.autoMark) {
            case DATETIME:
                autoMark = new Date();
                break;
            case VERSION:
                autoMark = IdentityUtils.valueOfLong();
                break;
            case IDENTITY:
            default:
                autoMark = IdentityUtils.valueOfString();
                break;
        }
        return autoMark;
    }

    @Override
    public Object getAutoUnmark() throws RestException {
        return null;
    }
}
