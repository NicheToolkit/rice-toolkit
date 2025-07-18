package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.mybatis.enums.StyleType;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestI18n;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.DefaultColumnResolver;
import io.github.nichetoolkit.rice.RestUserResolver;
import io.github.nichetoolkit.rice.resolver.RestColumnResolver;
import io.github.nichetoolkit.rice.resolver.RestIdResolver;
import io.github.nichetoolkit.rice.defaults.DefaultLongIdResolver;
import io.github.nichetoolkit.rice.defaults.DefaultStringIdResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

/**
 * <code>RiceStarterAutoConfigure</code>
 * <p>The rice starter auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RiceContextAutoConfigure.class)
@ImportAutoConfiguration(value = {RiceLoginAutoConfigure.class, RiceServiceAutoConfigure.class})
public class RiceStarterAutoConfigure {

    /**
     * <code>RICE_I18N</code>
     * {@link java.lang.String} <p>The constant <code>RICE_I18N</code> field.</p>
     * @see java.lang.String
     */
    private static final String RICE_I18N = "rice-i18n/messages";

    /**
     * <code>RiceStarterAutoConfigure</code>
     * <p>Instantiates a new rice starter auto configure.</p>
     */
    public RiceStarterAutoConfigure() {
        log.debug("The auto configuration for [rice-starter] initiated");
    }

    /**
     * <code>riceI18nBasename</code>
     * <p>The rice i 18 n basename method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestI18n} <p>The rice i 18 n basename return object is <code>RestI18n</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestI18n
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RestI18n riceI18nBasename() {
        return () -> Collections.singleton(RICE_I18N);
    }

    /**
     * <code>defaultStringIdResolver</code>
     * <p>The default string id resolver method.</p>
     * @return {@link io.github.nichetoolkit.rice.resolver.RestIdResolver} <p>The default string id resolver return object is <code>RestIdResolver</code> type.</p>
     * @see io.github.nichetoolkit.rice.resolver.RestIdResolver
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(DefaultStringIdResolver.class)
    public RestIdResolver<String> defaultStringIdResolver() {
        return DefaultStringIdResolver.DEFAULT_RESOLVER;
    }

    /**
     * <code>defaultLongIdResolver</code>
     * <p>The default long id resolver method.</p>
     * @return {@link io.github.nichetoolkit.rice.resolver.RestIdResolver} <p>The default long id resolver return object is <code>RestIdResolver</code> type.</p>
     * @see io.github.nichetoolkit.rice.resolver.RestIdResolver
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(DefaultLongIdResolver.class)
    public RestIdResolver<Long> defaultLongIdResolver() {
        return DefaultLongIdResolver.DEFAULT_RESOLVER;
    }

    /**
     * <code>defaultColumnResolver</code>
     * <p>The default column resolver method.</p>
     * @return {@link io.github.nichetoolkit.rice.resolver.RestColumnResolver} <p>The default column resolver return object is <code>RestColumnResolver</code> type.</p>
     * @see io.github.nichetoolkit.rice.resolver.RestColumnResolver
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(DefaultColumnResolver.class)
    public RestColumnResolver defaultColumnResolver() {
        return new RestColumnResolver() {
            @Override
            public StyleType support() {
                return StyleType.LOWER_UNDERLINE;
            }

            @Override
            public String resolve(String fieldName) throws RestException {
                return GeneralUtils.underline(fieldName);
            }
        };
    }

}
