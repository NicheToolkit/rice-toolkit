package io.github.nichetoolkit.rice.mapper;

import io.github.nichetoolkit.rice.InfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoMapper<E extends InfoEntity<I>, I> extends SuperMapper<E, I> {
    List<E> findByName(@Param("name") String name, @Param("sign") String sign);

    List<E> findDynamicByName(@Param("tablename") String tablename, @Param("name") String name, @Param("sign") String sign);

    List<E> findByNameAndNotId(@Param("name") String name, @Param("id") I id, @Param("sign") String sign);

    List<E> findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id, @Param("sign") String sign);

    List<E> findByEntity(@Param("entity") E entity, @Param("sign") String sign);

    List<E> findDynamicByEntity(@Param("tablename") String tablename, @Param("entity") E entity, @Param("sign") String sign);

    List<E> findByEntityAndNotId(@Param("entity") E entity, @Param("id") I id, @Param("sign") String sign);

    List<E> findDynamicByEntityAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id, @Param("sign") String sign);
}
