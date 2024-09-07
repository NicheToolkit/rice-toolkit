package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>RiceTestWebAutoConfigure</code>
 * <p>The type rice test web auto configure class.</p>
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
@ImportAutoConfiguration(value = {DatasourceAutoConfigure.class, RedisAutoConfigure.class})
public class RiceTestWebAutoConfigure {
    /**
     * <code>RiceTestWebAutoConfigure</code>
     * Instantiates a new rice test web auto configure.
     */
    public RiceTestWebAutoConfigure() {
        log.debug("================= rice-toolkit-test-web initiated ！ ===================");
    }
}
