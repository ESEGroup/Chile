package models;

/**
 * Created by ericreis on 11/12/16.
 */
public class Department
{
    private int id;
    private String name;
	private String admin;

    public Department()
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

	public String getAdmin()
	{
		return admin;
	}
	
	public void setAdmin(String admin)
	{
		this.admin = admin;
	}
}
