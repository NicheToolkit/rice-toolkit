package io.github.nichetoolkit.rice.configure;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <code>DatasourceAutoConfigure</code>
 * <p>The datasource auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.transaction.annotation.EnableTransactionManagement
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class DatasourceAutoConfigure {
    /**
     * <code>DatasourceAutoConfigure</code>
     * <p>Instantiates a new datasource auto configure.</p>
     */
    public DatasourceAutoConfigure() {
        log.debug("The auto configuration for [datasource] initiated");
    }

    /**
     * <code>dataSource</code>
     * <p>The data source method.</p>
     * @return {@link com.zaxxer.hikari.HikariDataSource} <p>The data source return object is <code>HikariDataSource</code> type.</p>
     * @see com.zaxxer.hikari.HikariDataSource
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.context.properties.ConfigurationProperties
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Primary
    @Bean(name = "hikariDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @ConditionalOnMissingBean(HikariDataSource.class)
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * <code>sqlSessionFactory</code>
     * <p>The sql session factory method.</p>
     * @param dataSource {@link javax.sql.DataSource} <p>The data source parameter is <code>DataSource</code> type.</p>
     * @return {@link org.apache.ibatis.session.SqlSessionFactory} <p>The sql session factory return object is <code>SqlSessionFactory</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     * @see javax.sql.DataSource
     * @see org.springframework.beans.factory.annotation.Qualifier
     * @see org.apache.ibatis.session.SqlSessionFactory
     * @see org.springframework.context.annotation.Bean
     * @see java.lang.Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("hikariDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*/*.xml"));
        return bean.getObject();
    }

    /**
     * <code>transactionManager</code>
     * <p>The transaction manager method.</p>
     * @param dataSource {@link javax.sql.DataSource} <p>The data source parameter is <code>DataSource</code> type.</p>
     * @return {@link org.springframework.jdbc.datasource.DataSourceTransactionManager} <p>The transaction manager return object is <code>DataSourceTransactionManager</code> type.</p>
     * @see javax.sql.DataSource
     * @see org.springframework.beans.factory.annotation.Qualifier
     * @see org.springframework.jdbc.datasource.DataSourceTransactionManager
     * @see org.springframework.context.annotation.Bean
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("hikariDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * <code>sqlSessionTemplate</code>
     * <p>The sql session template method.</p>
     * @param sqlSessionFactory {@link org.apache.ibatis.session.SqlSessionFactory} <p>The sql session factory parameter is <code>SqlSessionFactory</code> type.</p>
     * @return {@link org.mybatis.spring.SqlSessionTemplate} <p>The sql session template return object is <code>SqlSessionTemplate</code> type.</p>
     * @see org.apache.ibatis.session.SqlSessionFactory
     * @see org.springframework.beans.factory.annotation.Qualifier
     * @see org.mybatis.spring.SqlSessionTemplate
     * @see org.springframework.context.annotation.Bean
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
