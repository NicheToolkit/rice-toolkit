package io.github.nichetoolkit.rice.mapper.natives;

import io.github.nichetoolkit.rice.IdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <code>NameLoadMapper</code>
 * <p>The type name load mapper interface.</p>
 * @param <E> {@link io.github.nichetoolkit.rice.IdEntity} <p>the generic parameter is <code>IdEntity</code> type.</p>
 * @param <I> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rice.IdEntity
 * @since Jdk1.8
 */
public interface NameLoadMapper<E extends IdEntity<I>, I> {
    /**
     * <code>findByNameLoad</code>
     * <p>the by name load method.</p>
     * @param name       {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param logicValue {@link java.lang.String} <p>the logic value parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the by name load return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findByNameLoad(@Param("name") String name,@Param("logicValue") String logicValue, @Param("loadParams") Boolean... loadParams);

    /**
     * <code>findDynamicByNameLoad</code>
     * <p>the dynamic by name load method.</p>
     * @param tablename  {@link java.lang.String} <p>the tablename parameter is <code>String</code> type.</p>
     * @param name       {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param logicValue {@link java.lang.String} <p>the logic value parameter is <code>String</code> type.</p>
     * @param loadParams {@link java.lang.Boolean} <p>the load params parameter is <code>Boolean</code> type.</p>
     * @return {@link java.util.List} <p>the dynamic by name load return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see org.apache.ibatis.annotations.Param
     * @see java.lang.Boolean
     * @see java.util.List
     */
    List<E> findDynamicByNameLoad(@Param("tablename") String tablename, @Param("name") String name, @Param("logicValue") String logicValue, @Param("loadParams") Boolean... loadParams);

}
