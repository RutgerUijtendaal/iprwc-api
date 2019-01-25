package com.rutgeruijtendaal.resources;

import com.codahale.metrics.annotation.Timed;
import com.rutgeruijtendaal.auth.jwt.AuthUser;
import com.rutgeruijtendaal.core.db.entities.ContactInfo;
import com.rutgeruijtendaal.exceptions.DropwizardException;
import com.rutgeruijtendaal.service.ContactService;
import io.dropwizard.auth.Auth;
import io.dropwizard.auth.PrincipalImpl;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/contact")
@Consumes(MediaType.APPLICATION_JSON)
public class ContactInfoResource {

    private ContactService contactService;

    public ContactInfoResource(ContactService contactService) {
        this.contactService = contactService;
    }

    @POST
    @Timed
    @UnitOfWork
    public ContactInfo createContactInfo(@Auth PrincipalImpl principal, ContactInfo contactInfo) throws DropwizardException {
        return contactService.saveNewContact(principal, contactInfo);
    }

    @GET
    @Timed
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public ContactInfo getContact(@Auth AuthUser authUser) {
        return contactService.getContactInfo(authUser);
    }


}
