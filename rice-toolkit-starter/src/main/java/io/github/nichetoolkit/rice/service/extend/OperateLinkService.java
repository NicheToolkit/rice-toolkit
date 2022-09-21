package io.github.nichetoolkit.rice.service.extend;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rice.enums.OperateType;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;

/**
 * <p>OperateLinkService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface OperateLinkService<I> {

    /**
     * 通过id集合批量逻辑删除
     * @param linkIdList 对象的id集合
     * @param operateType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateAllByLinkIds(Collection<I> linkIdList, OperateType operateType) throws RestException;

    /**
     * 通过id单个逻辑删除
     * @param linkId 对象的id
     * @param operateType 对象的操作
     * @throws RestException 模块异常
     */
    @Transactional(rollbackFor = {RestException.class, SQLException.class})
    void operateByLinkId(I linkId, OperateType operateType) throws RestException;

}
