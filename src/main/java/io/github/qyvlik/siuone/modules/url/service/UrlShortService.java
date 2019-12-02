package io.github.qyvlik.siuone.modules.url.service;

import io.github.qyvlik.siuone.modules.url.entity.UrlOrigin;
import io.github.qyvlik.siuone.modules.url.entity.UrlShort;

public interface UrlShortService {
    UrlShort get(String shortUrl);

    UrlOrigin getOriginUrl(String shortUrl);

    UrlShort get(Long id);

    Long insert(UrlShort urlShort);
}
