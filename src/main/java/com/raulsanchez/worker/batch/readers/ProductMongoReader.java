package com.raulsanchez.worker.batch.readers;

import com.raulsanchez.worker.commons.mongo.documents.ProductInCatalogDocument;
import com.raulsanchez.worker.commons.mongo.repositories.ProductInCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.batch.infrastructure.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ProductMongoReader {

    private final ProductInCatalogRepository repository;

    @Bean
    public RepositoryItemReader<ProductInCatalogDocument> productReader() {

        return new RepositoryItemReaderBuilder<ProductInCatalogDocument>()
                .name("catalogMongoReader")
                .repository(repository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .pageSize(10)
                .build();
    }
}