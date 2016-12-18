package api.controllers;

import models.Role;
import models.util.HttpResponse;
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

@Path("/role")
@RequestScoped
public class RoleController
{
    private RoleService roleService;

    public RoleController()
    {
        this.roleService = new RoleService();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login()
    {
        HttpResponse response = new HttpResponse();

        try
        {
            List<Role> roles = this.roleService.getAll();

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got all roles");
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
