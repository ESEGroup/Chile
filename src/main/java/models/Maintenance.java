package models;

import java.util.Date;

/**
 * Created by antonio-galvao on 14/12/16.
 */

public class Maintenance
{
    private int id;
    private Date date;
    private Date finishedDate;
    private String description;
    private boolean finished;
    private boolean is_deleted;

    private User employee;
    private Equipment equipment;

    public Maintenance()
    {
        this.employee = new User();
        this.equipment = new Equipment();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getFinishedDate()
    {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate)
    {
        this.finishedDate = finishedDate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public User getEmployee()
    {
        return employee;
    }

    public void setEmployee(User employee)
    {
        this.employee = employee;
    }

    public Equipment getEquipment()
    {
        return equipment;
    }

    public void setEquipment(Equipment equipment)
    {
        this.equipment = equipment;
    }

    public boolean is_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}

