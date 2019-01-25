package com.rutgeruijtendaal.core.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int orderId;

    @Column(name="order_date_time")
    private Timestamp orderDateTime;

    @Column(name="user_id")
    private int userId;

    @Column(name="order_status_id")
    private int orderStatusId;

    @Column(name="payment_type_id")
    private int paymentTypeId;

    @Column(name="delivery_status_id")
    private int deliveryStatusId;

    public Order() {

    }

    public Order(Timestamp orderDateTime, int userId, int orderStatusId, int paymentTypeId, int deliveryStatusId) {
        this.orderDateTime = orderDateTime;
        this.userId = userId;
        this.orderStatusId = orderStatusId;
        this.paymentTypeId = paymentTypeId;
        this.deliveryStatusId = deliveryStatusId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Timestamp orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public int getDeliveryStatusId() {
        return deliveryStatusId;
    }

    public void setDeliveryStatusId(int deliveryStatusId) {
        this.deliveryStatusId = deliveryStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                userId == order.userId;

    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId);
    }
}
