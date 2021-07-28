package org.andot.log4j2.nacos.listener;

import lombok.extern.slf4j.Slf4j;
import org.andot.log4j2.nacos.properties.LoggerEvent;
import org.andot.log4j2.nacos.properties.LoggerLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 事件监听
 * @author lucas
 */
@Slf4j
@Configuration
public class Log4jListener implements ApplicationListener<LoggerEvent> {

    @Override
    public void onApplicationEvent(LoggerEvent loggerEvent) {
        String loggerFactoryClassStr = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
        System.out.println("loggerFactoryClassStr >>>> " + loggerFactoryClassStr);
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        org.apache.logging.log4j.core.config.Configuration config = ctx.getConfiguration();
        for (LoggerLevel loggerLevel : loggerEvent.getLoggerLevelList()) {
            LoggerConfig loggerConfig = config.getLoggerConfig(loggerLevel.getName());
            loggerConfig.setLevel(Level.valueOf(loggerLevel.getLevel()));
        }
        ctx.updateLoggers(config);
        System.out.println("当前日志已经生效！");
    }
}
