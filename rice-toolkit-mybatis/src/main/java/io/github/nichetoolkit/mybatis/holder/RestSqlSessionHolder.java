package io.github.nichetoolkit.mybatis.holder;

import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.error.lack.ConfigureLackError;
import io.github.nichetoolkit.rest.fitter.RestFulfilledFitter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.annotation.Resource;

/**
 * <code>RestSqlSessionHolder</code>
 * <p>The rest sql session holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.fitter.RestFulfilledFitter
 * @see lombok.extern.slf4j.Slf4j
 * @see lombok.Setter
 * @since Jdk1.8
 */
@Slf4j
@Setter
public class RestSqlSessionHolder implements RestFulfilledFitter<RestSqlSessionHolder> {

    /**
     * <code>sqlSessionFactory</code>
     * {@link org.apache.ibatis.session.SqlSessionFactory} <p>The <code>sqlSessionFactory</code> field.</p>
     * @see org.apache.ibatis.session.SqlSessionFactory
     * @see javax.annotation.Resource
     */
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.mybatis.holder.RestSqlSessionHolder} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static RestSqlSessionHolder INSTANCE = null;

    /**
     * <code>instance</code>
     * <p>The instance method.</p>
     * @return {@link io.github.nichetoolkit.mybatis.holder.RestSqlSessionHolder} <p>The instance return object is <code>RestSqlSessionHolder</code> type.</p>
     */
    public static RestSqlSessionHolder instance() {
        return RestOptional.ofNullable(INSTANCE).orNullThrow(ConfigureLackError::new);
    }

    @Override
    public void afterAutowirePropertiesSet() {
        INSTANCE = this;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    /**
     * <code>sqlSessionFactory</code>
     * <p>The sql session factory method.</p>
     * @return {@link org.apache.ibatis.session.SqlSessionFactory} <p>The sql session factory return object is <code>SqlSessionFactory</code> type.</p>
     * @see org.apache.ibatis.session.SqlSessionFactory
     */
    public static SqlSessionFactory sqlSessionFactory() {
        return instance().sqlSessionFactory;
    }

    /**
     * <code>configuration</code>
     * <p>The configuration method.</p>
     * @return {@link org.apache.ibatis.session.Configuration} <p>The configuration return object is <code>Configuration</code> type.</p>
     * @see org.apache.ibatis.session.Configuration
     */
    public static Configuration configuration() {
        return instance().sqlSessionFactory.getConfiguration();
    }

    /**
     * <code>typeAliasRegistry</code>
     * <p>The type alias registry method.</p>
     * @return {@link org.apache.ibatis.type.TypeAliasRegistry} <p>The type alias registry return object is <code>TypeAliasRegistry</code> type.</p>
     * @see org.apache.ibatis.type.TypeAliasRegistry
     */
    public static TypeAliasRegistry typeAliasRegistry() {
        return instance().sqlSessionFactory.getConfiguration().getTypeAliasRegistry();
    }

    /**
     * <code>typeHandlerRegistry</code>
     * <p>The type handler registry method.</p>
     * @return {@link org.apache.ibatis.type.TypeHandlerRegistry} <p>The type handler registry return object is <code>TypeHandlerRegistry</code> type.</p>
     * @see org.apache.ibatis.type.TypeHandlerRegistry
     */
    public static TypeHandlerRegistry typeHandlerRegistry() {
        return instance().sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
    }

}
