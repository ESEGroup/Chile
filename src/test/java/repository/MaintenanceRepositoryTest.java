package repository;

import models.Department;
import models.Equipment;
import models.Maintenance;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio-galvao on 17/12/16.
 */
public class MaintenanceRepositoryTest
{
    private MaintenanceRepository maintenanceRepository;

    @Before
    public void setUp() throws SQLException
    {
        this.maintenanceRepository = new MaintenanceRepository();

        String query = "SET AUTOCOMMIT = 0;";
        this.maintenanceRepository.stmt.execute(query);
    }

    @After
    public void tearDown() throws SQLException
    {
        String query = "ROLLBACK;";
        this.maintenanceRepository.stmt.execute(query);
    }

    @Test
    public void insertMaintenanceTest() throws SQLException
    {
        int expected = 1;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,11,8);

        Maintenance maintenance = new Maintenance();
        maintenance.setDate(calendar.getTime());
        maintenance.setFinishedDate(calendar.getTime());
        maintenance.setDescription("Maintenance Description Test");
        maintenance.setFinished(true);
        maintenance.setIs_deleted(false);

        User employee = new User();
        employee.setId(1);
        maintenance.setEmployee(employee);

        Equipment equipment = new Equipment();
        equipment.setId(1);
        maintenance.setEquipment(equipment);

        Assert.assertEquals(expected, this.maintenanceRepository.insert(maintenance));
    }

    @Test
    public void getAllScheduledMaintenancesByEquipmentId() throws SQLException
    {
        List<Maintenance> expected = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,11,8);

        Maintenance maintenance = new Maintenance();
        maintenance.setDate(calendar.getTime());
        maintenance.setFinishedDate(calendar.getTime());
        maintenance.setDescription("Maintenance Description Test");
        maintenance.setFinished(true);
        maintenance.setIs_deleted(false);

        User employee = new User();
        employee.setId(1);
        maintenance.setEmployee(employee);

        Equipment equipment = new Equipment();
        equipment.setId(1);
        maintenance.setEquipment(equipment);

        expected.add(maintenance);

        Assert.assertNotEquals(0, this.maintenanceRepository.getScheduledMaintenancesByEquipmentId(1).size());

    }

}
