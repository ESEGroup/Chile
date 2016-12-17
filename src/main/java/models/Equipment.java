package models;

import java.util.Date;

public class Equipment
{
	private int id;
	private String equipmentRegistry;
	private String description;
	private Date lastMaintenance;
	private String location;
	private int maintenancePeriodicity;		// days
	private boolean status;					// could be a enum type or a EquipmentStatus type

	private Department department;

	public Equipment()
	{
		this.department = new Department();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipmentRegistry()
	{
		return equipmentRegistry;
	}
	
	public void setEquipmentRegistry(String equipmentRegistry)
	{
		this.equipmentRegistry = equipmentRegistry;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;	
	}

	public Date getLastMaintenance()
	{
		return lastMaintenance;
	}
	
	public void setLastMaintenance(Date lastMaintenance)
	{
		this.lastMaintenance = lastMaintenance;	
	}

	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;	
	}

	public int getMaintenancePeriodicity()
	{
		return maintenancePeriodicity;
	}
	
	public void setMaintenancePeriodicity(int maintenancePeriodicity)
	{
		this.maintenancePeriodicity = maintenancePeriodicity;	
	}

	public boolean getStatus()
	{
		return status;
	}
	
	public void setStatus(boolean status)
	{
		this.status = status;	
	}

	public Department getDepartment()
	{
		return department;
	}
	
	public void setDepartment(Department department)
	{
		this.department = department;	
	}
}
