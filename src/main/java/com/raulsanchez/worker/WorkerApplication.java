package com.raulsanchez.worker;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@EnableJdbcJobRepository
public class WorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
	}

}
