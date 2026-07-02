package com.raulsanchez.worker.batch.writers;

import com.raulsanchez.worker.batch.dtos.ProductCacheDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.raulsanchez.worker.batch.utils.RedisKeys.*;

@Component
@RequiredArgsConstructor
public class ProductRedisWriter implements
        ItemWriter<ProductCacheDto> {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void write(Chunk<? extends ProductCacheDto> chunk) throws Exception {

        chunk.getItems().forEach(product -> {

            this.redisTemplate.opsForValue().set(
                    PRODUCTS_BY_ID + product.id(),
                    product,
                    TTL_HOURS, TimeUnit.HOURS
            );

            this.redisTemplate.opsForValue().set(
                    PRODUCTS_BY_SKU + product.sku(),
                    product,
                    TTL_HOURS, TimeUnit.HOURS
            );

            this.redisTemplate.opsForList().leftPush(
                    PRODUCTS_BY_CATEGORY + product.categoryId(),
                    product
            );

            this.redisTemplate.expire(
                    PRODUCTS_BY_CATEGORY + product.categoryId(),
                    TTL_HOURS, TimeUnit.HOURS
            );

            if (product.active()) {

                this.redisTemplate.opsForList().leftPush(
                        PRODUCTS_ACTIVE,
                        product
                );

                this.redisTemplate.expire(
                        PRODUCTS_ACTIVE,
                        TTL_HOURS, TimeUnit.HOURS
                );
            }

        });
    }
}