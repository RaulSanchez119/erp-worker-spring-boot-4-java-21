package com.raulsanchez.worker.commons.mongo.repositories;

import com.raulsanchez.worker.commons.mongo.documents.ProductInCatalogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductInCatalogRepository extends MongoRepository<ProductInCatalogDocument, String> {

}