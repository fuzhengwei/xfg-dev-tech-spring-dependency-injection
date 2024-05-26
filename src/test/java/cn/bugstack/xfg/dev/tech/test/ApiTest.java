package cn.bugstack.xfg.dev.tech.test;

import cn.bugstack.xfg.dev.tech.domain.IAwardService;
import cn.bugstack.xfg.dev.tech.domain.impl.AliPayAwardService;
import cn.bugstack.xfg.dev.tech.domain.impl.NullAwardService;
import cn.bugstack.xfg.dev.tech.domain.impl.OpenAIModelAwardService;
import cn.bugstack.xfg.dev.tech.domain.impl.OpenAIUseCountAwardService;
import cn.bugstack.xfg.dev.tech.domain.rule.LogicChain;
import cn.bugstack.xfg.dev.tech.trigger.http.AwardController;
import cn.bugstack.xfg.dev.tech.types.Response;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Value("${config.userId}")
    private String userId;
    @Resource
    private AwardController awardController;
    @Resource
    private OpenAIModelAwardService openAIModelAwardService;
    @Autowired(required = true)
    private OpenAIUseCountAwardService openAIUseCountAwardService;
    @Autowired(required = false)
    private NullAwardService nullAwardService;
    @Autowired(required = false)
    private AliPayAwardService aliPayAwardService;
    @Resource
    private IAwardService awardService;
    @Autowired(required = false)
    @Qualifier("whitelistedUsers")
    private List<String> whitelistedUsers;
    @Autowired
    private Environment env;

    @Resource
    private ApplicationContext applicationContext;

    static {
        // BeanCreateCondition 会检测这个值，确定是否创建对象
        System.setProperty("isOpenWhitelistedUsers", "false");
    }

    @Test
    public void test_awardService_primary() {
        log.info("测试结果 {}", awardService.getClass());
    }

    @Test
    public void test_environment() {
        log.info("应用信息 {} {}", env.getProperty("app.name"), env.getProperty("app.version"));
    }

    @Test
    public void test_prototype() {
        log.info("测试结果: {}", applicationContext.getBean(LogicChain.class).hashCode());
        log.info("测试结果: {}", applicationContext.getBean(LogicChain.class).hashCode());
    }

    @Test
    public void test_condition() {
        // isOpenWhitelistedUsers 可以分别设置 true、false 验证，ture 的时候，会实例化
        log.info("测试结果: {}", JSON.toJSONString(whitelistedUsers));
    }

    @Test
    public void test_award() {
        Response<String> response = awardController.distributeAward("xiaofuge", "user_credit_random");
        log.info("测试结果: {}", JSON.toJSONString(response));
    }

    @Test
    public void test_async() throws InterruptedException {
        async();

        new CountDownLatch(1).await();
    }

    @Async
    private void async() {
        System.out.println("异步方法开始执行: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000); // 模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步方法执行完成: " + Thread.currentThread().getName());
    }

}
