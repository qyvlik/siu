package io.github.qyvlik.siuone.modules.url.service.impl;

import io.github.qyvlik.siuone.common.utils.Base62;
import io.github.qyvlik.siuone.common.wapper.CrudService;
import io.github.qyvlik.siuone.modules.url.dao.UrlShortMapping;
import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import io.github.qyvlik.siuone.modules.url.entity.UrlShort;
import io.github.qyvlik.siuone.modules.url.service.UrlOriginService;
import io.github.qyvlik.siuone.modules.url.service.UrlShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("urlShortService")
public class UrlShortServiceImpl extends CrudService<UrlShort, UrlShortMapping> implements UrlShortService {

    @Autowired
    private UrlOriginService urlOriginService;

    @Override
    public UrlShort get(String shortUrl) {
        return super.get(Base62.toBase10(shortUrl));
    }

    @Override
    public UrlOrigin getOriginUrl(String shortUrl) {
        Long originUrlId = this.mapping.getOriginUrlId(Base62.toBase10(shortUrl));
        if (originUrlId != null) {
            return urlOriginService.get(originUrlId);
        }
        return null;
    }

    @Override
    public UrlShort get(Long id) {
        return super.get(id);
    }
}
