package org.andot.log4j2.nacos.converter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.api.config.convert.NacosConfigConverter;
import lombok.extern.slf4j.Slf4j;
import org.andot.log4j2.nacos.properties.LoggerLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 * @author lucas
 */
@Slf4j
public class Log4jNacosConfigConverter implements NacosConfigConverter<List<LoggerLevel>> {
    @Override
    public boolean canConvert(Class targetType) {
        if (LoggerLevel.class.isInstance(targetType)) {
            return true;
        }
        return false;
    }

    @Override
    public List<LoggerLevel> convert(String config) {
        if (StringUtils.isEmpty(config)) {
            log.info("接受内容为空！");
            return null;
        }
        log.info("==============log4j config info====================");
        log.info(config);
        log.info("=================================================");
        List<LoggerLevel> loggerLevelList = JSONArray.parseArray(config, LoggerLevel.class);
        if (CollectionUtils.isEmpty(loggerLevelList)) {
            return null;
        }
        return loggerLevelList;
    }
}
