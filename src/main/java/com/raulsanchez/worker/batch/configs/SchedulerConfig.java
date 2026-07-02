package com.raulsanchez.worker.batch.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameter;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {

    private final JobOperator jobOperator;
    private final Job cacheWarmupJob;

    @Scheduled(cron = "0 0 5 * * *")
    public void startJob() {
        try {
            log.info("Starting cache warmup job");

            var id = this.jobOperator.start(
                    this.cacheWarmupJob,
                    new JobParameters(
                            Set.of(
                                    new JobParameter<>(
                                            "timestamp",
                                            LocalDateTime.now().toString(),
                                            String.class,
                                            true
                                    )
                            )
                    )
            );

            log.info("Cache warmup job executed with is {}", id);
        } catch (Exception e) {
            log.error("Error on job scheduler", e);
        }
    }

}