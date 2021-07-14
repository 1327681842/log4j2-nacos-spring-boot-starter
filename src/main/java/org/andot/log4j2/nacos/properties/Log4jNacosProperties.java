package org.andot.log4j2.nacos.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性配置
 * @author lucas
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "log4j2-nacos.config")
public class Log4jNacosProperties {
    private String serverAddr;
    private String dataId;
    private String namespace;
    private String group;
}
