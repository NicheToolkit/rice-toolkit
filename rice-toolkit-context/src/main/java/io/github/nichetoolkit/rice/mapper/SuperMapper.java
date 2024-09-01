package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface SuperMapper<E extends IdEntity<I>,I> {

    Integer save(@Param("entity") E entity);

    Integer saveDynamic(@Param("tablename") String tablename, @Param("entity") E entity);

    Integer saveAll(@Param("entityList") Collection<E> entityList);

    Integer saveDynamicAll(@Param("tablename") String tablename, @Param("entityList") Collection<E> entityList);

    Integer deleteById(@Param("id") I id);

    Integer deleteDynamicById(@Param("tablename") String tablename, @Param("id") I id);

    Integer deleteAll(@Param("idList") Collection<I> idList);

    Integer deleteDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList);

    E findById(@Param("id") I id);

    E findDynamicById(@Param("tablename") String tablename, @Param("id") I id);

    List<E> findAll(@Param("idList") Collection<I> idList);

    List<E> findDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection<I> idList);

    List<E> findAllByWhere(@Param("whereSql") String whereSql);

    List<E> findDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql);

    Integer deleteAllByWhere(@Param("whereSql") String whereSql);

    Integer deleteDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql);
}
