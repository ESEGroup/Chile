package api.controllers;

import models.User;
import models.dto.LoginDTO;
import models.util.HttpResponse;
import services.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ericreis on 11/10/16.
 */

@Path("/login")
@RequestScoped
public class LoginController
{
    private LoginService loginService;

    public LoginController()
    {
        this.loginService = new LoginService();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO)
    {
        HttpResponse response = new HttpResponse();

        try
        {
            User user = this.loginService.login(loginDTO);

            if (user != null)
            {
                response.setStatus(Response.Status.OK.getStatusCode());
                response.setMessage("Login successful.");
                response.setUser(user);
            }
            else
            {
                response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
                response.setMessage("Invalid credentials.");
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
