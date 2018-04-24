package com.target.myRetail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sridhar K - 04/21/2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Product_description {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}