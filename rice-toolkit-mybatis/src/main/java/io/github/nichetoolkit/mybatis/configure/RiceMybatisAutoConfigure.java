package io.github.nichetoolkit.mybatis.configure;

import io.github.nichetoolkit.rest.configure.RestUtilsAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

/**
 * <code>RiceMybatisAutoConfigure</code>
 * <p>The rice mybatis auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RestUtilsAutoConfigure.class)
public class RiceMybatisAutoConfigure {
    /**
     * <code>RiceMybatisAutoConfigure</code>
     * <p>Instantiates a new rice mybatis auto configure.</p>
     */
    public RiceMybatisAutoConfigure() {
        log.debug("The auto configuration for [rice-mybatis] initiated");
    }
}
