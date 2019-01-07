package com.rutgeruijtendaal.core.db.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.rutgeruijtendaal.core.db.pks.CartItemPK;
import com.rutgeruijtendaal.view.View;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="cart_item")
@IdClass(CartItemPK.class)
public class CartItem {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "item_count")
    private int itemCount;

    @Id
    @Column(name = "cart_id")
    @JsonView(View.Private.class)
    private int cartId;

    public CartItem() {

    }

    public CartItem(int productId, int itemCount, int cartId) {
        this.productId = productId;
        this.itemCount = itemCount;
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return productId == cartItem.productId &&
                cartId == cartItem.cartId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, cartId);
    }
}
