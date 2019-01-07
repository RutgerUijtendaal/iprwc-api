package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.core.db.entities.Cart;
import com.rutgeruijtendaal.core.db.entities.User;
import com.rutgeruijtendaal.db.CartDAO;
import com.rutgeruijtendaal.db.UserDAO;
import com.rutgeruijtendaal.utils.PasswordService;

import javax.ws.rs.WebApplicationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterService {

    private UserDAO userDao;
    private CartDAO cartDAO;

    public RegisterService(UserDAO userDao, CartDAO cartDAO) {
        this.userDao = userDao;
        this.cartDAO = cartDAO;
    }

    public User register(User user) {
        if(userDao.isEmailTaken(user.getEmail())) {
            throw new WebApplicationException("Email already exists");
        }

        user.setPassword(createPasswordHash(user.getPassword()));

        userDao.create(user);

        Cart cart = new Cart(user.getUserId());
        cartDAO.create(cart);

        return user;
    }

    private String createPasswordHash(String password) {
        String hash = "";

        try {
            hash = PasswordService.generatePasswordHash(password);
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch(InvalidKeySpecException e) {
            System.out.println(e.getMessage());
        }

        return hash;
    }
}

