package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>SuperMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("UnusedReturnValue")
public interface SuperMapper<E extends IdEntity<I>,I> {

    /*
     * Mapper的实现，关联实体映射泛型，必须放在第一个，在xxxMapper进行了多级继承的情况下，
     * 通用Mapper自动生成的方法，构建sql查询语句时，无法找到最终继承的Mapper<E>类，
     * 无法正确的获取E的类型，因此E，必须要放在第一位，参考 MapperTemplate 99行
     */

    /**
     * 实体保存（存在更新不存在插入）
     * @param entity 实体
     * @return Integer SQL影响行数
     */
    Integer save(@Param("entity") E entity);

    /**
     * 批量保存（存在更新不存在插入）
     * @param entityList 实体集合
     * @return Integer SQL影响行数
     */
    Integer saveAll(@Param("entityList") Collection<E> entityList);

    /**
     * 实体单个删除
     * @param id 实体id集合
     * @return Integer SQL影响行数（成功为1）
     */
    Integer deleteById(@Param("id") I id);

    /**
     * 实体批量删除
     * @param idList 实体id集合
     * @return Integer SQL影响行数
     */
    Integer deleteAll(@Param("idList") Collection<I> idList);

    /**
     * 通过id查询实体
     * @param id 实体id
     * @return T 查询的数据
     */
    E findById(@Param("id") I id);

    /**
     * 实体批量查询
     * @param idList 实体集合
     * @return List<T> 查询的数据集合
     */
    List<E> findAll(@Param("idList") Collection<I> idList);

    /**
     * 通过filter查询条件查询
     * @param whereSql 过滤条件
     * @return List<T>
     */
    List<E> findAllByWhere(@Param("whereSql") String whereSql);

    /**
     * 通过filter查询条件删除
     * @param whereSql 过滤条件
     */
    Integer deleteAllByWhere(@Param("whereSql") String whereSql);
}
