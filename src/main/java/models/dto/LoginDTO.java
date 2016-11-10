package models.dto;

/**
 * Created by ericreis on 11/10/16.
 */
public class LoginDTO
{
    private String employeeId;
    private String password;

    public LoginDTO()
    {
    }

    public String getEmployeeId()
    {
        return employeeId;
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
}
