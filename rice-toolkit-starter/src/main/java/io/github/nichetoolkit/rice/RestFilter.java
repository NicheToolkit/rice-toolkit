package io.github.nichetoolkit.rice;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.DeleteMode;
import io.github.nichetoolkit.rice.enums.LogicMode;
import io.github.nichetoolkit.rice.service.SuperService;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

/**
 * <code>RestFilter</code>
 * <p>The rest filter class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.DefaultFilter
 * @see lombok.experimental.SuperBuilder
 * @since Jdk1.8
 */
@SuperBuilder(builderMethodName = "ofRestBuilder")
public abstract class RestFilter extends DefaultFilter<String, String> {

    /**
     * <code>RestFilter</code>
     * <p>Instantiates a new rest filter.</p>
     */
    public RestFilter() {
    }

    /**
     * <code>RestFilter</code>
     * <p>Instantiates a new rest filter.</p>
     * @param ids {@link java.lang.String} <p>The ids parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestFilter(String... ids) {
        super(ids);
    }


    /**
     * <code>toRemoveSql</code>
     * <p>The to remove sql method.</p>
     * @param superService {@link io.github.nichetoolkit.rice.service.SuperService} <p>The super service parameter is <code>SuperService</code> type.</p>
     * @param alias        {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestFilter} <p>The to remove sql return object is <code>RestFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.SuperService
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestFilter toRemoveSql(SuperService<? extends IdModel<String>, ? extends IdEntity<String>, ? extends RestFilter, String, String> superService, @NonNull String alias) throws RestException {
        LogicMode logicMode = superService.logicMode();
        Object unmarkOfLogic = superService.unmarkOfLogic();
        Boolean accurateJudge = superService.judgeOfAccurate();
        Object markOfLogic = superService.markOfLogic();
        super.toRemoveSql(logicMode, markOfLogic, accurateJudge, unmarkOfLogic, alias);
        return this;
    }

    /**
     * <code>toQuerySql</code>
     * <p>The to query sql method.</p>
     * @param superService {@link io.github.nichetoolkit.rice.service.SuperService} <p>The super service parameter is <code>SuperService</code> type.</p>
     * @param alias        {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rice.RestFilter} <p>The to query sql return object is <code>RestFilter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rice.service.SuperService
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    public RestFilter toQuerySql(SuperService<? extends IdModel<String>, ? extends IdEntity<String>, ? extends RestFilter, String, String> superService, @NonNull String alias) throws RestException {
        DeleteMode deleteMode = superService.deleteMode();
        LogicMode logicMode = superService.logicMode();
        Object markOfLogic = superService.markOfLogic();
        Boolean accurateJudge = superService.judgeOfAccurate();
        Object unmarkOfLogic = superService.unmarkOfLogic();
        super.toQuerySql(deleteMode, logicMode, markOfLogic, accurateJudge, unmarkOfLogic, alias);
        return this;
    }


    @Override
    public RestFilter toNameSql(@NonNull String alias) throws RestException {
        super.toNameSql(alias);
        return this;
    }

    @Override
    public RestFilter toJsonbSql(@NonNull String alias) throws RestException {
        super.toJsonbSql(alias);
        return this;
    }

    @Override
    public RestFilter toJsonbSql(@NonNull String alias, String variable) throws RestException {
        super.toJsonbSql(alias, variable);
        return this;
    }

    @Override
    public RestFilter toTimeSql(@NonNull String alias) throws RestException {
        super.toTimeSql(alias);
        return this;
    }

    @Override
    public RestFilter toIdSql(@NonNull String alias) throws RestException {
        super.toIdSql(alias);
        return this;
    }

    @Override
    public RestFilter toOperateSql(@NonNull String alias) throws RestException {
        super.toOperateSql(alias);
        return this;
    }

}
