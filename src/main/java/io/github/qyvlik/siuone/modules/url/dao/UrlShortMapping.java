package io.github.qyvlik.siuone.modules.url.dao;

import io.github.qyvlik.siuone.common.wapper.CrudMapping;
import io.github.qyvlik.siuone.modules.url.entity.UrlShort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UrlShortMapping extends CrudMapping<UrlShort> {

    Long getOriginUrlId(@Param("id") Long id);

    Long getIdByOriginUrlId(@Param("originUrlId") Long originUrlId);

}
