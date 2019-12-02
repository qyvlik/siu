package io.github.qyvlik.siuone.modules.view.service;

import io.github.qyvlik.siuone.modules.view.entity.ViewLogs;

public interface ViewLogsService {

    ViewLogs get(Long id);

    Long insert(ViewLogs viewLogs);
}
