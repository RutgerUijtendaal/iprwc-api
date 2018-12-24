package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.core.User;
import com.rutgeruijtendaal.db.UserDAO;
import com.rutgeruijtendaal.utils.PasswordService;

import javax.ws.rs.WebApplicationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterService {

    private UserDAO userDao;

    public RegisterService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public User register(User user) {
        if(userDao.isEmailTaken(user.getEmail())) {
            throw new WebApplicationException("Email already exists");
        }

        user.setPassword(createPasswordHash(user.getPassword()));

        userDao.create(user);

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

