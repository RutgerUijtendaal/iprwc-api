package com.rutgeruijtendaal.core.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemJson {

    @JsonCreator
    public CartItemJson(
            @JsonProperty("productId") int productId,
            @JsonProperty("itemCount") int itemCount
    ) {
        this.productId = productId;
        this.itemCount = itemCount;
    }

    @JsonProperty("productId")
    public int productId;

    @JsonProperty("itemCount")
    public int itemCount;

}
