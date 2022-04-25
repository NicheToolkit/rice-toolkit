package io.github.nichetoolkit.rice.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>RiceStarterAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rice"})
public class RiceSuperStarterAutoConfigure {
    public RiceSuperStarterAutoConfigure() {
        log.debug("================= rice-supper-toolkit-starter initiated ！ ===================");
    }
}
