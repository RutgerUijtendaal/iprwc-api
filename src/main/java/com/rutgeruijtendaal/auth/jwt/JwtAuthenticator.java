package com.rutgeruijtendaal.auth.jwt;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtContext;

import java.util.Optional;

public class JwtAuthenticator implements Authenticator<JwtContext, AuthUser> {


    @Override
    public Optional<AuthUser> authenticate(JwtContext context) throws AuthenticationException {
        try {
            JwtClaims claims = context.getJwtClaims();
            int id = Integer.parseInt(claims.getSubject());
            String username = (String) claims.getClaimValue("user");
            String roles = (String) claims.getClaimValue("roles");

            return Optional.of(new AuthUser(id, username, roles));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
