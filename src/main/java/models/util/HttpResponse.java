package models.util;

import models.User;

/**
 * Created by ericreis on 10/18/16.
 */
public class HttpResponse
{
    private int status;
    private String message;
    private User user;

    public int getStatus()
    {
        return this.status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
