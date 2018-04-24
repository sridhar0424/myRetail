package com.target.myRetail.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sridhar K - 04/21/2018.
 */
@Document(collection = "Product")
public class ProductPrice {
    
	@Id
    private String id;
    private int productId;
    private CurrentPrice CurrentPrice;

    public ProductPrice(){

    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int id) {
        this.productId = id;
    }

    public CurrentPrice getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(CurrentPrice currentPrice) {
        this.CurrentPrice = currentPrice;
    }
	@Override
	public String toString() {
		return "ProductPrice [id=" + id + ", productId=" + productId + ", CurrentPrice=" + CurrentPrice.toString() + "]";
	}


}
