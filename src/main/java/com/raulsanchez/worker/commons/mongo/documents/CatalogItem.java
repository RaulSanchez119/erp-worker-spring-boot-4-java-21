package com.raulsanchez.worker.commons.mongo.documents;


public record CatalogItem(
        String id,
        String code,
        String value,
        String description,
        Integer displayOrder,
        CatalogItemMetadata metadata
) {}