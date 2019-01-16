package com.rutgeruijtendaal.resources;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.CartItemJson;
import com.rutgeruijtendaal.core.db.entities.CartItem;
import com.rutgeruijtendaal.core.db.entities.Product;
import com.rutgeruijtendaal.service.CartService;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {

    private CartService cartService;

    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }


    @GET
    @UnitOfWork
    public List<CartItem> getCartItems(@Auth AuthUser authUser) {
        return cartService.getCartItems(authUser);
    }

    @PUT
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public CartItem addItem(@Auth AuthUser authUser, Product product) {
        return cartService.addCartItem(authUser, product);
    }

    @DELETE
    @UnitOfWork
    @Path("/{productId}")
    public void removeItem(@Auth AuthUser authUser, @PathParam("productId") IntParam productId) {
        cartService.removeCartItem(authUser, productId.get());
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public CartItem updateItem(@Auth AuthUser authUser, CartItemJson cartItemJson) {
        return cartService.updateCartItem(authUser, cartItemJson);
    }

}
