package com.steafan.cangzhu.api.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cz-copilot")
@Data
public class CzProperties {
    private Jwt jwt;
}

