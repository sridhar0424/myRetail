package com.target.myRetail.client;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.target.myRetail.controller.ProductsController;
import com.target.myRetail.model.ProductInfo;

/**
 * Created by Sridhar K - 04/23/2018.
 */
@Component
public class ProductInfoClient {
	private final Logger log = Logger.getLogger(ProductsController.class.getName());
    public ProductInfo getProductInfo(int productId){
         RestTemplate restTemplate = new RestTemplate();
         String productStr = String.valueOf(productId);
         log.info("Call external service using product id:"+productStr);
         return restTemplate.getForObject("https://redsky.target.com/v2/pdp/tcin/{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics,available_to_promise_network", ProductInfo.class, productStr);
         
       }
}
