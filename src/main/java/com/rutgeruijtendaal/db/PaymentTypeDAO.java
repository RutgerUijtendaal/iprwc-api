package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.PaymentType;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentTypeDAO extends BaseDAO<PaymentType> {

    public PaymentTypeDAO(SessionFactory factory) {
        super(factory);
    }

    public List<PaymentType> getAll() {
        return list((Query<PaymentType>) namedQuery("com.rutgeruijtendaal.core.db.entities.PaymentType.getAll"));
    }
}
