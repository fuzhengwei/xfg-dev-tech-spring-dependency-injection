package cn.bugstack.xfg.dev.tech.domain.impl;

import cn.bugstack.xfg.dev.tech.domain.IAwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 用户积分奖品
 */
@Slf4j
@Service("user_credit_random")
@Order(3)
public class UserCreditRandomAwardService implements IAwardService {

    @Override
    public void doDistributeAward(String userId) {
        log.info("发奖服务，用户积分奖励 {}", userId);
    }

}
