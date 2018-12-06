package com.rutgeruijtendaal;

import com.rutgeruijtendaal.core.OrderStatus;
import com.rutgeruijtendaal.db.OrderStatusDAO;
import com.rutgeruijtendaal.resources.OrderStatusResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class apiApplication extends Application<apiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new apiApplication().run(args);
    }


    private final HibernateBundle<apiConfiguration> hibernate = new HibernateBundle<apiConfiguration>(OrderStatus.class) {
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
        final OrderStatusDAO dao = new OrderStatusDAO(hibernate.getSessionFactory());
        environment.jersey().register(new OrderStatusResource(dao));
    }

}
