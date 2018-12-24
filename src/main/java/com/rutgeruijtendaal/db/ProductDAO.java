package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO extends BaseDAO<Product> {

    public ProductDAO(SessionFactory factory) {
        super(factory);
    }


    public Product create(Product product) { return persist(product); }

    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        return list((Query<Product>) namedQuery("com.rutgeruijtendaal.core.Product.getAll"));
    }
}
