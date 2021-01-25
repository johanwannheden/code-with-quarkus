package org.example.timelog.logging.boundary;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.example.timelog.logging.model.TimelogEntity;
import org.example.timelog.logging.service.TimelogService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("timelog")
public class TimelogResource {

    private static final Logger LOGGER = Logger.getLogger(TimelogResource.class);

    @Inject
    TimelogService service;

    @Operation(
            summary = "Add entry",
            description = "Persist a Timelog record and returns it"
    )
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createLogEntry(@RequestBody TimelogEntity entry) {
        LOGGER.info("Adding entry " + entry);
        return Response.accepted(service.persistEntry(entry)).build();
    }

    @Operation(
            summary = "Get entries",
            description = "Reads all Timelog record"
    )
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getLogEntries() {
        var allEntries = service.getAllEntries();
        return Response.ok(allEntries).build();
    }

    @Operation(
            summary = "Update entry",
            description = "Updates a Timelog record"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("update/{id}")
    public void updateLogEntry(@PathParam("id") @NotNull String id, @RequestBody TimelogEntity entry) {
        service.updateEntry(Long.parseLong(id), entry);
    }

    @Operation(
            summary = "Delete entry",
            description = "Deletes a Timelog record"
    )
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteLogEntry(@PathParam("id") @NotNull String id) {
        service.deleteEntry(Long.parseLong(id));
    }
}
