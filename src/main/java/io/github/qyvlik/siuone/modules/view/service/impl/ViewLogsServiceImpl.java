package io.github.qyvlik.siuone.modules.view.service.impl;

import io.github.qyvlik.siuone.common.wapper.CrudService;
import io.github.qyvlik.siuone.modules.view.dao.ViewLogsMapping;
import io.github.qyvlik.siuone.modules.view.entity.ViewLogs;
import io.github.qyvlik.siuone.modules.view.service.ViewLogsService;
import org.springframework.stereotype.Service;

@Service("viewLogsService")
public class ViewLogsServiceImpl extends CrudService<ViewLogs, ViewLogsMapping> implements ViewLogsService {
    @Override
    public ViewLogs get(Long id) {
        return super.get(id);
    }
}
