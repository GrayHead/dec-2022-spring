package ua.com.owu.dec2022spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Async
public class SchedulerConfig {

    @Scheduled(cron = "****12*")

    public void someStuff() {
        System.out.println("some job");
    }
}
