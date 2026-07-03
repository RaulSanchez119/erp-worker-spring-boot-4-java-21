package com.raulsanchez.worker.batch.processors;

import com.raulsanchez.worker.batch.dtos.ProductCacheDto;
import com.raulsanchez.worker.commons.mongo.documents.ProductInCatalogDocument;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductCacheProcessor implements
        ItemProcessor<ProductInCatalogDocument, ProductCacheDto> {


    @Override
    public @Nullable ProductCacheDto process(ProductInCatalogDocument document) throws Exception {
        return ProductCacheDto.builder()
                .id(document.getId())
                .sku(document.getSku())
                .name(document.getName())
                .description(document.getDescription())
                .price(document.getPrice().doubleValue())
                .money(document.getCurrency())
                .stock(document.getStock())
                .imageUrl(document.getImageUrl())
                .categoryId(document.getCategoryId())
                .active(document.isActive())
                .tags(document.getTags())
                .specifications(document.getSpecifications())
                .build();
    }
}