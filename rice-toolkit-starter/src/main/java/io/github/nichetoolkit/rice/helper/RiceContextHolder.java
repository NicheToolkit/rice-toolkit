package io.github.nichetoolkit.rice.helper;

import io.github.nichetoolkit.rice.configure.RiceBeanProperties;
import io.github.nichetoolkit.rice.configure.RiceLoginProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RiceContextHolder implements InitializingBean {

    private static RiceContextHolder INSTANCE = null;

    public static RiceContextHolder getInstance() {
        return INSTANCE;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RiceBeanProperties beanProperties;

    @Autowired
    private RiceLoginProperties loginProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        INSTANCE = this;
    }

    public static ApplicationContext applicationContext() {
        return INSTANCE.applicationContext;
    }

    public static RiceBeanProperties beanProperties() {
        return INSTANCE.beanProperties;
    }

    public static RiceLoginProperties loginProperties() {
        return INSTANCE.loginProperties;
    }
}
