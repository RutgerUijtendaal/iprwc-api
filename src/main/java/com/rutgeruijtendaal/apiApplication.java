package com.rutgeruijtendaal;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import com.rutgeruijtendaal.core.db.entities.*;
import com.rutgeruijtendaal.exceptions.DropwizardExceptionMapper;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import org.jose4j.jwt.consumer.JwtContext;

import com.rutgeruijtendaal.auth.AuthFilters;
import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.db.*;
import com.rutgeruijtendaal.resources.*;
import com.rutgeruijtendaal.service.*;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.PolymorphicAuthDynamicFeature;
import io.dropwizard.auth.PolymorphicAuthValueFactoryProvider;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class apiApplication extends Application<apiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new apiApplication().run(args);
    }


    private final HibernateBundle<apiConfiguration> hibernate = new HibernateBundle<apiConfiguration>(
            OrderStatus.class,
            User.class,
            ContactInfo.class,
            ProductType.class,
            PaymentType.class,
            TaxBracket.class,
            Product.class,
            Cart.class,
            CartItem.class,
            Order.class,
            OrderedProduct.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(apiConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "api";
    }

    @Override
    public void initialize(Bootstrap<apiConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(apiConfiguration config, Environment environment) {
        DaoManager.getInstance().setupDAOS(hibernate.getSessionFactory());

        environment.jersey().register(new DropwizardExceptionMapper());

        registerResources(environment);
        registerAuthFilters(environment);
        configureCors(environment);
    }

    private void registerResources(Environment environment) {
        registerBaseResources(environment);
        registerUserResources(environment);
        registerProductResources(environment);
        registerCartResources(environment);
        registerOrderResources(environment);
    }

    private void registerBaseResources(Environment environment) {
        environment.jersey().register(new OrderStatusResource());
        environment.jersey().register(new TaxBracketResource());
        environment.jersey().register(new ProductTypeResource());
        environment.jersey().register(new PaymentTypeResource());
    }

    private void registerUserResources(Environment environment) {
        final RegisterService registerService = new RegisterService();
        final LoginService loginService = new LoginService();
        final ContactService contactService = new ContactService();

        environment.jersey().register(new LoginResource(loginService));
        environment.jersey().register(new ContactInfoResource(contactService));
        environment.jersey().register(new RegisterResource(registerService));
    }

    private void registerProductResources(Environment environment) {
        environment.jersey().register(new ProductResource());
    }

    private void registerCartResources(Environment environment) {
        final CartService cartService = new CartService();

        environment.jersey().register(new CartResource(cartService));
    }

    private void registerOrderResources(Environment environment) {
        final OrderService orderService = new OrderService();

        environment.jersey().register(new OrderResource(orderService));
    }


    private void registerAuthFilters(Environment environment) {
        AuthFilters authFilters = new AuthFilters(hibernate);

        final AuthFilter<BasicCredentials, PrincipalImpl> basicFilter = authFilters.buildBasicAuthFilter();
        final AuthFilter<JwtContext, AuthUser> jwtFilter = authFilters.buildJwtAuthFilter();

        final PolymorphicAuthDynamicFeature feature = new PolymorphicAuthDynamicFeature<>(
                ImmutableMap.of(
                        PrincipalImpl.class, basicFilter,
                        AuthUser.class, jwtFilter));

        final AbstractBinder binder = new PolymorphicAuthValueFactoryProvider.Binder<>(
                ImmutableSet.of(PrincipalImpl.class, AuthUser.class));

        environment.jersey().register(feature);
        environment.jersey().register(binder);
        environment.jersey().register(RolesAllowedDynamicFeature.class);

    }

    private void configureCors(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }

}
