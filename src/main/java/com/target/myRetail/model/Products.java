package com.target.myRetail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by Sridhar K on 04/21/2018.
 */
@JsonInclude(Include.NON_NULL)
public class Products {

    private int productId;
    private String name;
    private ProductsPrice productsPrice;

    public Products(){

    }
    public ProductsPrice getCurrentPrice() {
        return productsPrice;
    }

    public void setCurrentPrice(ProductsPrice productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int id) {
        this.productId = id;
    }
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", name=" + name + ", productsPrice=" + productsPrice.toString() + "]";
	}

}

