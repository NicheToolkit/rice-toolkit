package io.github.nichetoolkit.rice.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RiceSerializeProperties</code>
 * <p>The rice serialize properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Setter
 * @see lombok.Getter
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rice.serialize")
public class RiceSerializeProperties {
   /**
    * <code>bigDecimalFormat</code>
    * {@link java.lang.String} <p>The <code>bigDecimalFormat</code> field.</p>
    * @see java.lang.String
    */
   private String bigDecimalFormat = "0.00";
}
