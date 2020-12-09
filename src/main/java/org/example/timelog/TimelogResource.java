package org.example.timelog;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.example.timelog.model.Timelog;
import org.example.timelog.service.TimelogService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
            description = "Persist a Timelog record"
    )
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public void createLogEntry(@RequestBody Timelog entry) {
        LOGGER.info("Adding entry " + entry);
        service.persistEntry(entry);
    }

    @Operation(
            summary = "Get entries",
            description = "Reads all Timelog record"
    )
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getLogEntires() {
        var allEntries = service.getAllEntries();
        return Response.ok(allEntries).build();
    }

    @Operation(
            summary = "Update entry",
            description = "Updates a Timelog record"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("update")
    public void updateLogEntry(@RequestBody Timelog entry) {
        service.updateEntry(entry);
    }
}
