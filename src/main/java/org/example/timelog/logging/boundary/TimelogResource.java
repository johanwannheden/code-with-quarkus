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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TimelogResource {

    private static final Logger LOGGER = Logger.getLogger(TimelogResource.class);

    @Inject
    TimelogService service;

    @Operation(
            summary = "Add entry",
            description = "Persist a Timelog record and returns it"
    )
    @PUT
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
    @Path("all")
    public Response getLogEntries() {
        var allEntries = service.getAllEntries();
        return Response.ok(allEntries).build();
    }

    @Operation(
            summary = "Update entry",
            description = "Updates a Timelog record"
    )
    @POST
    @Path("update/{id}")
    public void updateLogEntry(@PathParam("id") @NotNull long id, @RequestBody TimelogEntity entry) {
        service.updateEntry(id, entry);
    }

    @Operation(
            summary = "Delete entry",
            description = "Deletes a Timelog record"
    )
    @DELETE
    @Path("delete/{id}")
    public void deleteLogEntry(@PathParam("id") @NotNull long id) {
        service.deleteEntry(id);
    }
}
