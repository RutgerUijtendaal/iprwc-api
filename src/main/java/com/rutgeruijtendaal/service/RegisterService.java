package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.core.db.entities.Cart;
import com.rutgeruijtendaal.core.db.entities.User;
import com.rutgeruijtendaal.db.CartDAO;
import com.rutgeruijtendaal.db.DaoManager;
import com.rutgeruijtendaal.db.UserDAO;
import com.rutgeruijtendaal.exceptions.DropwizardException;
import com.rutgeruijtendaal.utils.PasswordService;
import com.rutgeruijtendaal.utils.ValidationService;

import javax.ws.rs.WebApplicationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterService {

    private UserDAO userDao;
    private CartDAO cartDAO;

    public RegisterService() {
        this.userDao = DaoManager.getInstance().getUserDAO();
        this.cartDAO = DaoManager.getInstance().getCartDAO();
    }

    public User register(User user) throws DropwizardException {
        if(userDao.isEmailTaken(user.getEmail())) {
            throw new DropwizardException(400, "Email already exists");
        }

        validateCredentials(user);

        user.setPassword(createPasswordHash(user.getPassword()));

        userDao.create(user);

        Cart cart = new Cart(user.getUserId());
        cartDAO.create(cart);

        return user;
    }

    private void validateCredentials(User user) throws DropwizardException {
        if(!ValidationService.isValidEmail(user.getEmail()) || !ValidationService.isValidPassword(user.getPassword())) {
            throw new DropwizardException(400, "Invalid user credentials");
        }
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

