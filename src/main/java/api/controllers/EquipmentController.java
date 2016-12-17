package api.controllers;

import models.Equipment;
import models.status.EquipmentStatus;
import models.util.HttpResponse;
import services.EquipmentService;

import javax.enterprise.context.RequestScoped;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

/**
 * Created by antonio-galvao on 16/12/16.
 */

@Path("/equipment")
@RequestScoped
public class EquipmentController {
    public EquipmentService equipmentService;

    public EquipmentController() { this.equipmentService = new EquipmentService(); }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        HttpResponse response = new HttpResponse();

        try{
            Equipment equipment = this.equipmentService.get(id);

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got equipment");
            response.setData(equipment);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch(Exception e){
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        HttpResponse response = new HttpResponse();

        try{
            List<Equipment> equipments = this.equipmentService.getAll();

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got all equipments");
            response.setData(equipments);

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch(Exception e) {
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Equipment equipment){
        HttpResponse response = new HttpResponse();

        try{
            int statusCode = this.equipmentService.create(equipment);

            switch (statusCode){
                case EquipmentStatus.OK:
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully created equipment");
                    response.setData(equipment);
                    break;
                case EquipmentStatus.CONFLICT:
                    response.setStatus(Response.Status.CONFLICT.getStatusCode());
                    response.setMessage("Equipment already registered");
            }

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e){
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Equipment equipment){
        HttpResponse response = new HttpResponse();

        try{
            int statusCode = this.equipmentService.update(equipment);

            switch (statusCode){
                case EquipmentStatus.OK:
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully updated equipment");
                    response.setData(equipment);
                    break;
                case EquipmentStatus.NOT_FOUND:
                    response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
                    response.setMessage("Equipment not found");
            }

            return Response.status(response.getStatus()).entity(response).build();
        }
        catch (Exception e){
            response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            response.setMessage(e.getMessage());
            return Response.status(response.getStatus()).entity(response).build();
        }
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id){
        HttpResponse response = new HttpResponse();

        try
        {
            int statusCode = this.equipmentService.softDelete(id);
            switch (statusCode)
            {
                case EquipmentStatus.OK:
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully deleted equipment");
                    break;
                case EquipmentStatus.NOT_FOUND:
                    response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
                    response.setMessage("Equipment not found");
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

}
