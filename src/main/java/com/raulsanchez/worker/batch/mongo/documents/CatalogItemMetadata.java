package com.raulsanchez.worker.batch.mongo.documents;

import java.math.BigDecimal;
import java.util.List;

public record CatalogItemMetadata(
        String icon,
        String color,
        List<String> nextStatuses,
        BigDecimal fee
) {}