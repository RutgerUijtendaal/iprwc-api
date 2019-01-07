package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.ProductType;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductTypeDAO extends BaseDAO<ProductType> {

    public ProductTypeDAO(SessionFactory factory) {
        super(factory);
    }

    @SuppressWarnings("unchecked")
    public List <ProductType> getAll() {
        return list((Query<ProductType>) namedQuery("com.rutgeruijtendaal.core.db.entities.ProductType.getAll"));
    }
}
