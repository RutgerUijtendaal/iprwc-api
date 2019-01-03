package com.rutgeruijtendaal.resources;

import com.rutgeruijtendaal.auth.Secrets;
import com.rutgeruijtendaal.auth.jwt.UserRoles;
import com.rutgeruijtendaal.core.LoginResponse;
import com.rutgeruijtendaal.service.LoginService;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.hibernate.UnitOfWork;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

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
