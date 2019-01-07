package com.rutgeruijtendaal.resources;

import com.rutgeruijtendaal.core.response.LoginResponse;
import com.rutgeruijtendaal.service.LoginService;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.hibernate.UnitOfWork;
import org.jose4j.lang.JoseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private LoginService loginService;

    public LoginResource(LoginService loginService) { this.loginService = loginService; }

    @GET
    @UnitOfWork
    public LoginResponse login(@Auth PrincipalImpl principal) throws JoseException {
        return loginService.login(principal);
    }

}
