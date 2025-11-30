package io.github.nichetoolkit.pagehelper;

import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * <code>PageHelperAutoConfigure</code>
 * <p>The page helper auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnBean
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.context.annotation.Lazy
 * @since Jdk17
 */
@Slf4j
@AutoConfiguration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties({PageHelperProperties.class})
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@Lazy(false)
public class PageHelperAutoConfigure implements InitializingBean {

    /**
     * <code>sqlSessionFactoryList</code>
     * {@link java.util.List} <p>The <code>sqlSessionFactoryList</code> field.</p>
     * @see java.util.List
     */
    private final List<SqlSessionFactory> sqlSessionFactoryList;

    /**
     * <code>properties</code>
     * {@link io.github.nichetoolkit.pagehelper.PageHelperProperties} <p>The <code>properties</code> field.</p>
     * @see io.github.nichetoolkit.pagehelper.PageHelperProperties
     */
    private final PageHelperProperties properties;

    /**
     * <code>PageHelperAutoConfigure</code>
     * <p>Instantiates a new page helper auto configure.</p>
     * @param sqlSessionFactoryList {@link java.util.List} <p>The sql session factory list parameter is <code>List</code> type.</p>
     * @param properties            {@link io.github.nichetoolkit.pagehelper.PageHelperProperties} <p>The properties parameter is <code>PageHelperProperties</code> type.</p>
     * @see java.util.List
     * @see io.github.nichetoolkit.pagehelper.PageHelperProperties
     */
    public PageHelperAutoConfigure(List<SqlSessionFactory> sqlSessionFactoryList, PageHelperProperties properties) {
        this.sqlSessionFactoryList = sqlSessionFactoryList;
        this.properties = properties;
        log.debug("The auto configuration for [rice-pagehelper] initiated");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PageInterceptor interceptor = new PageInterceptor();
        interceptor.setProperties(this.properties.getProperties());
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            if (!containsInterceptor(configuration, interceptor)) {
                configuration.addInterceptor(interceptor);
            }
        }
    }

    /**
     * <code>containsInterceptor</code>
     * <p>The contains interceptor method.</p>
     * @param configuration {@link org.apache.ibatis.session.Configuration} <p>The configuration parameter is <code>Configuration</code> type.</p>
     * @param interceptor   {@link org.apache.ibatis.plugin.Interceptor} <p>The interceptor parameter is <code>Interceptor</code> type.</p>
     * @return boolean <p>The contains interceptor return object is <code>boolean</code> type.</p>
     * @see org.apache.ibatis.session.Configuration
     * @see org.apache.ibatis.plugin.Interceptor
     */
    private boolean containsInterceptor(Configuration configuration, Interceptor interceptor) {
        try {
            return configuration.getInterceptors().stream().anyMatch(config->interceptor.getClass().isAssignableFrom(config.getClass()));
        } catch (Exception e) {
            return false;
        }
    }
}
