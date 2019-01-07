package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.db.entities.TaxBracket;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TaxBracketDAO extends BaseDAO<TaxBracket> {

    public TaxBracketDAO(SessionFactory factory) {
        super(factory);
    }

    @SuppressWarnings("unchecked")
    public List<TaxBracket> getAll() {
        return list((Query<TaxBracket>) namedQuery("com.rutgeruijtendaal.core.db.entities.TaxBracket.getAll"));
    }
}
