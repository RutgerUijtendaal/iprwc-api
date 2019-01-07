package com.rutgeruijtendaal.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.db.entities.ContactInfo;
import com.rutgeruijtendaal.db.ContactInfoDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

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

    @GET
    @Timed
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public ContactInfo getContact(@Auth AuthUser authUser) {
        System.out.println(authUser);
        return findSafely(authUser.getId());
    }

    private ContactInfo findSafely(int contactInfoId) {
        return contactInfoDAO.findById(contactInfoId).orElseThrow(() -> new NotFoundException("No such contact info."));
    }

}
