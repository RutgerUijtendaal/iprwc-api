package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.json.CartItemJson;
import com.rutgeruijtendaal.core.db.entities.Cart;
import com.rutgeruijtendaal.core.db.entities.CartItem;
import com.rutgeruijtendaal.core.db.entities.Product;
import com.rutgeruijtendaal.db.CartDAO;
import com.rutgeruijtendaal.db.CartItemDAO;
import com.rutgeruijtendaal.db.DaoManager;
import com.rutgeruijtendaal.db.ProductDAO;
import com.rutgeruijtendaal.exceptions.DropwizardException;

import java.util.List;
import java.util.Optional;

public class CartService {

    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;
    private ProductDAO productDAO;

    public CartService() {
        this.cartDAO = DaoManager.getInstance().getCartDAO();
        this.cartItemDAO = DaoManager.getInstance().getCartItemDAO();
        this.productDAO = DaoManager.getInstance().getProductDAO();
    }

    public List<CartItem> getCartItems(AuthUser authUser) throws DropwizardException {
        Cart cart = getActiveCart(authUser);

        if(cart == null) {
            throw new DropwizardException(404, "Cart not found");
        }

        return cartItemDAO.getCartItems(cart.getCartId());
    }

    public CartItem addCartItem(AuthUser authUser, Product product) throws DropwizardException {
        Cart cart = getActiveCart(authUser);

        if(cart == null) {
            throw new DropwizardException(404, "Cart not found");
        }

        List<CartItem> cartItems = cartItemDAO.getCartItems(cart.getCartId());

        if(cartItems == null) {
            throw new DropwizardException(404, "Cart Items not found");
        }

        // If the item is already in the cart increment the count by 1 instead
        for(CartItem cartItem: cartItems) {
            if(product.getProductId() == cartItem.getProductId()) {
                cartItem.setItemCount(cartItem.getItemCount() + 1);
                return cartItemDAO.update(cartItem);
            }
        }

        return cartItemDAO.create(buildNewCartItemFromProduct(authUser, product));
    }

    public void removeCartItem(AuthUser authUser, int productId) throws DropwizardException {
        Optional<Product> product = productDAO.findById(productId);

        if(product.isPresent()) {
            cartItemDAO.remove(buildNewCartItemFromProduct(authUser, product.get()));
        } else {
            throw new DropwizardException(404, "No such product found");
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
