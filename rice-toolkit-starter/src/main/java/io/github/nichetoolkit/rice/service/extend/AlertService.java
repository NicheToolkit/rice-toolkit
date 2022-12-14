package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>OperateService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface AlertService<I> {

    /**
     * 通过id集合批量逻辑删除
     * @param idList  对象的id集合
     * @param keyType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertAll(Collection<I> idList, RestKey<Integer> keyType) throws RestException;

    /**
     * 通过id集合批量逻辑删除
     * @param idList  对象的id集合
     * @param keyType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertAll(String tableKey, Collection<I> idList, RestKey<Integer> keyType) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param id      对象的id
     * @param keyType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertById(I id, RestKey<Integer> keyType) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param id      对象的id
     * @param keyType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertById(String tableKey, I id, RestKey<Integer> keyType) throws RestException;
}
