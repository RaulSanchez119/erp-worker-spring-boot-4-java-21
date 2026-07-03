package com.raulsanchez.worker.batch.readers;

import com.raulsanchez.worker.commons.mongo.documents.CatalogDocument;
import com.raulsanchez.worker.commons.mongo.repositories.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.batch.infrastructure.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CatalogMongoReader {

    private final CatalogRepository repository;

    @Bean
    public RepositoryItemReader<CatalogDocument> catalogReader() {
        return new RepositoryItemReaderBuilder<CatalogDocument>()
                .name("catalogMongoReader")
                .repository(repository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .pageSize(10)
                .build();
    }
}