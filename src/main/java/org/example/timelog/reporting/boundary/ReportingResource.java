package org.example.timelog.reporting.boundary;

import org.example.timelog.CallContext;
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
    private final CallContext callContext;

    @Inject
    ReportingResource(ReportingService reportingService, CallContext callContext){
        this.reportingService = reportingService;
        this.callContext = callContext;
    }

    @GET
    @Path("generate/{year}/{month}")
    @Produces("application/pdf")
    public Response generate(@PathParam("year") int year, @PathParam("month") int month) {
        var report = reportingService.generateMonthlyReport(year, month, callContext.getCurrentUserId());
        var reportFileName = String.format("timelog-%d-%d.pdf", year, month);
        return Response
                .ok((StreamingOutput) output -> output.write(report.readAllBytes()))
                .header("Content-Disposition", "attachment; filename=" + reportFileName)
                .build();
    }

}
