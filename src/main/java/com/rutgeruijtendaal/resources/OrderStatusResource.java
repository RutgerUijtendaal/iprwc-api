package com.rutgeruijtendaal.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.rutgeruijtendaal.core.db.entities.OrderStatus;
import com.rutgeruijtendaal.db.OrderStatusDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orderstatus")
@Produces(MediaType.APPLICATION_JSON)
public class OrderStatusResource {

    private OrderStatusDAO orderStatusDAO;

    @JsonCreator
    public OrderStatusResource(OrderStatusDAO orderStatusDAO) {
        this.orderStatusDAO = orderStatusDAO;
    }

    @GET
    @UnitOfWork
    @Path("/{orderStatusId}")
    public OrderStatus getOrderStatus(@PathParam("orderStatusId")IntParam orderStatusId) {
        return findSafely(orderStatusId.get());
    }

    @GET
    @UnitOfWork
    public List<OrderStatus> getAll() {
        return orderStatusDAO.getAll();
    }

    private OrderStatus findSafely(int orderStatusId) {
        return orderStatusDAO.findById(orderStatusId).orElseThrow(() -> new NotFoundException("No such order status."));
    }
}
