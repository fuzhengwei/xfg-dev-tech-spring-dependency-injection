package cn.bugstack.xfg.dev.tech.trigger.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendAwardJob {

    @Scheduled(cron = "0/5 * * * * ?")
    public void inspection(){
        log.info("巡检任务");
    }

}
