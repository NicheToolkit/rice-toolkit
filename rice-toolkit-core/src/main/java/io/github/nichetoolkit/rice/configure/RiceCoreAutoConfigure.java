package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.mybatis.configure.RiceMybatisAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

/**
 * <code>RiceCoreAutoConfigure</code>
 * <p>The rice core auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @since Jdk17
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RiceMybatisAutoConfigure.class)
public class RiceCoreAutoConfigure {
    /**
     * <code>RiceCoreAutoConfigure</code>
     * <p>Instantiates a new rice core auto configure.</p>
     */
    public RiceCoreAutoConfigure() {
        log.debug("The auto configuration for [rice-core] initiated");
    }
}
