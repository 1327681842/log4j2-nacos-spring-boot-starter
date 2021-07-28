package org.andot.log4j2.nacos.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andot.log4j2.nacos.converter.Log4jNacosConfigConverter;
import org.andot.log4j2.nacos.properties.Log4jNacosProperties;
import org.andot.log4j2.nacos.properties.LoggerEvent;
import org.andot.log4j2.nacos.properties.LoggerLevel;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * log4j use nacos implements dynamic config
 * @author lucas
 */
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty("log4j2-nacos.config.server-addr")
@Configuration
public class LoggerDynamicsConfig {

    private final ApplicationContext applicationContext;

    @NacosConfigListener(groupId = "${log4j2-nacos.config.group}",
            dataId = "${log4j2-nacos.config.data-id}",
            timeout = 3000, converter = Log4jNacosConfigConverter.class)
    public void onChange(List<LoggerLevel> loggerLevelList) {
        LoggerEvent loggerEvent = new LoggerEvent(LoggerDynamicsConfig.class);
        loggerEvent.setLoggerLevelList(loggerLevelList);
        applicationContext.publishEvent(loggerEvent);
    }
}
