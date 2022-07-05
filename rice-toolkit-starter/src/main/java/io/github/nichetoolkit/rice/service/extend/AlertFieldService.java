package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestKey;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>OperateService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface AlertFieldService<I> {

    /**
     * 通过id集合批量逻辑删除
     * @param field 字段
     * @param idList 对象的id集合
     * @param keyType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertAll( Collection<I> idList, String field, RestKey<Integer> keyType) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param field 字段
     * @param id 对象的id
     * @param keyType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void alertById( I id, String field, RestKey<Integer> keyType) throws RestException;
}
