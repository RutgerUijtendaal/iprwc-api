package com.rutgeruijtendaal.auth.jwt;

import java.security.Principal;
import java.util.Objects;

public class AuthUser implements Principal {

    private final int id;
    private final String name;
    private final String roles;

    public AuthUser(int id, String name, String roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUser authUser = (AuthUser) o;
        return id == authUser.id &&
                name.equals(authUser.name) &&
                roles.equals(authUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roles);
    }
}
