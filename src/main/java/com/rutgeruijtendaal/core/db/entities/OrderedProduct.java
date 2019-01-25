package com.rutgeruijtendaal.core.db.entities;

import com.rutgeruijtendaal.core.db.pks.OrderedItemPK;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="ordered_products")
@IdClass(OrderedItemPK.class)
public class OrderedProduct {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Id
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "paid_price")
    private int paidPrice;

    @Column(name = "item_count")
    private int itemCount;

    public OrderedProduct() {

    }

    public OrderedProduct(int productId, int orderId, int paidPrice, int itemCount) {
        this.productId = productId;
        this.orderId = orderId;
        this.paidPrice = paidPrice;
        this.itemCount = itemCount;
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

    public int getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(int paidPrice) {
        this.paidPrice = paidPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return productId == that.productId &&
                orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
