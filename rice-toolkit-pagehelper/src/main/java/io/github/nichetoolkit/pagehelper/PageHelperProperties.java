package io.github.nichetoolkit.pagehelper;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;
import java.util.Properties;

/**
 * <code>PageHelperProperties</code>
 * <p>The page helper properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk17
 */
@Getter
@ConfigurationProperties(prefix = "nichetoolkit.pagehelper")
public class PageHelperProperties {
    /**
     * <code>properties</code>
     * {@link io.github.nichetoolkit.pagehelper.PageHelperProperties.CacheProperties} <p>The <code>properties</code> field.</p>
     * @see io.github.nichetoolkit.pagehelper.PageHelperProperties.CacheProperties
     */
    private final CacheProperties properties = new CacheProperties();
    /**
     * <code>offsetAsPageNum</code>
     * {@link java.lang.Boolean} <p>The <code>offsetAsPageNum</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean offsetAsPageNum;
    /**
     * <code>rowBoundsWithCount</code>
     * {@link java.lang.Boolean} <p>The <code>rowBoundsWithCount</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean rowBoundsWithCount;
    /**
     * <code>pageSizeZero</code>
     * {@link java.lang.Boolean} <p>The <code>pageSizeZero</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean pageSizeZero;
    /**
     * <code>reasonable</code>
     * {@link java.lang.Boolean} <p>The <code>reasonable</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean reasonable;
    /**
     * <code>supportMethodsArguments</code>
     * {@link java.lang.Boolean} <p>The <code>supportMethodsArguments</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean supportMethodsArguments;
    /**
     * <code>dialect</code>
     * {@link java.lang.String} <p>The <code>dialect</code> field.</p>
     * @see java.lang.String
     */
    private String dialect;
    /**
     * <code>helperDialect</code>
     * {@link java.lang.String} <p>The <code>helperDialect</code> field.</p>
     * @see java.lang.String
     */
    private String helperDialect;
    /**
     * <code>autoRuntimeDialect</code>
     * {@link java.lang.Boolean} <p>The <code>autoRuntimeDialect</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean autoRuntimeDialect;
    /**
     * <code>autoDialect</code>
     * {@link java.lang.Boolean} <p>The <code>autoDialect</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean autoDialect;
    /**
     * <code>closeConn</code>
     * {@link java.lang.Boolean} <p>The <code>closeConn</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean closeConn;
    /**
     * <code>params</code>
     * {@link java.lang.String} <p>The <code>params</code> field.</p>
     * @see java.lang.String
     */
    private String params;
    /**
     * <code>defaultCount</code>
     * {@link java.lang.Boolean} <p>The <code>defaultCount</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean defaultCount;
    /**
     * <code>dialectAlias</code>
     * {@link java.lang.String} <p>The <code>dialectAlias</code> field.</p>
     * @see java.lang.String
     */
    private String dialectAlias;
    /**
     * <code>autoDialectClass</code>
     * {@link java.lang.String} <p>The <code>autoDialectClass</code> field.</p>
     * @see java.lang.String
     */
    private String autoDialectClass;
    /**
     * <code>useSqlserver2012</code>
     * {@link java.lang.Boolean} <p>The <code>useSqlserver2012</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean useSqlserver2012;
    /**
     * <code>countColumn</code>
     * {@link java.lang.String} <p>The <code>countColumn</code> field.</p>
     * @see java.lang.String
     */
    private String countColumn;
    /**
     * <code>replaceSql</code>
     * {@link java.lang.String} <p>The <code>replaceSql</code> field.</p>
     * @see java.lang.String
     */
    private String replaceSql;
    /**
     * <code>sqlCacheClass</code>
     * {@link java.lang.String} <p>The <code>sqlCacheClass</code> field.</p>
     * @see java.lang.String
     */
    private String sqlCacheClass;
    /**
     * <code>boundSqlInterceptors</code>
     * {@link java.lang.String} <p>The <code>boundSqlInterceptors</code> field.</p>
     * @see java.lang.String
     */
    private String boundSqlInterceptors;
    /**
     * <code>keepOrderBy</code>
     * {@link java.lang.Boolean} <p>The <code>keepOrderBy</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean keepOrderBy;
    /**
     * <code>keepSubSelectOrderBy</code>
     * {@link java.lang.Boolean} <p>The <code>keepSubSelectOrderBy</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean keepSubSelectOrderBy;
    /**
     * <code>sqlParser</code>
     * {@link java.lang.String} <p>The <code>sqlParser</code> field.</p>
     * @see java.lang.String
     */
    private String sqlParser;
    /**
     * <code>asyncCount</code>
     * {@link java.lang.Boolean} <p>The <code>asyncCount</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean asyncCount;
    /**
     * <code>countSqlParser</code>
     * {@link java.lang.String} <p>The <code>countSqlParser</code> field.</p>
     * @see java.lang.String
     */
    private String countSqlParser;
    /**
     * <code>orderBySqlParser</code>
     * {@link java.lang.String} <p>The <code>orderBySqlParser</code> field.</p>
     * @see java.lang.String
     */
    private String orderBySqlParser;
    /**
     * <code>sqlServerSqlParser</code>
     * {@link java.lang.String} <p>The <code>sqlServerSqlParser</code> field.</p>
     * @see java.lang.String
     */
    private String sqlServerSqlParser;


