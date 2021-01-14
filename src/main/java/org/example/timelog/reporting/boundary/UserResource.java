package org.example.timelog.reporting.boundary;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.example.timelog.reporting.model.UserEntity;
import org.example.timelog.reporting.service.UserService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("user")
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class);

    @Inject
    UserService userService;

    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(@RequestBody UserEntity user) {
        LOGGER.info("Adding user " + user);
        userService.persistUser(user);
        return Response.ok().build();
    }

}
