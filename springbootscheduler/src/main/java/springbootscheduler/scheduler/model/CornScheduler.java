package springbootscheduler.scheduler.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CornScheduler {


    @Scheduled(cron = "*/5 * * * * ?")
    public void task(){
        log.info("Hello corn - " + System.currentTimeMillis() / 1000);
    }
}
