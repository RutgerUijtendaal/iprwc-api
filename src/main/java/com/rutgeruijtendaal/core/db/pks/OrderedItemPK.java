package com.rutgeruijtendaal.core.db.pks;

import java.io.Serializable;
import java.util.Objects;

public class OrderedItemPK implements Serializable {

    protected int productId;
    protected int orderId;

    public OrderedItemPK() {

    }

    public OrderedItemPK(int productId, int orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItemPK that = (OrderedItemPK) o;
        return productId == that.productId &&
                orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
