package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.CartItemJson;
import com.rutgeruijtendaal.core.db.entities.Cart;
import com.rutgeruijtendaal.core.db.entities.CartItem;
import com.rutgeruijtendaal.core.db.entities.Product;
import com.rutgeruijtendaal.db.CartDAO;
import com.rutgeruijtendaal.db.CartItemDAO;
import com.rutgeruijtendaal.db.ProductDAO;

import java.util.List;

public class CartService {

    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;
    private ProductDAO productDAO;

    public CartService(CartDAO cartDAO, CartItemDAO cartItemDAO, ProductDAO productDAO) {
        this.cartDAO = cartDAO;
        this.cartItemDAO = cartItemDAO;
        this.productDAO = productDAO;
    }

    public List<CartItem> getCartItems(AuthUser authUser) {
        Cart cart = getActiveCart(authUser);
        return cartItemDAO.getCartItems(cart.getCartId());
    }

    public CartItem addCartItem(AuthUser authUser, Product product) {
        return cartItemDAO.create(buildCartItemFromProduct(authUser, product));
    }

    public CartItem removeCartItem(AuthUser authUser, CartItemJson cartItemJson) {
        return cartItemDAO.remove(buildCartItemFromItemJson(authUser, cartItemJson));
    }

    public CartItem updateCartItem(AuthUser authUser, CartItemJson cartItemJson) {
        return cartItemDAO.update(buildCartItemFromItemJson(authUser, cartItemJson));
    }

    private CartItem buildCartItemFromProduct(AuthUser authUser, Product product) {
        Cart cart = getActiveCart(authUser);

        CartItem cartItem = new CartItem(
                product.getProductId(),
                1,
                cart.getCartId()
        );

        return cartItem;
    }

    private CartItem buildCartItemFromItemJson(AuthUser authUser, CartItemJson cartItemJson) {
        Cart cart = getActiveCart(authUser);

        CartItem cartItem = new CartItem(
            cartItemJson.product.getProductId(),
            cartItemJson.itemCount,
            cart.getCartId()
        );

        return cartItem;
    }

    private Cart getActiveCart(AuthUser authUser) {
        return cartDAO.getActiveCart(authUser.getId());
    }

}
