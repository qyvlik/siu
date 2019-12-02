package io.github.qyvlik.siuone.modules.url.service;

import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;

public interface UrlOriginService {
    Long insert(UrlOrigin urlOrigin);

    UrlOrigin get(Long id);

    String getOriginUrl(Long id);
}
