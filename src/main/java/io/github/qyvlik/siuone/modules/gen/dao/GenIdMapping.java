package io.github.qyvlik.siuone.modules.gen.dao;

import io.github.qyvlik.siuone.common.wapper.CrudMapping;
import io.github.qyvlik.siuone.modules.gen.entity.GenId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GenIdMapping extends CrudMapping<GenId> {
}
