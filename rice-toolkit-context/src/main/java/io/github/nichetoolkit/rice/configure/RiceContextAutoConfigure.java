package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

/**
 * <code>RiceContextAutoConfigure</code>
 * <p>The rice context auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk17
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RiceCoreAutoConfigure.class)
@ImportAutoConfiguration(RiceSerializeAutoConfigure.class)
public class RiceContextAutoConfigure {
    /**
     * <code>RiceContextAutoConfigure</code>
     * <p>Instantiates a new rice context auto configure.</p>
     */
    public RiceContextAutoConfigure() {
        log.debug("The auto configuration for [rice-context] initiated");
    }
}
