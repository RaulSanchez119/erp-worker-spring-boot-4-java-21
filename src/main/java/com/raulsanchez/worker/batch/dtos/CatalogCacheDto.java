package com.raulsanchez.worker.batch.dtos;

import com.raulsanchez.worker.commons.CatalogType;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record CatalogCacheDto(
        String id,
        boolean active,
        String name,
        String description,
        CatalogType type,
        Instant createdAt,
        Instant updatedAt,
        List<ItemsCacheDto> items
) {
}