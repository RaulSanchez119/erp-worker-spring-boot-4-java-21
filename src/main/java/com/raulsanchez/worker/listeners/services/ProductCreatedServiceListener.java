package com.raulsanchez.worker.listeners.services;

import com.raulsanchez.worker.commons.mongo.documents.ProductInCatalogDocument;
import com.raulsanchez.worker.commons.mongo.repositories.ProductInCatalogRepository;
import com.raulsanchez.worker.listeners.configs.RabbitMQConfig;
import com.raulsanchez.worker.listeners.dtos.ProductCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCreatedServiceListener {

    private final ProductInCatalogRepository repository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void onProductCreated(ProductCreatedMessage message) {

        log.info("Received message ProductCreated with SKU: {}", message.sku());

        ProductInCatalogDocument document = ProductInCatalogDocument.builder()
                .id(message.productId())
                .sku(message.sku())
                .name(message.name())
                .price(message.price())
                .currency(message.currency())
                .description(message.description())
                .stock(message.stock())
                .categoryId(message.categoryId())
                .imageUrl(message.imageUrl())
                .active(message.active())
                .createdAt(message.timestamp())
                .build();

        this.repository.save(document);

        log.info("Product created successfully with SKU: {}", message.sku());
    }
}