package io.github.qyvlik.siuone.common.config;

import io.github.qyvlik.siuone.common.utils.SnowFlake;
import io.github.qyvlik.siuone.modules.gen.service.GenIdService;
import io.github.qyvlik.siuone.modules.gen.service.ShortUrlClient;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ShortUrlClient shortUrlClient(
            @Autowired GenIdService genIdService) {
        return new ShortUrlClient(0L, genIdService);
    }

    @Bean
    public SnowFlake snowFlake() {
        // todo
        return new SnowFlake(0L, 0L);
    }

    @Bean
    public UserAgentAnalyzer userAgentAnalyzer() {
        return UserAgentAnalyzer
                .newBuilder()
                .hideMatcherLoadStats()
                .withCache(10000)
                .build();
    }
}
