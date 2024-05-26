package cn.bugstack.xfg.dev.tech.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringBeanTest {

    public SpringBeanTest() {
        log.info("我是通过 Spring 配置文件实例化的 Bean 对象");
    }

}
