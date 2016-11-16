package api.controllers;

import models.Department;
import models.Role;
import models.util.HttpResponse;
import services.DepartmentService;
import services.RoleService;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */

@Path("/department")
@RequestScoped
public class DepartmentController
{
    private DepartmentService departmentService;

    public DepartmentController()
    {
        this.departmentService = new DepartmentService();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login()
    {
        HttpResponse response = new HttpResponse();

        try
        {
            List<Department> roles = this.departmentService.getAll();

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got all departments");
            response.setData(roles);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

}
