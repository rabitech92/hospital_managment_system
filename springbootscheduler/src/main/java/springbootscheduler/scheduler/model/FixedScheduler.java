package springbootscheduler.scheduler.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FixedScheduler {
    @Scheduled(fixedRate = 5000)
    public void task(){
        log.info("Scheduler one "+ System.currentTimeMillis()/60);
    }
}
