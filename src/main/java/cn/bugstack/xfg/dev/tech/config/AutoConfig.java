package cn.bugstack.xfg.dev.tech.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableConfigurationProperties(AutoConfigProperties.class)
public class AutoConfig {

    @Bean("redisson01")
    // 当 Spring 应用上下文中不存在某个特定类型的 Bean 时，才会创建和配置标注了 @ConditionalOnMissingBean 的 Bean 对象
    @ConditionalOnMissingBean
    public String redisson01() {
        return "模拟的 Redis 客户端 01";
    }

    @Bean("redisson02")
    // 当 Spring 应用上下文中不存在某个特定类型的 Bean 时，才会创建和配置标注了 @ConditionalOnMissingBean 的 Bean 对象
    @ConditionalOnMissingBean
    public String redisson02() {
        return "模拟的 Redis 客户端 02";
    }

    @Bean
    @Conditional(BeanCreateCondition.class)
    public List<String> whitelistedUsers() {
        return new ArrayList<String>() {{
            add("user001");
            add("user002");
            add("user003");
        }};
    }

    @Bean
    @ConditionalOnProperty(value = "sdk.config.enabled", havingValue = "true", matchIfMissing = false)
    public String createTopic(@Qualifier("redisson01") String redisson, AutoConfigProperties properties) {
        log.info("redisson {} {} {}", redisson, properties.getApiHost(), properties.getApiSecretKey());
        return redisson;
    }

}
