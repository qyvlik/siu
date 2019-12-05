package io.github.qyvlik.siuone.common.config;

import io.github.qyvlik.siuone.common.properties.SiuProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SiuProperties.class)
public class SiuConfig {


}
