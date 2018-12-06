package com.rutgeruijtendaal.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="order_status")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.rutgeruijtendaal.core.OrderStatus.getAll",
                        query = "SELECT p FROM OrderStatus p"
                )
        })
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY lets the DB AI the key
    private int order_status_id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    public OrderStatus() {

    }

    public OrderStatus(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return order_status_id;
    }

    public void setId(int order_status_id) {
        this.order_status_id = order_status_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if(!(o instanceof OrderStatus)) {
            return false;
        }

        final OrderStatus that = (OrderStatus) o;

        return Objects.equals(this.order_status_id, that.order_status_id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_status_id, name, description);
    }
}
