package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.CartItem;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CartItemDAO extends BaseDAO<CartItem> {

    public CartItemDAO(SessionFactory factory) {
        super(factory);
    }

    public CartItem create(CartItem cartItem) {
        return persist(cartItem);
    }

    public CartItem remove(CartItem cartItem) {
        return remove(cartItem);
    }

    public CartItem update(CartItem cartItem) {
        return update(cartItem);
    }

    public List<CartItem> getCartItems(int cartId) {
        Query query = currentSession().createQuery("from CartItem where cart_id = :cart_id");
        query.setParameter("cart_id", cartId);
        return query.list();
    }
}
