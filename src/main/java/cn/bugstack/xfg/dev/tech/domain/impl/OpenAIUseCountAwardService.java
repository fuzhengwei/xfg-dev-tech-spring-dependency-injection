package cn.bugstack.xfg.dev.tech.domain.impl;

import cn.bugstack.xfg.dev.tech.domain.IAwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * OpenAI 用户对话额度奖励
 */
@Slf4j
@Service("openai_use_count")
@Order(2)
public class OpenAIUseCountAwardService implements IAwardService {

    @Override
    public void doDistributeAward(String userId) {
        log.info("发奖服务，OpenAI 用户额度奖励 {}", userId);
    }

}
