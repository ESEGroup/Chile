package repository;

import models.Department;
import models.Equipment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.sql.SQLException;
import java.util.Date;

/**
 * Created by antonio-galvao on 17/12/16.
 */
public class EquipmentRepositoryTest
{
    private EquipmentRepository equipmentRepository;

    @Before
    public void setUp() throws SQLException
    {
        this.equipmentRepository = new EquipmentRepository();

        String query = "SET AUTOCOMMIT = 0;";
        this.equipmentRepository.stmt.execute(query);
    }

    @After
    public void tearDown() throws SQLException
    {
        String query = "ROLLBACK;";
        this.equipmentRepository.stmt.execute(query);
    }

    @Test
    public void insertEquipmentTest() throws SQLException
    {
        int expected = 1;

        Equipment equipment = new Equipment();
        equipment.setEquipmentRegistry("1234567");
        equipment.setDescription("This is a equipment description test.");
        equipment.setLastMaintenance(new Date());
        equipment.setLocation("Equipment location test");
        equipment.setMaintenancePeriodicity(30);
        equipment.setStatus(true);

        Department department = new Department();
        department.setId(1);
        equipment.setDepartment(department);

        Assert.assertEquals(expected, this.equipmentRepository.insert(equipment));

    }
}
