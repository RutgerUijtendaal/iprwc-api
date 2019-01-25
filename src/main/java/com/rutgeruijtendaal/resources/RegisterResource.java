package com.rutgeruijtendaal.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import com.rutgeruijtendaal.core.db.entities.User;
import com.rutgeruijtendaal.exceptions.DropwizardException;
import com.rutgeruijtendaal.service.RegisterService;
import com.rutgeruijtendaal.view.View;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterResource {

    private RegisterService registerService;

    public RegisterResource(RegisterService registerService) {
        this.registerService = registerService;
    }

    @POST
    @Timed
    @UnitOfWork
    @JsonView(View.Protected.class)
    public User registerUser(User user) throws DropwizardException {
        return registerService.register(user);
    }

}
