package com.rutgeruijtendaal.auth;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import com.rutgeruijtendaal.auth.basic.BasicAuthenticator;
import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.auth.jwt.JwtAuthenticator;
import com.rutgeruijtendaal.auth.jwt.JwtAuthoriser;
import com.rutgeruijtendaal.db.DaoManager;
import com.rutgeruijtendaal.db.UserDAO;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.HmacKey;

public class AuthFilters {

    HibernateBundle hibernate;

    public AuthFilters(HibernateBundle hibernate) {
        this.hibernate = hibernate;
    }

    public BasicCredentialAuthFilter<PrincipalImpl> buildBasicAuthFilter() {
        BasicAuthenticator basicAuthenticator = new UnitOfWorkAwareProxyFactory(hibernate)
                .create(BasicAuthenticator.class, UserDAO.class, DaoManager.getInstance().getUserDAO());

        return new BasicCredentialAuthFilter
                .Builder<PrincipalImpl>()
                .setAuthenticator(basicAuthenticator)
                .setPrefix("Basic")
                .buildAuthFilter();
    }

    public AuthFilter<JwtContext, AuthUser> buildJwtAuthFilter() {
        // These requirements would be tightened up for production use
        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(300)
                .setRequireSubject()
                .setVerificationKey(new HmacKey(Secrets.JWT_SECRET_KEY))
                .build();

        return new JwtAuthFilter.Builder<AuthUser>()
                .setJwtConsumer(consumer)
                .setRealm("realm")
                .setPrefix("Bearer")
                .setAuthenticator(new JwtAuthenticator())
                .setAuthorizer(new JwtAuthoriser()).buildAuthFilter();
    }
}
