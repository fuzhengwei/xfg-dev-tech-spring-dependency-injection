package cn.bugstack.xfg.dev.tech.domain.impl;

import cn.bugstack.xfg.dev.tech.domain.IAwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * OpenAI 可用的对话额度
 */
@Slf4j
@Service("openai_model")
// Primary 首选 Bean 对象标记
@Primary
@Order(1)
public class OpenAIModelAwardService implements IAwardService {

    @Override
    public void doDistributeAward(String userId) {
        log.info("发奖服务，OpenAI 模型奖励 {}", userId);
    }

}
