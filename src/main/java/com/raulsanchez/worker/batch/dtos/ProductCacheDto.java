package com.raulsanchez.worker.batch.dtos;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record ProductCacheDto(
        String id,
        String sku,
        String name,
        String description,
        Double price,
        String money,
        Integer stock,
        String imageUrl,
        Boolean active,
        String categoryId,
        List<String> tags,
        Map<String, Object> specifications
) {
}