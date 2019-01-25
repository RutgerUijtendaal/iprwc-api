package com.rutgeruijtendaal.resources;

import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.db.entities.Order;
import com.rutgeruijtendaal.core.db.entities.OrderedProduct;
import com.rutgeruijtendaal.core.json.OrderJson;
import com.rutgeruijtendaal.exceptions.DropwizardException;
import com.rutgeruijtendaal.service.OrderService;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PUT
    @UnitOfWork
    public Order placeOrder(@Auth AuthUser authUser, OrderJson orderJson) throws DropwizardException {
        System.out.println(orderJson.paymentTypeId);
        return this.orderService.placeOrder(authUser, orderJson);
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Order updateOrder(@Auth AuthUser authUser, Order order) throws DropwizardException {
        return this.orderService.updateOrder(authUser, order);
    }

    @GET
    @UnitOfWork
    public List<Order> getAllByUser(@Auth AuthUser authUser) {
        return this.orderService.getAllByUser(authUser);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public List<OrderedProduct> getProductsForOrder(
            @Auth AuthUser authUser,
            @PathParam("id") IntParam orderId
    ) throws DropwizardException {
        return this.orderService.getProductsForOrder(authUser, orderId.get());
    }

}
