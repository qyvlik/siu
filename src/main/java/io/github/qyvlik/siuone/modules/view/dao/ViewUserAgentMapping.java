package io.github.qyvlik.siuone.modules.view.dao;

import io.github.qyvlik.siuone.common.wapper.CrudMapping;
import io.github.qyvlik.siuone.modules.view.entity.ViewUserAgent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ViewUserAgentMapping extends CrudMapping<ViewUserAgent> {
    ViewUserAgent getByHash(@Param("userAgentHash") String userAgentHash);

    Long getIdByHash(@Param("userAgentHash") String userAgentHash);
}
