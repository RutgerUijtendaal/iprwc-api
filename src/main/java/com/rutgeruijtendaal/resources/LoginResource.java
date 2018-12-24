package com.rutgeruijtendaal.resources;

import com.rutgeruijtendaal.auth.Secrets;
import com.rutgeruijtendaal.auth.jwt.UserRoles;
import com.rutgeruijtendaal.core.LoginResponse;
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

    @GET
    @UnitOfWork
    public LoginResponse login(@Auth PrincipalImpl user) throws JoseException {
        System.out.println(user);
        return new LoginResponse(buildToken(user).getCompactSerialization());
    }

    private JsonWebSignature buildToken(PrincipalImpl user) {
        // These claims would be tightened up for production
        final JwtClaims claims = new JwtClaims();
        claims.setSubject("1");
        claims.setStringClaim("user", user.getName());
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
