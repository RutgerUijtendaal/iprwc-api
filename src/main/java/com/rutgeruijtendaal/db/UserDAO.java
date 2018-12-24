package com.rutgeruijtendaal.db;

import com.rutgeruijtendaal.core.User;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;


public class UserDAO extends BaseDAO<User> {

    public UserDAO(SessionFactory factory) {super(factory);}

    public User create(User user) {
        return persist(user);
    }

    public Optional<User> getByEmail(String email) {
        Query query =  currentSession().createQuery("from User where email = :email");
        query.setParameter("email", email);
        if (query.list().size() > 0) {
            User user =  (User) query.list().get(0);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public boolean isEmailTaken(String email) {
        Optional<User> optionalUser = getByEmail(email);
        return optionalUser.isPresent();
    }
}
