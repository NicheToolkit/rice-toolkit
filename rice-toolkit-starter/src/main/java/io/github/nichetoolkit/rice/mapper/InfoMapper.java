package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.InfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>InfoMapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version V.0.0.1
 */
public interface InfoMapper<E extends InfoEntity<I>, I> extends SuperMapper<E, I> {
    /**
     * 根据名称判断是否存在
     * @param name 对象名称
     * @return List<T> 查询的数据集合
     */
    List<E> findByName(@Param("name") String name);

    /**
     * 根据名称判断是否存在
     * @param name 对象名称
     * @return List<T> 查询的数据集合
     */
    List<E> findDynamicByName(@Param("tablename") String tablename, @Param("name") String name);

    /**
     * 根据id和名称判断是否存在
     * @param name 对象名称
     * @param id   对象id
     * @return List<T> 查询的数据集合
     */
    List<E> findByNameAndNotId(@Param("name") String name, @Param("id") I id);

    /**
     * 根据id和名称判断是否存在
     * @param name 对象名称
     * @param id   对象id
     * @return List<T> 查询的数据集合
     */
    List<E> findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id);

    /**
     * 根据名称判断是否存在
     * @param entity 对象实体
     * @return List<T> 查询的数据集合
     */
    List<E> findByEntity(@Param("entity") E entity);

    /**
     * 根据名称判断是否存在
     * @param entity 对象实体
     * @return List<T> 查询的数据集合
     */
    List<E> findDynamicByEntity(@Param("tablename") String tablename, @Param("entity") E entity);

    /**
     * 根据id和名称判断是否存在
     * @param entity 对象实体
     * @param id     对象id
     * @return List<T> 查询的数据集合
     */
    List<E> findByEntityAndNotId(@Param("entity") E entity, @Param("id") I id);

    /**
     * 根据id和名称判断是否存在
     * @param entity 对象实体
     * @param id     对象id
     * @return List<T> 查询的数据集合
     */
    List<E> findDynamicByEntityAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id);
}
