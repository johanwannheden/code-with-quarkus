package org.example.timelog.reporting.boundary;

import org.example.timelog.reporting.generator.MonthlyBalanceGenerator;
import org.example.timelog.reporting.service.ReportingService;
import org.example.timelog.reporting.service.UserService;

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
import java.util.UUID;

@ApplicationScoped
@Path("reporting")
public class ReportingResource {

    private final ReportingService reportingService;

    @Inject
    ReportingResource(ReportingService reportingService){
        this.reportingService = reportingService;
    }

    @GET
    @Path("generate/{year}/{month}/{userId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generate(@PathParam("year") int year, @PathParam("month") int month, @PathParam("userId") String userId) {
        var report = reportingService.generateMonthlyReport(year, month, userId);
        var reportFileName = String.format("timelog-%d-%d.pdf", year, month);
        return Response
                .ok((StreamingOutput) output -> output.write(report.readAllBytes()))
                .header("Content-Disposition", "attachment; filename=" + reportFileName)
                .build();
    }

}
