package org.andot.log4j2.nacos.properties;

import lombok.*;

import java.io.Serializable;

/**
 * 日志等级对象
 * @author lucas
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoggerLevel implements Serializable {
    private String name;
    private String level;
}
