package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>RiceExampleAutoConfigure</code>
 * <p>The rice example auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.mybatis.spring.annotation.MapperScan
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"io.github.nichetoolkit.rice.mapper"})
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
@ImportAutoConfiguration(value = {DatasourceAutoConfigure.class, RedisTemplateAutoConfigure.class})
public class RiceExampleAutoConfigure {
    /**
     * <code>RiceExampleAutoConfigure</code>
     * <p>Instantiates a new rice example auto configure.</p>
     */
    public RiceExampleAutoConfigure() {
        log.debug("The auto configuration for [rice-example] initiated");
    }
}
