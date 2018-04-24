package com.target.myRetail.entity;

/**
 * Created by Sridhar K - 04/21/2018.
 */
public class CurrentPrice {

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
		return "CurrentPrice [value=" + value + ", currencyCode=" + currency_code + "]";
	}


}
