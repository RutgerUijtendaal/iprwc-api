package com.rutgeruijtendaal.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.rutgeruijtendaal.core.db.entities.Product;
import com.rutgeruijtendaal.db.DaoManager;
import com.rutgeruijtendaal.db.ProductDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private ProductDAO productDAO;

    public ProductResource() {
        this.productDAO = DaoManager.getInstance().getProductDAO();
    }

    @GET
    @UnitOfWork
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @GET
    @UnitOfWork
    @Path("/{productId}")
    public Product getProduct(@PathParam("productId") IntParam productId) {
        return findSafely(productId.get());
    }

    private Product findSafely(int productId) {
        return productDAO.findById(productId).orElseThrow(() -> new NotFoundException("No such product."));
    }
}
