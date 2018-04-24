package com.target.myRetail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sridhar K - 04/21/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}