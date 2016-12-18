package repository;

import models.*;
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
    public void updateMaintenanceTest() throws SQLException
    {
        int expected = 1;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,11,8);

        Maintenance maintenance = new Maintenance();
        maintenance.setId(1);
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

        Assert.assertEquals(expected, this.maintenanceRepository.update(maintenance));

        expected = 0;
        maintenance.setId(-1);
        Assert.assertEquals(expected, this.maintenanceRepository.update(maintenance));
    }

    @Test
    public void getMaintenanceByEquipmentIdTest() throws SQLException
    {
        Maintenance expected = null;

        int maintenanceId = 1;
        Assert.assertNotEquals(expected, this.maintenanceRepository.getMaintenanceByEquipmentId(maintenanceId));

        maintenanceId = -1;
        Assert.assertEquals(expected, this.maintenanceRepository.getMaintenanceByEquipmentId(maintenanceId));
    }

    @Test
    public void getMaintenancesByEquipmentIdTest() throws SQLException
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

        Assert.assertEquals(expected.size(), this.maintenanceRepository.getMaintenancesByEquipmentId(1).size());

    }

    @Test
    public void getMaintenancesByIdTest() throws SQLException
    {
        Maintenance expected = null;

        int maintenanceId = 1;
        Assert.assertNotEquals(expected, this.maintenanceRepository.get(maintenanceId));

        maintenanceId = -1;
        Assert.assertEquals(expected, this.maintenanceRepository.get(maintenanceId));

    }

    @Test
    public void getAllMaintenancesTest() throws SQLException
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

        Assert.assertNotEquals(expected.size(), this.maintenanceRepository.getAll().size());
    }

    @Test
    public void softDeleteTest() throws SQLException
    {
        Maintenance expected = null;

        int maintenanceId = 1;
        this.maintenanceRepository.softDelete(maintenanceId);
        Assert.assertEquals(expected, this.maintenanceRepository.get(maintenanceId));
    }
}
