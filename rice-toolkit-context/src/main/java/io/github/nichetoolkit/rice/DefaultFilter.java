package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.mybatis.builder.SqlBuilder;
import io.github.nichetoolkit.mybatis.consts.SQLConstants;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rice.builder.SqlBuilders;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.LogicMode;
import io.github.nichetoolkit.rice.filter.NameFilter;
import io.github.nichetoolkit.rice.filter.StateFilter;
import lombok.experimental.SuperBuilder;
import org.jspecify.annotations.NonNull;

import java.util.*;

/**
 * <code>DefaultFilter</code>
 * <p>The default filter class.</p>
 * @param <I> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.filter.NameFilter
 * @see lombok.experimental.SuperBuilder
 * @since Jdk17
 */
@SuperBuilder(builderMethodName = "ofDefaultBuilder")
public abstract class DefaultFilter<I, K> extends NameFilter<I, K> {

    /**
     * <code>DefaultFilter</code>
     * <p>Instantiates a new default filter.</p>
     */
    public DefaultFilter() {
    }

    /**
     * <code>DefaultFilter</code>
     * <p>Instantiates a new default filter.</p>
     * @param ids I <p>The ids parameter is <code>I</code> type.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings(value = "unchecked")
    public DefaultFilter(I... ids) {
        super(ids);
    }


    @Override
    public void toIdentitySql(SqlBuilder sqlBuilder, @NonNull String alias) throws RestException {
        List<I> idList = toIds();
        String prefix = null;
        if (GeneralUtils.isNotEmpty(alias) && alias.contains(SQLConstants.PERIOD)) {
            prefix = alias.split("\\.")[0];
        }
        String identitySql = DefaultIdentityHandler.toSqlHandle(prefix, this);
        SqlBuilders.append(sqlBuilder, identitySql);
    }

    /**
     * <code>toAlertnessSql</code>
     * <p>The to alertness sql method.</p>
     * @param sqlBuilder {@link io.github.nichetoolkit.mybatis.builder.SqlBuilder} <p>The sql builder parameter is <code>SqlBuilder</code> type.</p>
     * @param alias      {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.mybatis.builder.SqlBuilder
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void toAlertnessSql(SqlBuilder sqlBuilder, @NonNull String alias) throws RestException {
        if (this instanceof StateFilter) {
            StateFilter<?> stateFilter = (StateFilter<?>) this;
            List<?> states = stateFilter.toStates();
            String prefix = null;
            if (GeneralUtils.isNotEmpty(alias) && alias.contains(SQLConstants.PERIOD)) {
                prefix = alias.split("\\.")[0];
            }
            String alertnessSql = DefaultAlertnessHandler.toSqlHandle(prefix, stateFilter);
            SqlBuilders.append(sqlBuilder, alertnessSql);
        } else {
            throw new UnsupportedErrorException("the method of 'toAlertnessSql()' is unsupported，it is must be 'StatusFilter' type.");
        }
    }

    /**
     * <code>toRemoveSql</code>
     * <p>The to remove sql method.</p>
     * @param logicMode     {@link io.github.nichetoolkit.rice.enums.LogicMode} <p>The logic mode parameter is <code>LogicMode</code> type.</p>
     * @param markOfLogic   {@link java.lang.Object} <p>The mark of logic parameter is <code>Object</code> type.</p>
     * @param accurateJudge {@link java.lang.Boolean} <p>The accurate judge parameter is <code>Boolean</code> type.</p>
     * @param unmarkOfLogic {@link java.lang.Object} <p>The unmark of logic parameter is <code>Object</code> type.</p>
     * @param alias         {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.DefaultFilter} <p>The to remove sql return object is <code>DefaultFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.LogicMode
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public DefaultFilter<I, K> toRemoveSql(LogicMode logicMode, Object markOfLogic, Boolean accurateJudge, Object unmarkOfLogic, @NonNull String alias) throws RestException {
        if (GeneralUtils.isNotEmpty(markOfLogic)) {
            if (logicMode == LogicMode.CONFIG && GeneralUtils.isNotEmpty(markOfLogic)) {
                if (accurateJudge) {
                    if (this.isRemove) {
                        SqlBuilders.equal(SQL_BUILDER, alias, markOfLogic);
                    } else if (GeneralUtils.isNotEmpty(unmarkOfLogic)){
                        SqlBuilders.equal(SQL_BUILDER, alias, unmarkOfLogic);
                    }  else {
                        SqlBuilders.isnull(SQL_BUILDER, alias);
                    }
                } else {
                    if (this.isRemove) {
                        SqlBuilders.equal(SQL_BUILDER, alias, markOfLogic);
                    } else {
                        SqlBuilders.unequal(SQL_BUILDER, alias, markOfLogic);
                    }
                }
            } else if (logicMode == LogicMode.AUTO) {
                if (this.isRemove) {
                    SqlBuilders.nonnull(SQL_BUILDER, alias);
                } else {
                    SqlBuilders.isnull(SQL_BUILDER, alias);
                }
            }
        }
        return this;
    }

    /**
     * <code>toQuerySql</code>
     * <p>The to query sql method.</p>
     * @param deleteMode    {@link io.github.nichetoolkit.rice.enums.DeleteMode} <p>The delete mode parameter is <code>DeleteMode</code> type.</p>
     * @param logicMode     {@link io.github.nichetoolkit.rice.enums.LogicMode} <p>The logic mode parameter is <code>LogicMode</code> type.</p>
     * @param markOfLogic   {@link java.lang.Object} <p>The mark of logic parameter is <code>Object</code> type.</p>
     * @param accurateJudge {@link java.lang.Boolean} <p>The accurate judge parameter is <code>Boolean</code> type.</p>
     * @param unmarkOfLogic {@link java.lang.Object} <p>The unmark of logic parameter is <code>Object</code> type.</p>
     * @param alias         {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.DefaultFilter} <p>The to query sql return object is <code>DefaultFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.enums.DeleteMode
     * @see io.github.nichetoolkit.rice.enums.LogicMode
     * @see java.lang.Object
     * @see java.lang.Boolean
     * @see java.lang.String
     * @see org.jspecify.annotations.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public DefaultFilter<I, K> toQuerySql(DeleteMode deleteMode, LogicMode logicMode, Object markOfLogic, Boolean accurateJudge, Object unmarkOfLogic, @NonNull String alias) throws RestException {
        if (deleteMode == DeleteMode.OPERATE) {
            return toOperateSql(alias);
        } else if (deleteMode == DeleteMode.REMOVE) {
            return toRemoveSql(logicMode, markOfLogic, accurateJudge, unmarkOfLogic, alias);
        }
        return this;
    }

    @Override
    public DefaultFilter<I, K> toNameSql(@NonNull String alias) throws RestException {
        super.toNameSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toJsonbSql(@NonNull String alias) throws RestException {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toJsonbSql(@NonNull String alias, String variable) throws RestException {
        super.toJsonbSql(alias, variable);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toTimeSql(@NonNull String alias) throws RestException {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toIdSql(@NonNull String alias) throws RestException {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public DefaultFilter<I, K> toOperateSql(@NonNull String alias) throws RestException {
        super.toOperateSql(alias);
        return this;
    }

}
