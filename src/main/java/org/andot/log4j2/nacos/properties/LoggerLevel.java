package org.andot.log4j2.nacos.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 日志等级对象
 * @author lucas
 */
@Setter
@Getter
public class LoggerLevel {
    private String name;
    private String level;
}
