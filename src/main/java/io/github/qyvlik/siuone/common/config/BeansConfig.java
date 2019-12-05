package io.github.qyvlik.siuone.common.config;

import io.github.qyvlik.siuone.common.properties.SiuProperties;
import io.github.qyvlik.siuone.common.properties.SnowflakeProperties;
import io.github.qyvlik.siuone.common.utils.SnowFlake;
import io.github.qyvlik.siuone.modules.gen.service.GenIdService;
import io.github.qyvlik.siuone.modules.gen.service.ShortUrlClient;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SnowflakeProperties.class, SiuProperties.class})
public class BeansConfig {

    private SnowflakeProperties snowflakeProperties;

    public BeansConfig(SnowflakeProperties snowflakeProperties) {
        this.snowflakeProperties = snowflakeProperties;
    }

    @Bean
    public ShortUrlClient shortUrlClient(
            @Autowired GenIdService genIdService) {

        Long finalMachineId = snowflakeProperties.getDataCenterId() << SnowFlake.DATACENTER_LEFT
                | snowflakeProperties.getMachineId() << SnowFlake.MACHINE_LEFT;

        return new ShortUrlClient(finalMachineId, genIdService);
    }

    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake(
                this.snowflakeProperties.getDataCenterId(),
                this.snowflakeProperties.getMachineId()
        );
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
