package api.controllers;

import models.Maintenance;
import models.User;
import models.dto.LoginDTO;
import models.util.HttpResponse;
import services.LoginService;
import services.MaintenanceService;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ericreis on 11/10/16.
 */

@Path("/maintenance")
@RequestScoped
public class MaintenanceController
{
    private MaintenanceService maintenanceService;

    public MaintenanceController()
    {
        this.maintenanceService = new MaintenanceService();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Maintenance maintenance)
    {
        HttpResponse response = new HttpResponse();

        try
        {
//            switch (this.maintenanceService.create(maintenance))
//            {
//                case x:
//                    ...
//                    ...
//            }

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
