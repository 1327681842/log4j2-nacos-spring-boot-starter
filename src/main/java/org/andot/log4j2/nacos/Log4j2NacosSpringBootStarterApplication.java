package org.andot.log4j2.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 * @author lucas
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Log4j2NacosSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Log4j2NacosSpringBootStarterApplication.class, args);
    }

}
