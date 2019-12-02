package io.github.qyvlik.siuone.modules.view.service;

import io.github.qyvlik.siuone.modules.view.entity.ViewUserAgent;

public interface ViewUserAgentService {

    Long insert(ViewUserAgent viewUserAgent);

    ViewUserAgent getByHash(String hash);

    Long getIdByHash(String hash);

}
