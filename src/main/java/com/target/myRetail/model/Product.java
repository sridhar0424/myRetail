package com.target.myRetail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sridhar K - 04/21/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Product {
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

