package org.andot.log4j2.nacos.properties;

import lombok.*;

/**
 * 日志等级对象
 * @author lucas
 */
@Setter
@Getter
@Builder
public class LoggerLevel {
    private String name;
    private String level;
}
