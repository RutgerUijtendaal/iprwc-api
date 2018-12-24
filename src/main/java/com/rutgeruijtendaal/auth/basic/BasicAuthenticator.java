package com.rutgeruijtendaal.auth.basic;

import com.rutgeruijtendaal.core.User;
import com.rutgeruijtendaal.db.DaoManager;
import com.rutgeruijtendaal.db.UserDAO;
import com.rutgeruijtendaal.utils.PasswordService;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, PrincipalImpl> {

    private UserDAO userDAO;

    public BasicAuthenticator(UserDAO userDAO) {
        this.userDAO = userDAO;
//        this.userDAO = DaoManager.getInstance().getUserDAO();
    }

    @Override
    @UnitOfWork
    public Optional<PrincipalImpl> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if(isValidCredentials(credentials)) {
            return Optional.of(new PrincipalImpl(credentials.getUsername()));
        }
        return Optional.empty();
    }

    private boolean isValidCredentials(BasicCredentials credentials) {
        Optional<User> user = userDAO.getByEmail(credentials.getUsername());

        if(user.isPresent()) {

            try {
                return PasswordService.isValidPassword(credentials.getPassword(), user.get().getPassword());
            } catch(NoSuchAlgorithmException e) {
                System.out.println(e.getMessage());
            } catch(InvalidKeySpecException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("No user found during cred validation");
        }
        return false;
    }


}
