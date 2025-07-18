package io.github.nichetoolkit.rice.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "nichetoolkit.rice.serialize")
public class RiceSerializeProperties {
    private String bigDecimalFormat = "0.00";
}
