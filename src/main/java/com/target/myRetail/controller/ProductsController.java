package com.target.myRetail.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.myRetail.Exceptions.DefaultException;
import com.target.myRetail.model.Products;
import com.target.myRetail.model.Status;
import com.target.myRetail.service.ProductService;


	/**
	 * Created by Sridhar K - 04/21/2018.
	 */
	@RestController
	public class ProductsController {
	    private ProductService productService;
	    private final Logger log = Logger.getLogger(ProductsController.class.getName());

	    public ProductsController() {
	    }

	    @Autowired
	    public ProductsController(ProductService productService) {
	        this.productService = productService;
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Products getProductById(@PathVariable("productId") int productId) throws DefaultException {
	    	log.info("Received GET request for product id:"+ productId);
	        return this.productService.findProductById(productId);
	    }

	    @RequestMapping(method = RequestMethod.PUT, value = "products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Status> updateProductById(@RequestBody Products product, @PathVariable("productId") int productId) throws DefaultException {
	    	log.info("Received PUT request for product id:"+ productId);
	       	productService.updateProduct(product,productId);
	        return new ResponseEntity<>(new Status(0,"Successfully updated"), HttpStatus.OK);
	    }

}
