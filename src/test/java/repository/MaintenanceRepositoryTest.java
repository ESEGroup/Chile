package repository;

import models.Equipment;
import models.Maintenance;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

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

        Maintenance maintenance = new Maintenance();
        maintenance.setDate(new Date());
        maintenance.setFinishedDate(new Date());
        maintenance.setDescription("Maintenance Description Test");
        maintenance.setFinished(true);

        User employee = new User();
        employee.setId(1);
        maintenance.setEmployee(employee);

        Equipment equipment = new Equipment();
        equipment.setId(1);
        maintenance.setEquipment(equipment);

        Assert.assertEquals(expected, this.maintenanceRepository.insert(maintenance));
    }
}
