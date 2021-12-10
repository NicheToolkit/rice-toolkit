package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * <p>RiceMybatisStarterAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"io.github.nichetoolkit.rice.mapper"})
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
@ImportAutoConfiguration(value = {RiceMybatisDatasourceAutoConfigure.class})
public class RiceMybatisStarterAutoConfigure {
    public RiceMybatisStarterAutoConfigure() {
        log.debug("================= rice-mybatis-toolkit-starter-auto-configure initiated ！ ===================");
    }
}
