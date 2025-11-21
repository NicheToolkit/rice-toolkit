package io.github.nichetoolkit.rice.configure;

import io.github.nichetoolkit.mybatis.enums.StyleType;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestI18nResources;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.DefaultColumnResolver;
import io.github.nichetoolkit.rice.advice.LoginAdvice;
import io.github.nichetoolkit.rice.constant.AdviceConstants;
import io.github.nichetoolkit.rice.interceptor.DefaultResponseInterceptor;
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
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * <code>controllerAdvice</code>
     * <p>The controller advice method.</p>
     * @param loginAdvices {@link java.util.Optional} <p>The login advices parameter is <code>Optional</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.interceptor.DefaultResponseInterceptor} <p>The controller advice return object is <code>DefaultResponseInterceptor</code> type.</p>
     * @see java.util.Optional
     * @see io.github.nichetoolkit.rice.interceptor.DefaultResponseInterceptor
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(AdviceConstants.RESPONSE_ORDER)
    @ConditionalOnMissingBean(DefaultResponseInterceptor.class)
    public DefaultResponseInterceptor controllerAdvice(Optional<List<LoginAdvice>> loginAdvices) {
        return new DefaultResponseInterceptor(loginAdvices.orElse(new ArrayList<>()));
    }


    /**
     * <code>riceI18nResources</code>
     * <p>The rice i 18 n resources method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestI18nResources} <p>The rice i 18 n resources return object is <code>RestI18nResources</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestI18nResources
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public RestI18nResources riceI18nResources() {
        return RestI18nResources.of(RICE_I18N);
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
