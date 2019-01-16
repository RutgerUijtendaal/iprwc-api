package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.CartItemJson;
import com.rutgeruijtendaal.core.db.entities.Cart;
import com.rutgeruijtendaal.core.db.entities.CartItem;
import com.rutgeruijtendaal.core.db.entities.Product;
import com.rutgeruijtendaal.db.CartDAO;
import com.rutgeruijtendaal.db.CartItemDAO;
import com.rutgeruijtendaal.db.ProductDAO;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

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
        Cart cart = getActiveCart(authUser);
        List<CartItem> cartItems = cartItemDAO.getCartItems(cart.getCartId());

        // If the item is already in the cart increment the count by 1 instead
        for(CartItem cartItem: cartItems) {
            if(product.getProductId() == cartItem.getProductId()) {
                cartItem.setItemCount(cartItem.getItemCount() + 1);
                return cartItemDAO.update(cartItem);
            }
        }

        return cartItemDAO.create(buildNewCartItemFromProduct(authUser, product));
    }

    public void removeCartItem(AuthUser authUser, int productId) {
        Optional<Product> product = productDAO.findById(productId);
        if(product.isPresent()) {
            cartItemDAO.remove(buildNewCartItemFromProduct(authUser, product.get()));
        } else {
            throw new NotFoundException("No such product found");
        }
    }

    public CartItem updateCartItem(AuthUser authUser, CartItemJson cartItemJson) {
        return cartItemDAO.update(buildCartItemFromItemJson(authUser, cartItemJson));
    }

    private CartItem buildNewCartItemFromProduct(AuthUser authUser, Product product) {
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
            cartItemJson.productId,
            cartItemJson.itemCount,
            cart.getCartId()
        );

        return cartItem;
    }

    private Cart getActiveCart(AuthUser authUser) {
        return cartDAO.getActiveCart(authUser.getId());
    }

}
