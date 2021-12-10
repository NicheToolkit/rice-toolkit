package io.github.nichetoolkit.rice.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>RiceBeanNameProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.bean.name")
public class RiceBeanNameProperties {
    private Boolean uniqueEnabled = true;
    private Boolean nonnullEnabled = true;

    public RiceBeanNameProperties() {
    }

    public Boolean getUniqueEnabled() {
        return uniqueEnabled;
    }

    public void setUniqueEnabled(Boolean uniqueEnabled) {
        this.uniqueEnabled = uniqueEnabled;
    }

    public Boolean getNonnullEnabled() {
        return nonnullEnabled;
    }

    public void setNonnullEnabled(Boolean nonnullEnabled) {
        this.nonnullEnabled = nonnullEnabled;
    }

}
