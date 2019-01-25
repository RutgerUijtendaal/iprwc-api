package com.rutgeruijtendaal.resources;

import com.rutgeruijtendaal.core.db.entities.PaymentType;
import com.rutgeruijtendaal.db.DaoManager;
import com.rutgeruijtendaal.db.PaymentTypeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/paymenttype")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentTypeResource {

    private PaymentTypeDAO paymentTypeDAO;

    public PaymentTypeResource() {
        this.paymentTypeDAO = DaoManager.getInstance().getPaymentTypeDAO();
    }

    @GET
    @UnitOfWork
    @Path("/{paymentTypeId}")
    public PaymentType getOrderStatus(@PathParam("paymentTypeId") IntParam paymentTypeId) {
        return findSafely(paymentTypeId.get());
    }

    @GET
    @UnitOfWork
    public List<PaymentType> getAll() { return paymentTypeDAO.getAll(); }

    private PaymentType findSafely(int paymentTypeId) {
        return paymentTypeDAO.findById(paymentTypeId).orElseThrow(() -> new NotFoundException("No such payment type."));
    }
}
