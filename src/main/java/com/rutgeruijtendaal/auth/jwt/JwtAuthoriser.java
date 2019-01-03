package com.rutgeruijtendaal.auth.jwt;

import io.dropwizard.auth.Authorizer;

public class JwtAuthoriser implements Authorizer<AuthUser> {

    @Override
    public boolean authorize(AuthUser authUser, String requiredRole) {
        if (authUser == null) {
            System.out.println("user object was null");
            return false;
        }

        String roles = authUser.getRoles();
        if (roles == null) {
            System.out.println("roles were null");
            return false;
        }
        return roles.contains(requiredRole);
    }
}
