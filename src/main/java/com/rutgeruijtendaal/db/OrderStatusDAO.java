package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.OrderStatus;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class OrderStatusDAO extends BaseDAO<OrderStatus> {

    public OrderStatusDAO(SessionFactory factory) {
        super(factory);
    }

    @SuppressWarnings("unchecked")
    public List<OrderStatus> getAll() {
        return list((Query<OrderStatus>) namedQuery("com.rutgeruijtendaal.core.db.entities.OrderStatus.getAll"));
    }
}
