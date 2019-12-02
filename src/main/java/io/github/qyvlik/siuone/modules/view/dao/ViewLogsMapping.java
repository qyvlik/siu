package io.github.qyvlik.siuone.modules.view.dao;

import io.github.qyvlik.siuone.common.wapper.CrudMapping;
import io.github.qyvlik.siuone.modules.view.entity.ViewLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ViewLogsMapping extends CrudMapping<ViewLogs> {
}
