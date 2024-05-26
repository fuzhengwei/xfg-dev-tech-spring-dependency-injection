package cn.bugstack.xfg.dev.tech.trigger.http;

import cn.bugstack.xfg.dev.tech.domain.IAwardService;
import cn.bugstack.xfg.dev.tech.types.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController()
@CrossOrigin(origins = "*")
@DependsOn({"openai_model", "openai_use_count", "user_credit_random"})
public class AwardController {

    private final List<IAwardService> awardServices;
    private final Map<String, IAwardService> awardServiceMap;

    public AwardController(List<IAwardService> awardServices, Map<String, IAwardService> awardServiceMap) {
        this.awardServices = awardServices;
        this.awardServiceMap = awardServiceMap;
    }

    public Response<String> distributeAward(@RequestParam String userId, @RequestParam String awardKey) {
        try {
            log.info("发放奖品服务 userId:{} awardKey:{}", userId, awardKey);
            awardServiceMap.get(awardKey);
            return Response.<String>builder()
                    .code("0000")
                    .info("调用成功")
                    .data("发奖完成")
                    .build();
        } catch (Exception e) {
            return Response.<String>builder()
                    .code("0001")
                    .info("调用失败")
                    .build();
        }
    }

    @PostConstruct
    public void init() {
        log.info("对象初始化完成 {}", this);
    }

    @PreDestroy
    public void destroy() {
        log.info("对象销毁完成 {}", this);
    }

}
