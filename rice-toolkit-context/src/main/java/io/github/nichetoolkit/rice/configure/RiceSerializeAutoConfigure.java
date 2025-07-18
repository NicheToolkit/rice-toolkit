package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.rice.serialize.BigDecimalSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

/**
 * <code>RiceSerializeAutoConfigure</code>
 * <p>The rice serialize auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(RiceSerializeProperties.class)
public class RiceSerializeAutoConfigure {

    /**
     * <code>serializeProperties</code>
     * {@link io.github.nichetoolkit.rice.configure.RiceSerializeProperties} <p>The <code>serializeProperties</code> field.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceSerializeProperties
     */
    private final RiceSerializeProperties serializeProperties;

    /**
     * <code>RiceSerializeAutoConfigure</code>
     * <p>Instantiates a new rice serialize auto configure.</p>
     * @param serializeProperties {@link io.github.nichetoolkit.rice.configure.RiceSerializeProperties} <p>The serialize properties parameter is <code>RiceSerializeProperties</code> type.</p>
     * @see io.github.nichetoolkit.rice.configure.RiceSerializeProperties
     */
    public RiceSerializeAutoConfigure(RiceSerializeProperties serializeProperties) {
        this.serializeProperties = serializeProperties;
        log.debug("The auto configuration for [rice-serialize] initiated");
    }

    /**
     * <code>bigDecimalSerializer</code>
     * <p>The big decimal serializer method.</p>
     * @return {@link io.github.nichetoolkit.rice.serialize.BigDecimalSerializer} <p>The big decimal serializer return object is <code>BigDecimalSerializer</code> type.</p>
     * @see io.github.nichetoolkit.rice.serialize.BigDecimalSerializer
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(BigDecimalSerializer.class)
    public BigDecimalSerializer bigDecimalSerializer() {
        return new BigDecimalSerializer(this.serializeProperties);
    }
}
