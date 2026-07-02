package com.raulsanchez.worker.batch.mongo.repositories;

import com.raulsanchez.worker.batch.mongo.documents.CatalogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CatalogRepository extends MongoRepository<CatalogDocument, String> {

}
