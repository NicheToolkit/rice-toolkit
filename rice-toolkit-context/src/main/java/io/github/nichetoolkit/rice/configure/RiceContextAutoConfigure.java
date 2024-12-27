package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>RiceContextAutoConfigure</code>
 * <p>The rice context auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.context.annotation.Configuration
 * @see  org.springframework.context.annotation.ComponentScan
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
public class RiceContextAutoConfigure {
    /**
     * <code>RiceContextAutoConfigure</code>
     * <p>Instantiates a new rice context auto configure.</p>
     */
    public RiceContextAutoConfigure() {
        log.debug("The auto configuration for [rice-context] initiated");
    }
}
