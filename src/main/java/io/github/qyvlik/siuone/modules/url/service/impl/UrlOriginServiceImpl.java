package io.github.qyvlik.siuone.modules.url.service.impl;

import io.github.qyvlik.siuone.common.wapper.CrudService;
import io.github.qyvlik.siuone.modules.url.dao.UrlOriginMapping;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import io.github.qyvlik.siuone.modules.url.service.UrlOriginService;
import org.springframework.stereotype.Service;

@Service("urlOriginService")
public class UrlOriginServiceImpl extends CrudService<UrlOrigin, UrlOriginMapping> implements UrlOriginService {
    @Override
    public UrlOrigin get(Long id) {
        return super.get(id);
    }

    @Override
    public String getOriginUrl(Long id) {
        return this.mapping.getOriginUrl(id);
    }
}
