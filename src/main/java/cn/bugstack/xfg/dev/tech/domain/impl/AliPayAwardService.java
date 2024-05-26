package cn.bugstack.xfg.dev.tech.domain.impl;

import cn.bugstack.xfg.dev.tech.domain.IAwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
// 用于根据配置环境实例化 Bean 对象
@Profile({"prod", "test"})
@Lazy
public class AliPayAwardService implements IAwardService {

    public AliPayAwardService() {
        log.info("如一些支付场景，必须指定上线后才能实例化");
    }

    @Override
    public void doDistributeAward(String userId) {
        log.info("红包奖励 {}", userId);
    }

}
