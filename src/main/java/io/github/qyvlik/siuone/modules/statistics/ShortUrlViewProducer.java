package io.github.qyvlik.siuone.modules.statistics;

import io.github.qyvlik.siuone.modules.shorturl.entity.ShortUrlView;

public interface ShortUrlViewProducer {
    void send(ShortUrlView shortUrlView);
}
