package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>RiceCoreAutoConfigure</code>
 * <p>The type rice core auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
public class RiceCoreAutoConfigure {
    /**
     * <code>RiceCoreAutoConfigure</code>
     * <p>Instantiates a new rice core auto configure.</p>
     */
    public RiceCoreAutoConfigure() {
        log.debug("The auto configuration for [rice-core] initiated");
    }
}
