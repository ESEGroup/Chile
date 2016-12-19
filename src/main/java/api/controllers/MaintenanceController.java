package api.controllers;

import models.Maintenance;
import models.status.MaintenanceStatus;
import models.util.HttpResponse;
import services.MaintenanceService;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    @Path("/getAlert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlert()
    {
        HttpResponse response = new HttpResponse();
        try{
            List<Maintenance> maintenances = maintenanceService.getAlert();

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got all maintenances");
            response.setData(maintenances);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch(Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            Maintenance maintenance = this.maintenanceService.get(id);

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got maintenance");
            response.setData(maintenance);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        HttpResponse response = new HttpResponse();

        try
        {
            List<Maintenance> maintenances = this.maintenanceService.getAll();

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got all maintenances");
            response.setData(maintenances);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @GET
    @Path("/getMaintenanceByEquipmentId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaintenanceByEquipmentId(@PathParam("id") int id)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            Maintenance maintenance = this.maintenanceService.getMaintenanceByEquipmentId(id);

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got maintenance");
            response.setData(maintenance);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @GET
    @Path("/getMaintenancesByEquipmentId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMaintenanceByEquipmentId(@PathParam("id") int id)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            List<Maintenance> maintenances = this.maintenanceService.getAllMaintenanceByEquipmentId(id);

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got maintenance");
            response.setData(maintenances);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
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
            int statusCode = this.maintenanceService.create(maintenance);

            switch (statusCode)
            {
                case MaintenanceStatus.OK:
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully created maintenance");
                    response.setData(maintenance);
                    break;
                case MaintenanceStatus.CONFLICT:
                    response.setStatus(Response.Status.CONFLICT.getStatusCode());
                    response.setMessage("Already exists a maintenance for this equipment");
            }

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Maintenance maintenance)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            int statusCode = this.maintenanceService.update(maintenance);

            switch (statusCode)
            {
                case MaintenanceStatus.OK:
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully updated maintenance");
                    response.setData(maintenance);
                    break;
                case MaintenanceStatus.NOT_FOUND:
                    response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
                    response.setMessage("Maintenance not found");
            }

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            int statusCode = this.maintenanceService.softDelete(id);

            switch (statusCode)
            {
                case MaintenanceStatus.OK:
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully deleted maintenance");
                    break;
                case MaintenanceStatus.NOT_FOUND:
                    response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
                    response.setMessage("Maintenance not found");
            }

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e)
        {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }}
