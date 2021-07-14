package org.andot.log4j2.nacos.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andot.log4j2.nacos.properties.Log4jNacosProperties;
import org.andot.log4j2.nacos.properties.LoggerLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties("log4j2-nacos.config.server-addr")
@Configuration
public class LoggerDynamicsConfig {

    private final Log4jNacosProperties log4jNacosProperties;

    @PostConstruct
    public void init() {
        try {
            ConfigService configService = NacosFactory.createConfigService(log4jNacosProperties.getServerAddr());
            String configInfo = configService.getConfig(log4jNacosProperties.getDataId(), log4jNacosProperties.getGroup(), 46000);
            log.info(configInfo);
            configService.addListener(log4jNacosProperties.getDataId(), log4jNacosProperties.getGroup(), new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String json) {
                    log.info("==============log config info====================");
                    log.info(json);
                    log.info("=================================================");
                    List<LoggerLevel> loggerLevelList = JSONArray.parseArray(json, LoggerLevel.class);
                    if (CollectionUtils.isEmpty(loggerLevelList)) {
                        return;
                    }
                    String loggerFactoryClassStr = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
                    System.out.println("loggerFactoryClassStr >>>> " + loggerFactoryClassStr);
                    LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
                    org.apache.logging.log4j.core.config.Configuration config = ctx.getConfiguration();
                    for (LoggerLevel loggerLevel : loggerLevelList) {
                        LoggerConfig loggerConfig = config.getLoggerConfig(loggerLevel.getName());
                        loggerConfig.setLevel(Level.getLevel(loggerLevel.getLevel()));
                    }
                    ctx.updateLoggers();
                    log.debug("当前可以打印日志的等级为：debug");
                    log.info("当前可以打印日志的等级为：info");
                    log.error("当前可以打印日志的等级为：error");
                    log.trace("当前可以打印日志的等级为：trace");
                    log.warn("当前可以打印日志的等级为：warn");
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
