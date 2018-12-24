package com.rutgeruijtendaal.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class BaseDAO<T> extends AbstractDAO<T> {

    public BaseDAO(SessionFactory factory) { super(factory); }

    public Optional<T> findById(int id) {
        return Optional.ofNullable(get(id));
    }
}
