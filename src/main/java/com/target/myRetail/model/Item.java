package com.target.myRetail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sridhar K - 04/21/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Item {
    private Product_description product_description;

    public Product_description getProduct_description() {
        return product_description;
    }

    public void setProduct_description(Product_description product_description) {
        this.product_description = product_description;
    }
}
