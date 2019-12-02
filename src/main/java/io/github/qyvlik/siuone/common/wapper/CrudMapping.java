package io.github.qyvlik.siuone.common.wapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface CrudMapping<T extends DataEntity> {

    T getBySharedIdAndId(@Param("sharedId") Serializable sharedId, @Param("id") Serializable id);

    T get(Serializable id);

    List<T> findList(T entity);

    Long update(T entity);

    Long insert(T entity);

    Long count(T entity);

    Long delete(Serializable id);

    Long insertList(List<T> list);
}
