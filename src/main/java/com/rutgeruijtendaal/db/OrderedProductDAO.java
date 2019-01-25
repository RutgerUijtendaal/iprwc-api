package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.OrderedProduct;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class OrderedProductDAO extends BaseDAO<OrderedProduct> {

    public OrderedProductDAO(SessionFactory factory) {
        super(factory);
    }

    public OrderedProduct create(OrderedProduct orderedProduct) { return persist(orderedProduct); }

    public List<OrderedProduct> getOrderedProducts(int orderId) {
        Query query = currentSession().createQuery("from OrderedProduct where order_id = :orderId");
        query.setParameter("orderId", orderId);
        return query.list();
    }
}
