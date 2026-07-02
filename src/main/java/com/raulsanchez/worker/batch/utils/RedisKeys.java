package com.raulsanchez.worker.batch.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeys {
    // Products
    public static final String PRODUCTS_BY_ID = "products:byId::";
    public static final String PRODUCTS_BY_SKU = "products:bySku::";
    public static final String PRODUCTS_BY_CATEGORY = "products:byCategory::";
    public static final String PRODUCTS_ACTIVE = "products:active::all";

    // Catalogs
    public static final String CATALOGS_BY_TYPE = "catalogs:byType::";
    public static final String CATALOGS_ITEMS = "catalogs:items::";

    // TTL
    public static final long TTL_HOURS = 24L;
}