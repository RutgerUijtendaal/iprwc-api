package com.rutgeruijtendaal.core.db.pks;

import java.io.Serializable;
import java.util.Objects;


public class CartItemPK implements Serializable {

    protected int productId;
    protected int cartId;

    public CartItemPK() {

    }

    public CartItemPK(int productId, int cartId) {
        this.productId = productId;
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
        CartItemPK that = (CartItemPK) o;
        return productId == that.productId &&
                cartId == that.cartId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, cartId);
    }
}
