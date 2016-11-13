package models.util;

import models.User;

/**
 * Created by ericreis on 10/18/16.
 */
public class HttpResponse
{
    private int status;
    private String message;
    private Object data;

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

    public Object getData()
    {
        return this.data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