    /**
     * <code>setOffsetAsPageNum</code>
     * <p>The set offset as page num setter method.</p>
     * @param offsetAsPageNum {@link java.lang.Boolean} <p>The offset as page num parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setOffsetAsPageNum(Boolean offsetAsPageNum) {
        this.offsetAsPageNum = offsetAsPageNum;
        Optional.ofNullable(offsetAsPageNum).ifPresent(properties::setOffsetAsPageNum);
    }


    /**
     * <code>setRowBoundsWithCount</code>
     * <p>The set row bounds with count setter method.</p>
     * @param rowBoundsWithCount {@link java.lang.Boolean} <p>The row bounds with count parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setRowBoundsWithCount(Boolean rowBoundsWithCount) {
        this.rowBoundsWithCount = rowBoundsWithCount;
        Optional.ofNullable(rowBoundsWithCount).ifPresent(properties::setRowBoundsWithCount);
    }


    /**
     * <code>setPageSizeZero</code>
     * <p>The set page size zero setter method.</p>
     * @param pageSizeZero {@link java.lang.Boolean} <p>The page size zero parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setPageSizeZero(Boolean pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
        Optional.ofNullable(pageSizeZero).ifPresent(properties::setPageSizeZero);
    }


    /**
     * <code>setReasonable</code>
     * <p>The set reasonable setter method.</p>
     * @param reasonable {@link java.lang.Boolean} <p>The reasonable parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
        Optional.ofNullable(reasonable).ifPresent(properties::setReasonable);
    }


    /**
     * <code>setSupportMethodsArguments</code>
     * <p>The set support methods arguments setter method.</p>
     * @param supportMethodsArguments {@link java.lang.Boolean} <p>The support methods arguments parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setSupportMethodsArguments(Boolean supportMethodsArguments) {
        this.supportMethodsArguments = supportMethodsArguments;
        Optional.ofNullable(supportMethodsArguments).ifPresent(properties::setSupportMethodsArguments);
    }


    /**
     * <code>setDialect</code>
     * <p>The set dialect setter method.</p>
     * @param dialect {@link java.lang.String} <p>The dialect parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setDialect(String dialect) {
        this.dialect = dialect;
        Optional.ofNullable(dialect).ifPresent(properties::setDialect);
    }


    /**
     * <code>setHelperDialect</code>
     * <p>The set helper dialect setter method.</p>
     * @param helperDialect {@link java.lang.String} <p>The helper dialect parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setHelperDialect(String helperDialect) {
        this.helperDialect = helperDialect;
        Optional.ofNullable(helperDialect).ifPresent(properties::setHelperDialect);
    }


    /**
     * <code>setAutoRuntimeDialect</code>
     * <p>The set auto runtime dialect setter method.</p>
     * @param autoRuntimeDialect {@link java.lang.Boolean} <p>The auto runtime dialect parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setAutoRuntimeDialect(Boolean autoRuntimeDialect) {
        this.autoRuntimeDialect = autoRuntimeDialect;
        Optional.ofNullable(autoRuntimeDialect).ifPresent(properties::setAutoRuntimeDialect);
    }


    /**
     * <code>setAutoDialect</code>
     * <p>The set auto dialect setter method.</p>
     * @param autoDialect {@link java.lang.Boolean} <p>The auto dialect parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setAutoDialect(Boolean autoDialect) {
        this.autoDialect = autoDialect;
        Optional.ofNullable(autoDialect).ifPresent(properties::setAutoDialect);
    }


    /**
     * <code>setCloseConn</code>
     * <p>The set close conn setter method.</p>
     * @param closeConn {@link java.lang.Boolean} <p>The close conn parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setCloseConn(Boolean closeConn) {
        this.closeConn = closeConn;
        Optional.ofNullable(closeConn).ifPresent(properties::setCloseConn);
    }


    /**
     * <code>setParams</code>
     * <p>The set params setter method.</p>
     * @param params {@link java.lang.String} <p>The params parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setParams(String params) {
        this.params = params;
        Optional.ofNullable(params).ifPresent(properties::setParams);
    }


    /**
     * <code>setDefaultCount</code>
     * <p>The set default count setter method.</p>
     * @param defaultCount {@link java.lang.Boolean} <p>The default count parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setDefaultCount(Boolean defaultCount) {
        this.defaultCount = defaultCount;
        Optional.ofNullable(defaultCount).ifPresent(properties::setDefaultCount);
    }


    /**
     * <code>setDialectAlias</code>
     * <p>The set dialect alias setter method.</p>
     * @param dialectAlias {@link java.lang.String} <p>The dialect alias parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setDialectAlias(String dialectAlias) {
        this.dialectAlias = dialectAlias;
        Optional.ofNullable(dialectAlias).ifPresent(properties::setDialectAlias);
    }


    /**
     * <code>setAutoDialectClass</code>
     * <p>The set auto dialect class setter method.</p>
     * @param autoDialectClass {@link java.lang.String} <p>The auto dialect class parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setAutoDialectClass(String autoDialectClass) {
        this.autoDialectClass = autoDialectClass;
        Optional.ofNullable(autoDialectClass).ifPresent(properties::setAutoDialectClass);
    }


    /**
     * <code>setUseSqlserver2012</code>
     * <p>The set use sqlserver 2012 setter method.</p>
     * @param useSqlserver2012 {@link java.lang.Boolean} <p>The use sqlserver 2012 parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setUseSqlserver2012(Boolean useSqlserver2012) {
        this.useSqlserver2012 = useSqlserver2012;
        Optional.ofNullable(useSqlserver2012).ifPresent(v -> properties.setProperty("useSqlserver2012", v.toString()));
    }


    /**
     * <code>setCountColumn</code>
     * <p>The set count column setter method.</p>
     * @param countColumn {@link java.lang.String} <p>The count column parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setCountColumn(String countColumn) {
        this.countColumn = countColumn;
        Optional.ofNullable(countColumn).ifPresent(v -> properties.setProperty("countColumn", v));
    }


    /**
     * <code>setReplaceSql</code>
     * <p>The set replace sql setter method.</p>
     * @param replaceSql {@link java.lang.String} <p>The replace sql parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setReplaceSql(String replaceSql) {
        this.replaceSql = replaceSql;
        Optional.ofNullable(replaceSql).ifPresent(v -> properties.setProperty("replaceSql", v));
    }


    /**
     * <code>setSqlCacheClass</code>
     * <p>The set sql cache class setter method.</p>
     * @param sqlCacheClass {@link java.lang.String} <p>The sql cache class parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setSqlCacheClass(String sqlCacheClass) {
        this.sqlCacheClass = sqlCacheClass;
        Optional.ofNullable(sqlCacheClass).ifPresent(v -> properties.setProperty("sqlCacheClass", v));
    }


    /**
     * <code>setBoundSqlInterceptors</code>
     * <p>The set bound sql interceptors setter method.</p>
     * @param boundSqlInterceptors {@link java.lang.String} <p>The bound sql interceptors parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setBoundSqlInterceptors(String boundSqlInterceptors) {
        this.boundSqlInterceptors = boundSqlInterceptors;
        Optional.ofNullable(boundSqlInterceptors).ifPresent(v -> properties.setProperty("boundSqlInterceptors", v));
    }


    /**
     * <code>setKeepOrderBy</code>
     * <p>The set keep order by setter method.</p>
     * @param keepOrderBy {@link java.lang.Boolean} <p>The keep order by parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setKeepOrderBy(Boolean keepOrderBy) {
        this.keepOrderBy = keepOrderBy;
        Optional.ofNullable(keepOrderBy).ifPresent(v -> properties.setProperty("keepOrderBy", v.toString()));
    }


    /**
     * <code>setKeepSubSelectOrderBy</code>
     * <p>The set keep sub select order by setter method.</p>
     * @param keepSubSelectOrderBy {@link java.lang.Boolean} <p>The keep sub select order by parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setKeepSubSelectOrderBy(Boolean keepSubSelectOrderBy) {
        this.keepSubSelectOrderBy = keepSubSelectOrderBy;
        Optional.ofNullable(keepSubSelectOrderBy).ifPresent(v -> properties.setProperty("keepSubSelectOrderBy", v.toString()));
    }


    /**
     * <code>setSqlParser</code>
     * <p>The set sql parser setter method.</p>
     * @param sqlParser {@link java.lang.String} <p>The sql parser parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setSqlParser(String sqlParser) {
        this.sqlParser = sqlParser;
        Optional.ofNullable(sqlParser).ifPresent(v -> properties.setProperty("sqlParser", v));
    }


    /**
     * <code>setAsyncCount</code>
     * <p>The set async count setter method.</p>
     * @param asyncCount {@link java.lang.Boolean} <p>The async count parameter is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public void setAsyncCount(Boolean asyncCount) {
        this.asyncCount = asyncCount;
        Optional.ofNullable(asyncCount).ifPresent(v -> properties.setProperty("asyncCount", v.toString()));
    }


    /**
     * <code>setCountSqlParser</code>
     * <p>The set count sql parser setter method.</p>
     * @param countSqlParser {@link java.lang.String} <p>The count sql parser parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setCountSqlParser(String countSqlParser) {
        this.countSqlParser = countSqlParser;
        Optional.ofNullable(countSqlParser).ifPresent(v -> properties.setProperty("countSqlParser", v));
    }


    /**
     * <code>setOrderBySqlParser</code>
     * <p>The set order by sql parser setter method.</p>
     * @param orderBySqlParser {@link java.lang.String} <p>The order by sql parser parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setOrderBySqlParser(String orderBySqlParser) {
        this.orderBySqlParser = orderBySqlParser;
        Optional.ofNullable(orderBySqlParser).ifPresent(v -> properties.setProperty("orderBySqlParser", v));
    }


    /**
     * <code>setSqlServerSqlParser</code>
     * <p>The set sql server sql parser setter method.</p>
     * @param sqlServerSqlParser {@link java.lang.String} <p>The sql server sql parser parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public void setSqlServerSqlParser(String sqlServerSqlParser) {
        this.sqlServerSqlParser = sqlServerSqlParser;
        Optional.ofNullable(sqlServerSqlParser).ifPresent(v -> properties.setProperty("sqlServerSqlParser", v));
    }

    /**
     * <code>CacheProperties</code>
     * <p>The cache properties class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see java.util.Properties
     * @since Jdk17
     */
    public static class CacheProperties extends Properties {
        /**
         * <code>getOffsetAsPageNum</code>
         * <p>The get offset as page num getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get offset as page num return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getOffsetAsPageNum() {
            return Boolean.valueOf(getProperty("offsetAsPageNum"));
        }

        /**
         * <code>setOffsetAsPageNum</code>
         * <p>The set offset as page num setter method.</p>
         * @param offsetAsPageNum {@link java.lang.Boolean} <p>The offset as page num parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setOffsetAsPageNum(Boolean offsetAsPageNum) {
            setProperty("offsetAsPageNum", offsetAsPageNum.toString());
        }

        /**
         * <code>getRowBoundsWithCount</code>
         * <p>The get row bounds with count getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get row bounds with count return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getRowBoundsWithCount() {
            return Boolean.valueOf(getProperty("rowBoundsWithCount"));
        }

        /**
         * <code>setRowBoundsWithCount</code>
         * <p>The set row bounds with count setter method.</p>
         * @param rowBoundsWithCount {@link java.lang.Boolean} <p>The row bounds with count parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setRowBoundsWithCount(Boolean rowBoundsWithCount) {
            setProperty("rowBoundsWithCount", rowBoundsWithCount.toString());
        }

        /**
         * <code>getPageSizeZero</code>
         * <p>The get page size zero getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get page size zero return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getPageSizeZero() {
            return Boolean.valueOf(getProperty("pageSizeZero"));
        }

        /**
         * <code>setPageSizeZero</code>
         * <p>The set page size zero setter method.</p>
         * @param pageSizeZero {@link java.lang.Boolean} <p>The page size zero parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setPageSizeZero(Boolean pageSizeZero) {
            setProperty("pageSizeZero", pageSizeZero.toString());
        }

        /**
         * <code>getReasonable</code>
         * <p>The get reasonable getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get reasonable return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getReasonable() {
            return Boolean.valueOf(getProperty("reasonable"));
        }

        /**
         * <code>setReasonable</code>
         * <p>The set reasonable setter method.</p>
         * @param reasonable {@link java.lang.Boolean} <p>The reasonable parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setReasonable(Boolean reasonable) {
            setProperty("reasonable", reasonable.toString());
        }

        /**
         * <code>getSupportMethodsArguments</code>
         * <p>The get support methods arguments getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get support methods arguments return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getSupportMethodsArguments() {
            return Boolean.valueOf(getProperty("supportMethodsArguments"));
        }

        /**
         * <code>setSupportMethodsArguments</code>
         * <p>The set support methods arguments setter method.</p>
         * @param supportMethodsArguments {@link java.lang.Boolean} <p>The support methods arguments parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setSupportMethodsArguments(Boolean supportMethodsArguments) {
            setProperty("supportMethodsArguments", supportMethodsArguments.toString());
        }

        /**
         * <code>getDialect</code>
         * <p>The get dialect getter method.</p>
         * @return {@link java.lang.String} <p>The get dialect return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getDialect() {
            return getProperty("dialect");
        }

        /**
         * <code>setDialect</code>
         * <p>The set dialect setter method.</p>
         * @param dialect {@link java.lang.String} <p>The dialect parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setDialect(String dialect) {
            setProperty("dialect", dialect);
        }

        /**
         * <code>getHelperDialect</code>
         * <p>The get helper dialect getter method.</p>
         * @return {@link java.lang.String} <p>The get helper dialect return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getHelperDialect() {
            return getProperty("helperDialect");
        }

        /**
         * <code>setHelperDialect</code>
         * <p>The set helper dialect setter method.</p>
         * @param helperDialect {@link java.lang.String} <p>The helper dialect parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setHelperDialect(String helperDialect) {
            setProperty("helperDialect", helperDialect);
        }

        /**
         * <code>getAutoRuntimeDialect</code>
         * <p>The get auto runtime dialect getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get auto runtime dialect return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getAutoRuntimeDialect() {
            return Boolean.valueOf(getProperty("autoRuntimeDialect"));
        }

        /**
         * <code>setAutoRuntimeDialect</code>
         * <p>The set auto runtime dialect setter method.</p>
         * @param autoRuntimeDialect {@link java.lang.Boolean} <p>The auto runtime dialect parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setAutoRuntimeDialect(Boolean autoRuntimeDialect) {
            setProperty("autoRuntimeDialect", autoRuntimeDialect.toString());
        }

        /**
         * <code>getAutoDialect</code>
         * <p>The get auto dialect getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get auto dialect return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getAutoDialect() {
            return Boolean.valueOf(getProperty("autoDialect"));
        }

        /**
         * <code>setAutoDialect</code>
         * <p>The set auto dialect setter method.</p>
         * @param autoDialect {@link java.lang.Boolean} <p>The auto dialect parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setAutoDialect(Boolean autoDialect) {
            setProperty("autoDialect", autoDialect.toString());
        }

        /**
         * <code>getCloseConn</code>
         * <p>The get close conn getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get close conn return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getCloseConn() {
            return Boolean.valueOf(getProperty("closeConn"));
        }

        /**
         * <code>setCloseConn</code>
         * <p>The set close conn setter method.</p>
         * @param closeConn {@link java.lang.Boolean} <p>The close conn parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setCloseConn(Boolean closeConn) {
            setProperty("closeConn", closeConn.toString());
        }

        /**
         * <code>getParams</code>
         * <p>The get params getter method.</p>
         * @return {@link java.lang.String} <p>The get params return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getParams() {
            return getProperty("params");
        }

        /**
         * <code>setParams</code>
         * <p>The set params setter method.</p>
         * @param params {@link java.lang.String} <p>The params parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setParams(String params) {
            setProperty("params", params);
        }

        /**
         * <code>getDefaultCount</code>
         * <p>The get default count getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get default count return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getDefaultCount() {
            return Boolean.valueOf(getProperty("defaultCount"));
        }

        /**
         * <code>setDefaultCount</code>
         * <p>The set default count setter method.</p>
         * @param defaultCount {@link java.lang.Boolean} <p>The default count parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setDefaultCount(Boolean defaultCount) {
            setProperty("defaultCount", defaultCount.toString());
        }

        /**
         * <code>getDialectAlias</code>
         * <p>The get dialect alias getter method.</p>
         * @return {@link java.lang.String} <p>The get dialect alias return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getDialectAlias() {
            return getProperty("dialectAlias");
        }

        /**
         * <code>setDialectAlias</code>
         * <p>The set dialect alias setter method.</p>
         * @param dialectAlias {@link java.lang.String} <p>The dialect alias parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setDialectAlias(String dialectAlias) {
            setProperty("dialectAlias", dialectAlias);
        }

        /**
         * <code>getAutoDialectClass</code>
         * <p>The get auto dialect class getter method.</p>
         * @return {@link java.lang.String} <p>The get auto dialect class return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getAutoDialectClass() {
            return getProperty("autoDialectClass");
        }

        /**
         * <code>setAutoDialectClass</code>
         * <p>The set auto dialect class setter method.</p>
         * @param autoDialectClass {@link java.lang.String} <p>The auto dialect class parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setAutoDialectClass(String autoDialectClass) {
            setProperty("autoDialectClass", autoDialectClass);
        }

        /**
         * <code>getAsyncCount</code>
         * <p>The get async count getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get async count return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getAsyncCount() {
            return Boolean.valueOf(getProperty("asyncCount"));
        }

        /**
         * <code>setAsyncCount</code>
         * <p>The set async count setter method.</p>
         * @param asyncCount {@link java.lang.Boolean} <p>The async count parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setAsyncCount(Boolean asyncCount) {
            setProperty("asyncCount", asyncCount.toString());
        }

        /**
         * <code>getCountSqlParser</code>
         * <p>The get count sql parser getter method.</p>
         * @return {@link java.lang.String} <p>The get count sql parser return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getCountSqlParser() {
            return getProperty("countSqlParser");
        }

        /**
         * <code>setCountSqlParser</code>
         * <p>The set count sql parser setter method.</p>
         * @param countSqlParser {@link java.lang.String} <p>The count sql parser parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setCountSqlParser(String countSqlParser) {
            setProperty("countSqlParser", countSqlParser);
        }

        /**
         * <code>getOrderBySqlParser</code>
         * <p>The get order by sql parser getter method.</p>
         * @return {@link java.lang.String} <p>The get order by sql parser return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getOrderBySqlParser() {
            return getProperty("orderBySqlParser");
        }

        /**
         * <code>setOrderBySqlParser</code>
         * <p>The set order by sql parser setter method.</p>
         * @param orderBySqlParser {@link java.lang.String} <p>The order by sql parser parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setOrderBySqlParser(String orderBySqlParser) {
            setProperty("orderBySqlParser", orderBySqlParser);
        }

        /**
         * <code>getSqlServerSqlParser</code>
         * <p>The get sql server sql parser getter method.</p>
         * @return {@link java.lang.String} <p>The get sql server sql parser return object is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public String getSqlServerSqlParser() {
            return getProperty("sqlServerSqlParser");
        }

        /**
         * <code>setSqlServerSqlParser</code>
         * <p>The set sql server sql parser setter method.</p>
         * @param sqlServerSqlParser {@link java.lang.String} <p>The sql server sql parser parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public void setSqlServerSqlParser(String sqlServerSqlParser) {
            setProperty("sqlServerSqlParser", sqlServerSqlParser);
        }

        /**
         * <code>setBannerEnabled</code>
         * <p>The set banner enabled setter method.</p>
         * @param bannerEnabled {@link java.lang.Boolean} <p>The banner enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setBannerEnabled(Boolean bannerEnabled) {
            setProperty("banner", bannerEnabled.toString());
        }

        /**
         * <code>getBannerEnabled</code>
         * <p>The get banner enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get banner enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getBannerEnabled() {
            return Boolean.valueOf(getProperty("banner"));
        }
    }
}
