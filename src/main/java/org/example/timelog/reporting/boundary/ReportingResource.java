package org.example.timelog.reporting.boundary;

import org.example.timelog.reporting.generator.MonthlyBalanceGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;

@ApplicationScoped
@Path("reporting")
public class ReportingResource {

    @Inject
    MonthlyBalanceGenerator generator;

    @GET
    @Path("generate/{year}/{month}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generate(@PathParam("year") int year, @PathParam("month") int month) {
        InputStream entity = generator.generateMonthlyReport(year, month);
        var reportFileName = String.format("timelog-%d-%d.pdf", year, month);
        return Response
                .ok((StreamingOutput) output -> output.write(entity.readAllBytes()))
                .header("Content-Disposition", "attachment; filename=" + reportFileName)
                .build();
    }

}
