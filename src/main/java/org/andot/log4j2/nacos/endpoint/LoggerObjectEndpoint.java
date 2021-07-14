package org.andot.log4j2.nacos.endpoint;

import org.andot.log4j2.nacos.properties.LoggerLevel;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 获取当前项目所有的日志对象名称和等级
 * 如果没有配置nacos 址
 * @author lucas
 */
@ConditionalOnProperty("log4j2-nacos.config.server-addr")
@RestController
@RequestMapping("/logger")
public class LoggerObjectEndpoint {

    /**
     * 获取到全部日志对象和等级转换后返回
     * @return
     */
    @GetMapping("/names")
    public List<LoggerLevel> getObjNames () {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Map<String, LoggerConfig> loggerConfigMap = context.getConfiguration().getLoggers();
        if (MapUtils.isEmpty(loggerConfigMap)) {
            return Collections.emptyList();
        }
        return loggerConfigMap.values().stream().map(item->
                LoggerLevel.builder().name(item.getName()).level(item.getLevel().name()).build())
                .collect(Collectors.toList());
    }
}
