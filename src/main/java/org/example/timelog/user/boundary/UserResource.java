package org.example.timelog.user.boundary;

import com.google.firebase.auth.FirebaseAuthException;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.example.timelog.CallContext;
import org.example.timelog.user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@ApplicationScoped
@Path("user")
@Timed(value = "user")
public class UserResource {

    @Inject
    UserService service;

    @Inject
    CallContext callContext;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var currentUser = service.findUser(callContext.getCurrentUserId());
        if (currentUser != null && currentUser.isAdministrator()) {
            return Response.ok(service.findAll()).build();
        }
        return Response.ok(Collections.emptyList()).build();
    }

    @GET
    @Path("verify/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyEmail(@PathParam("email") String email) {
        var userWithEmailExists = service.verifyEmailAddressExists(email);
        return Response.ok(String.format("{\"valid\": %s}", userWithEmailExists)).build();
    }

    @GET
    @Path("detail/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetail(@PathParam("userId") String userId) {
        var user = service.findUser(userId);
        return Response.ok(user).build();
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(value = "user.register")
    public Response register(@QueryParam("token") String token, @QueryParam("email") String email) throws FirebaseAuthException {
        var user = service.verifyUser(token, email);
        return Response.ok(user).build();
    }

}
