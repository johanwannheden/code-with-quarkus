package org.example.timelog.reporting.boundary;

import org.example.timelog.reporting.service.ReportingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

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
    @Produces("application/pdf")
    public Response generate(@PathParam("year") int year, @PathParam("month") int month, @PathParam("userId") String userId) {
        var report = reportingService.generateMonthlyReport(year, month, userId);
        var reportFileName = String.format("timelog-%d-%d.pdf", year, month);
        return Response
                .ok((StreamingOutput) output -> output.write(report.readAllBytes()))
                .header("Content-Disposition", "attachment; filename=" + reportFileName)
                .build();
    }

}
