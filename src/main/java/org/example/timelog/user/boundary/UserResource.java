package org.example.timelog.user.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.timelog.user.service.UserService;

import com.google.firebase.auth.FirebaseAuthException;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

@ApplicationScoped
@Path("user")
@Timed(value = "user")
public class UserResource {

    @Inject
    UserService service;

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
