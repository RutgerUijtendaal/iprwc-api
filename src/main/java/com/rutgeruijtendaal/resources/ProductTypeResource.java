package com.rutgeruijtendaal.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.rutgeruijtendaal.core.ProductType;
import com.rutgeruijtendaal.db.ProductTypeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/producttype")
@Produces(MediaType.APPLICATION_JSON)
public class ProductTypeResource {

    private ProductTypeDAO productTypeDAO;

    @JsonCreator
    public ProductTypeResource(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @GET
    @UnitOfWork
    @Path("/{productTypeId}")
    public ProductType getProductType(@PathParam("productTypeId") IntParam productTypeId) {
        return findSafely(productTypeId.get());

    }

    @GET
    @UnitOfWork
    public List<ProductType> getAll() { return productTypeDAO.getAll(); }

    private ProductType findSafely(int productTypeId) {
        return productTypeDAO.findById(productTypeId).orElseThrow(() -> new NotFoundException("No such product type."));
    }
}
