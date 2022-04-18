package io.github.nichetoolkit.rice.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>RiceBeanKeyProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.bean.key")
public class RiceBeanKeyProperties {
    private Boolean invadeEnabled = false;

    public RiceBeanKeyProperties() {
    }

    public Boolean getInvadeEnabled() {
        return invadeEnabled;
    }

    public void setInvadeEnabled(Boolean invadeEnabled) {
        this.invadeEnabled = invadeEnabled;
    }
}
