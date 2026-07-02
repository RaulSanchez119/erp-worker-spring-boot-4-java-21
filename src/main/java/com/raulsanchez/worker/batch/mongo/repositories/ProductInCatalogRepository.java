package com.raulsanchez.worker.batch.mongo.repositories;

import com.raulsanchez.worker.batch.mongo.documents.ProductInCatalogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface ProductInCatalogRepository extends MongoRepository<ProductInCatalogDocument, String> {


}

