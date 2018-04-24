package com.target.myRetail.model;

/**
 * Created by Sridhar K - 04/23/2018.
 */
public class ProductsPrice {

    private Double value;
    private String currency_code;
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	@Override
	public String toString() {
		return "ProductsPrice [value=" + value + ", currency_code=" + currency_code + "]";
	}
    
}
