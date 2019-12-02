package io.github.qyvlik.siuone.modules.view.service.impl;

import io.github.qyvlik.siuone.common.wapper.CrudService;
import io.github.qyvlik.siuone.modules.view.dao.ViewUserAgentMapping;
import io.github.qyvlik.siuone.modules.view.entity.ViewUserAgent;
import io.github.qyvlik.siuone.modules.view.service.ViewUserAgentService;
import org.springframework.stereotype.Service;

@Service("viewUserAgentService")
public class ViewUserAgentServiceImpl extends CrudService<ViewUserAgent, ViewUserAgentMapping> implements ViewUserAgentService {

    @Override
    public ViewUserAgent getByHash(String hash) {
        return this.mapping.getByHash(hash);
    }

    @Override
    public Long getIdByHash(String hash) {
        return this.mapping.getIdByHash(hash);
    }
}
