package com.rutgeruijtendaal.service;

import com.rutgeruijtendaal.auth.Secrets;
import com.rutgeruijtendaal.auth.jwt.UserRoles;
import com.rutgeruijtendaal.core.response.LoginResponse;
import com.rutgeruijtendaal.core.db.entities.User;
import com.rutgeruijtendaal.db.UserDAO;
import io.dropwizard.auth.PrincipalImpl;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

public class LoginService {

    private UserDAO userDAO;

    public LoginService(UserDAO userDAO) { this.userDAO = userDAO; }

    public LoginResponse login(PrincipalImpl principal) throws JoseException {
        Optional<User> user = userDAO.getByEmail(principal.getName());

        if(user.isPresent()) {
            return new LoginResponse(buildToken(user.get()).getCompactSerialization());
        } else {
            throw new NotFoundException("No user for these credentials");
        }
    }


    private JsonWebSignature buildToken(User user) {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject(String.valueOf(user.getUserId()));
        claims.setStringClaim("user", user.getEmail());
        claims.setStringClaim("roles", UserRoles.ROLE_ONE);
        claims.setIssuedAtToNow();
        claims.setGeneratedJwtId();

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(Secrets.JWT_SECRET_KEY));
        return jws;
    }
}
