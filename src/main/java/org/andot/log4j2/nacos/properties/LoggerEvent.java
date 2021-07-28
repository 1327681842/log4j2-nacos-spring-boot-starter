package org.andot.log4j2.nacos.properties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author lucas
 */
@Setter
@Getter
@ToString
public class LoggerEvent extends ApplicationEvent {
    private List<LoggerLevel> loggerLevelList;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public LoggerEvent(Object source) {
        super(source);
    }
}
