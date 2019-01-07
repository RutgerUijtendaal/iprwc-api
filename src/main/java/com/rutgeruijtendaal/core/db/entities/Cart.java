package com.rutgeruijtendaal.core.db.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "is_ordered")
    private boolean isOrdered;

    public Cart() {

    }

    public Cart(int userId) {
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId &&
                userId == cart.userId &&
                isOrdered == cart.isOrdered;
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartId, userId, isOrdered);
    }
}
