package com.target.myRetail.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Sridhar K - 04/21/2018.
 */
@Repository
public interface ProductsRepository extends MongoRepository<ProductPrice, Integer> {
	public ProductPrice findByProductId(@Param("productId") int productId);
	public ProductPrice save(ProductPrice productPrice);
}
