package com.raulsanchez.worker.batch.processors;

import com.raulsanchez.worker.batch.dtos.CatalogCacheDto;
import com.raulsanchez.worker.batch.dtos.ItemsCacheDto;
import com.raulsanchez.worker.batch.mongo.documents.CatalogDocument;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CatalogCacheProcessor
        implements ItemProcessor<CatalogDocument, CatalogCacheDto> {

    @Override
    public CatalogCacheDto process(CatalogDocument document) throws Exception {
        return CatalogCacheDto.builder()
                .id(document.getId())
                .type(document.getCatalogType())
                .name(document.getName())
                .description(document.getDescription())
                .active(document.isActive())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .items(document.getItems()
                        .stream()
                        .map(item -> ItemsCacheDto.builder()
                                .code(item.code())
                                .value(item.value())
                                .description(item.description())
                                .displayOrder(item.displayOrder())
                                .build())
                        .toList())
                .build();
    }

}