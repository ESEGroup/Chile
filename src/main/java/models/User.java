package models;

import java.util.Date;

/**
 * Created by ericreis on 10/8/16.
 */
public class User
{
    private int id;
    private String name;
    private String cpf;
    private String rg;
    private String rgIssuer;
    private String employeeId;
    private String password;
    private Date birthDate;
    private boolean isAdmin;

    public User()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public String getRgIssuer()
    {
        return rgIssuer;
    }

    public void setRgIssuer(String rgIssuer)
    {
        this.rgIssuer = rgIssuer;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public boolean isAdmin()
    {
        return isAdmin;
    }

    public void setAdmin(boolean admin)
    {
        isAdmin = admin;
    }

}
