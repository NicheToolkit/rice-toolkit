package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>RiceContextAutoConfigure</code>
 * <p>The type rice context auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
public class RiceContextAutoConfigure {
    /**
     * <code>RiceContextAutoConfigure</code>
     * Instantiates a new rice context auto configure.
     */
    public RiceContextAutoConfigure() {
        log.debug("================= rice-context-auto-config initiated ！ ===================");
    }
}
