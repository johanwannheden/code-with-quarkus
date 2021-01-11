package org.example.timelog.reporting.boundary;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.example.timelog.reporting.model.Employee;
import org.example.timelog.reporting.service.EmployeeService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("employee")
public class EmployeeResource {

    private static final Logger LOGGER = Logger.getLogger(EmployeeResource.class);

    @Inject
    EmployeeService employeeService;

    @PUT
    @Path("create")
    public Response createEmployee(@RequestBody Employee employee) {
        LOGGER.info("Adding employee " + employee);
        employeeService.persistEmployee(employee);
        return Response.ok().build();
    }

}
