package com.target.myRetail.repository;

import com.target.myRetail.model.ProductItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductItem, Integer> {
}
