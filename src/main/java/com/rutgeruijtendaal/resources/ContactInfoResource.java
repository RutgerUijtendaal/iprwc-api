package com.rutgeruijtendaal.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.rutgeruijtendaal.core.ContactInfo;
import com.rutgeruijtendaal.core.OrderStatus;
import com.rutgeruijtendaal.db.ContactInfoDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/contact")
@Consumes(MediaType.APPLICATION_JSON)
public class ContactInfoResource {

    private ContactInfoDAO contactInfoDAO;

    @JsonCreator
    public ContactInfoResource(ContactInfoDAO contactInfoDAO) {
        this.contactInfoDAO = contactInfoDAO;
    }

    @POST
    @Timed
    @UnitOfWork
    public void createContactInfo(ContactInfo contactInfo) {
        contactInfoDAO.create(contactInfo);
    }

    @Path("/{id}")
    @GET
    @Timed
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public ContactInfo getContact(@PathParam("id") IntParam contactInfoId) {
        return findSafely(contactInfoId.get());
    }

    private ContactInfo findSafely(int contactInfoId) {
        return contactInfoDAO.findById(contactInfoId).orElseThrow(() -> new NotFoundException("No such contact info."));
    }

}
