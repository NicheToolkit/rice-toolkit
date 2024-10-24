package io.github.nichetoolkit.rice.defaults;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rice.DefaultLogicMark;
import io.github.nichetoolkit.rice.configure.RiceServiceProperties;

import java.util.Date;

public class DefaultAutoLogicMark extends DefaultLogicMark {

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
