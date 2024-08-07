package springbootscheduler.scheduler.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FixedRateScheduler {

    @Scheduled(fixedRate = 5000)
    public void task(){

        log.info("B Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}
