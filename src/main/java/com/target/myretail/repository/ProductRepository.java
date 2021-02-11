package com.target.myretail.repository;

import com.target.myretail.model.ProductItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductItem, Integer> {
}
