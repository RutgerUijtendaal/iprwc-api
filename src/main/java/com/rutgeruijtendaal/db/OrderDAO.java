package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.db.entities.Order;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO extends BaseDAO<Order> {

    public OrderDAO(SessionFactory factory) {
        super(factory);
    }

    public Order create(Order order) { return persist(order); }

    public Order update(Order order) { return (Order) currentSession().merge(order); }

    public List<Order> getOrdersByUser(AuthUser authUser) {
        Query query = currentSession().createQuery("from Order where user_id = :userId");
        query.setParameter("userId", authUser.getId());
        return query.list();
    }


}
