package com.rutgeruijtendaal.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.rutgeruijtendaal.core.TaxBracket;
import com.rutgeruijtendaal.db.TaxBracketDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/taxbracket")
@Produces(MediaType.APPLICATION_JSON)
public class TaxBracketResource {

    private TaxBracketDAO taxBracketDAO;

    @JsonCreator
    public TaxBracketResource(TaxBracketDAO taxBracketDAO) {
        this.taxBracketDAO = taxBracketDAO;
    }

    @GET
    @UnitOfWork
    @Path("/{taxBracketId}")
    public TaxBracket getTaxBracket(@PathParam("taxBracketId") IntParam taxBracketId) {
        return findSafely(taxBracketId.get());
    }

    @GET
    @UnitOfWork
    public List<TaxBracket> getAll() { return taxBracketDAO.getAll(); }

    private TaxBracket findSafely(int taxBracketId) {
        return taxBracketDAO.findById(taxBracketId).orElseThrow(() -> new NotFoundException("No such tax bracket."));
    }
}
