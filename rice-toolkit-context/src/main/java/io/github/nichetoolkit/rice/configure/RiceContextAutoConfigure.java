package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
public class RiceContextAutoConfigure {
    public RiceContextAutoConfigure() {
        log.debug("================= rice-context-auto-config initiated ！ ===================");
    }
}
