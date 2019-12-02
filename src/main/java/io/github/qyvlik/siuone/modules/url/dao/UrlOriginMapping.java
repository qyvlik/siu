package io.github.qyvlik.siuone.modules.url.dao;

import io.github.qyvlik.siuone.common.wapper.CrudMapping;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UrlOriginMapping extends CrudMapping<UrlOrigin> {
    String getOriginUrl(@Param("id") Long id);
}
