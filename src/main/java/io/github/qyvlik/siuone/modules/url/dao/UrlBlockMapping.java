package io.github.qyvlik.siuone.modules.url.dao;

import io.github.qyvlik.siuone.common.wapper.CrudMapping;
import io.github.qyvlik.siuone.modules.url.entity.UrlBlock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UrlBlockMapping extends CrudMapping<UrlBlock> {
}
