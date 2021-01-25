package org.example.timelog.user.boundary;

import org.example.timelog.user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("user")
public class UserResource {

    @Inject
    UserService service;

    @GET
    @Path("currentUserId")
    @Produces(MediaType.TEXT_PLAIN)
    // TEST PURPOSES only, for the time being, until there is a way to distinguish
    // multiple users
    public Response getCurrentUserId() {
        return Response.ok(service.getIdOfSingleEmployee()).build();
    }

}
