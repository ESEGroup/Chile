package api.controllers;

import models.User;
import models.status.UserStatus;
import models.util.HttpResponse;
import services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */

@Path("/user")
@RequestScoped
public class UserController
{
    private UserService userService;

    public UserController()
    {
        this.userService = new UserService();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            User user = this.userService.get(id);

            //avoid sending password back to the front-end
            user.setPassword(null);
            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got user");
            response.setData(user);

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
            List<User> users = this.userService.getAll();

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successfully got all users");
            response.setData(users);

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
    public Response create(User user)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            int statusCode = this.userService.create(user);

            switch (statusCode)
            {
                case UserStatus.OK:
                    //avoid sending password back to the front-end
                    user.setPassword(null);
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully created user");
                    response.setData(user);
                    break;
                case UserStatus.CONFLICT:
                    response.setStatus(Response.Status.CONFLICT.getStatusCode());
                    response.setMessage("User already registered");
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
    public Response update(User user)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            int statusCode = this.userService.update(user);

            switch (statusCode)
            {
                case UserStatus.OK:
                    //avoid sending password back to the front-end
                    user.setPassword(null);
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully updated user");
                    response.setData(user);
                    break;
                case UserStatus.NOT_FOUND:
                    response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
                    response.setMessage("User not found");
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
            int statusCode = this.userService.softDelete(id);

            switch (statusCode)
            {
                case UserStatus.OK:
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.setMessage("Successfully deleted user");
                    break;
                case UserStatus.NOT_FOUND:
                    response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
                    response.setMessage("User not found");
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
