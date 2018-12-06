package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.OrderStatus;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class OrderStatusDAO extends AbstractDAO<OrderStatus> {

    public OrderStatusDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<OrderStatus> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    @SuppressWarnings("unchecked")
    public List<OrderStatus> getAll() {
        return list((Query<OrderStatus>) namedQuery("com.rutgeruijtendaal.core.OrderStatus.getAll"));
    }
}
