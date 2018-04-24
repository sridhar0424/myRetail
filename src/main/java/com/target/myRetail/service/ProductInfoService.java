package com.target.myRetail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.target.myRetail.Exceptions.DefaultException;
import com.target.myRetail.client.ProductInfoClient;
import com.target.myRetail.controller.ProductsController;
import com.target.myRetail.model.ProductInfo;

/**
 * Created by: Sridhar K - 04/23/2018.
 * 
 */
@Component
public class ProductInfoService {
    private ProductInfoClient productInfoClient;
    private final Logger log = Logger.getLogger(ProductsController.class.getName());
    @Autowired
    public ProductInfoService(ProductInfoClient productInfoClient){
        this.productInfoClient = productInfoClient;
    }
    public String findProductInfoById(int productId) throws DefaultException {
        String productTitle = null;
        ProductInfo productInfo;
        try {
             productInfo = this.productInfoClient.getProductInfo(productId);
             productTitle= productInfo.getProduct().getItem().getProduct_description().getTitle();
             log.info("Product title from rest client:"+productTitle);
        }catch (HttpClientErrorException ex){
           //throw new DefaultException(ex.getStatusCode(), ex.getMessage());
        } catch (RestClientException  | NullPointerException ex){
            if(ex.getMessage()!=null) {
                throw new DefaultException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
            } else{
                throw new DefaultException(HttpStatus.INTERNAL_SERVER_ERROR,"Error at ProductInfoService->findProductInfoById->RestClientException|Nullpointer");
            }
        }
       return productTitle;
    }

}
