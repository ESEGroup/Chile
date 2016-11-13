package api.controllers;

import models.User;
import models.dto.LoginDTO;
import models.util.HttpResponse;
import services.LoginService;
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
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login()
    {
        HttpResponse response = new HttpResponse();

        try
        {
            List<User> users = this.userService.getAll();

            response.setStatus(Response.Status.OK.getStatusCode());
            response.setMessage("Successful get all users");
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

}
