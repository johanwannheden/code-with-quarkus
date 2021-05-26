package org.example.timelog.reporting.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.example.timelog.CallContext;
import org.example.timelog.reporting.service.ReportingService;

import io.micrometer.core.annotation.Timed;

@ApplicationScoped
@Path("reporting")
@Timed(value = "reporting", percentiles = {0.80, 0.90, 0.95}, histogram = true)
public class ReportingResource {

    private final ReportingService reportingService;
    private final CallContext callContext;

    @Inject
    ReportingResource(ReportingService reportingService, CallContext callContext) {
        this.reportingService = reportingService;
        this.callContext = callContext;
    }

    @GET
    @Path("generate/{year}/{month}/{user}")
    @Produces("application/pdf")
    @Timed(value = "generate", longTask = true)
    public Response generate(@PathParam("year") int year, @PathParam("month") int month, @PathParam("user") String user) {
        var report = reportingService.generateMonthlyReport(year, month, user);
        var reportFileName = String.format("timelog-%d-%d.pdf", year, month);
        return Response.ok((StreamingOutput) output -> output.write(report.readAllBytes()))
                .header("Content-Disposition", "attachment; filename=" + reportFileName)
                .build();
    }

}
