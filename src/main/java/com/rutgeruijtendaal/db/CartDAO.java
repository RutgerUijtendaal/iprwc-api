package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.Cart;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CartDAO extends BaseDAO<Cart> {

    public CartDAO(SessionFactory factory) {
        super(factory);
    }

    public Cart create(Cart cart) {
        return persist(cart);
    }

    public Cart update(Cart cart) {
        return (Cart) currentSession().merge(cart);
    }

    public Cart getActiveCart(int userId) {
        Query query = currentSession().createQuery("from Cart where user_id = :user_id AND is_ordered = false");
        query.setParameter("user_id", userId);

        // Get the currently active cart
        if(query.list().size() > 0) {
            Cart cart = (Cart) query.list().get(0);
            return cart;
        } else {
            // If non can be found create a new cart for the user
            Cart cart = new Cart(userId);
            cart = create(cart);
            return cart;
        }
    }
}
