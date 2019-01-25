package com.rutgeruijtendaal.core.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderJson {

    @JsonCreator
    public OrderJson(
            @JsonProperty("paymentTypeId") int paymentTypeId
    ) {
        this.paymentTypeId = paymentTypeId;
    }

    @JsonProperty("paymentTypeId")
    public int paymentTypeId;

}
