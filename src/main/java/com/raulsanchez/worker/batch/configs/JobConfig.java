package com.raulsanchez.worker.batch.configs;

import com.raulsanchez.worker.batch.dtos.CatalogCacheDto;
import com.raulsanchez.worker.batch.dtos.ProductCacheDto;
import com.raulsanchez.worker.batch.mongo.documents.CatalogDocument;
import com.raulsanchez.worker.batch.mongo.documents.ProductInCatalogDocument;
import com.raulsanchez.worker.batch.processors.CatalogCacheProcessor;
import com.raulsanchez.worker.batch.processors.ProductCacheProcessor;
import com.raulsanchez.worker.batch.readers.CatalogMongoReader;
import com.raulsanchez.worker.batch.readers.ProductMongoReader;
import com.raulsanchez.worker.batch.writers.CatalogRedisWriter;
import com.raulsanchez.worker.batch.writers.ProductRedisWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final ProductMongoReader productMongoReader;
    private final CatalogMongoReader catalogMongoReader;

    private final ProductCacheProcessor productCacheProcessor;
    private final CatalogCacheProcessor catalogCacheProcessor;

    private final ProductRedisWriter productRedisWriter;
    private final CatalogRedisWriter catalogRedisWriter;

    @Bean
    public Job cacheWarmupJob(JobRepository jobRepository) {

        return new JobBuilder("cacheWarmupJob", jobRepository)
                .start(warmupProductStep(jobRepository))
                .next(warmupCatalogStep(jobRepository))
                .build();
    }

    @Bean
    public Step warmupProductStep(JobRepository jobRepository) {

        return new StepBuilder("warmupProductStep", jobRepository)
                .<ProductInCatalogDocument, ProductCacheDto>chunk(10)
                .transactionManager(new ResourcelessTransactionManager())
                .reader(this.productMongoReader.productReader())
                .processor(this.productCacheProcessor)
                .writer(this.productRedisWriter)
                .build();
    }

    @Bean
    public Step warmupCatalogStep(JobRepository jobRepository) {

        return new StepBuilder("warmupCatalogStep", jobRepository)
                .<CatalogDocument, CatalogCacheDto>chunk(10)
                .transactionManager(new ResourcelessTransactionManager())
                .reader(this.catalogMongoReader.catalogReader())
                .processor(this.catalogCacheProcessor)
                .writer(this.catalogRedisWriter)
                .build();
    }
}