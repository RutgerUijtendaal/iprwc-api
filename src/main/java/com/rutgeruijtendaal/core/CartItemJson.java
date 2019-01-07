package com.rutgeruijtendaal.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemJson {

    public CartItemJson(int productId, int itemCount) {
        this.productId = productId;
        this.itemCount = itemCount;
    }

    @JsonProperty("productId")
    public int productId;

    @JsonProperty("itemCount")
    public int itemCount;

}
