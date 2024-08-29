package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@Configuration
@MapperScan(basePackages = {"io.github.nichetoolkit.rice.mapper"})
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
@ImportAutoConfiguration(value = {DatasourceAutoConfigure.class})
public class RiceTestWebAutoConfigure {
    public RiceTestWebAutoConfigure() {
        log.debug("================= rice-toolkit-test-web initiated ！ ===================");
    }
}
