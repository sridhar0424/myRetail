package com.target.myRetail.service;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.target.myRetail.Exceptions.DefaultException;
import com.target.myRetail.entity.ProductPrice;
import com.target.myRetail.entity.ProductsRepository;
import com.target.myRetail.model.Products;

/**
 * Created by: Sridhar K - 04/21/2018.
 * 
 */
@Component
public class ProductService {
    private ProductInfoService productInfoService;
    private ProductsRepository productsRepository;
    private final Logger log = Logger.getLogger(ProductService.class.getName());

    @Autowired
    public ProductService(ProductInfoService productInfoService, ProductsRepository productsRepository) {
        this.productInfoService = productInfoService;
        this.productsRepository = productsRepository;
    }

    public Products findProductById(int productId) throws DefaultException {

        //get product price details from mongoDB
    	ProductPrice productPrice = getProduct(productId);

      //get product name from rest service
      String productName = this.productInfoService.findProductInfoById(productId);
      log.info("Product name from external service:"+productName);
      
        //prepare the model object and return
        Products products = convertEntityToModel(productPrice);
        products.setName(productName);

        return products;
    }
  
    public void updateProduct(Products changedProduct, int productId) throws DefaultException {
    	//Validate if the inputs are valid
    	if(changedProduct == null || productId != changedProduct.getProductId() || (changedProduct.getCurrentPrice() == null)){
            throw new DefaultException(HttpStatus.BAD_REQUEST,"Invalid request");
        }
    	ProductPrice productPrice = getProduct(productId);

        //update with the values in request
        	if(changedProduct.getCurrentPrice().getValue() != null)
        		productPrice.getCurrentPrice().setValue(changedProduct.getCurrentPrice().getValue());
        	if(changedProduct.getCurrentPrice().getCurrency_code() != null)
        		productPrice.getCurrentPrice().setCurrency_code(changedProduct.getCurrentPrice().getCurrency_code());
    	//save to MongoDB
        ProductPrice updatedProduct = this.productsRepository.save(productPrice);
        log.info("Product updated completed for id:"+updatedProduct.getProductId());
    }

    private ProductPrice getProduct(int productId) throws DefaultException {
    	log.info("Retrieve product using product id:"+productId);
        ProductPrice productPrice = this.productsRepository.findByProductId(productId);
        if(productPrice==null){
            throw new DefaultException(HttpStatus.NOT_FOUND,"Record not found in database");
        }
        log.info("Retrieved product using product id:"+productPrice.getProductId());
        return productPrice;
    }
    private Products convertEntityToModel(ProductPrice productPrice) {
        Products products;
        ModelMapper mapper = new ModelMapper();
        products = mapper.map(productPrice, Products.class);
        return products;
    }

}
