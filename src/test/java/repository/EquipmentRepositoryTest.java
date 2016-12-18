package repository;

import models.Department;
import models.Equipment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio-galvao on 17/12/16.
 */
public class EquipmentRepositoryTest {
    private EquipmentRepository equipmentRepository;

    @Before
    public void setUp() throws SQLException {
        this.equipmentRepository = new EquipmentRepository();

        String query = "SET AUTOCOMMIT = 0;";
        this.equipmentRepository.stmt.execute(query);
    }

    @After
    public void tearDown() throws SQLException {
        String query = "ROLLBACK;";
        this.equipmentRepository.stmt.execute(query);
    }

    @Test
    public void insertEquipmentTest() throws SQLException {
        int expected = 1;

        Equipment equipment = new Equipment();
        equipment.setId(1);
        equipment.setEquipmentRegistry("1234567");
        equipment.setDescription("This is a equipment description test.");
        equipment.setLastMaintenance(new Date());
        equipment.setLocation("Equipment location test");
        equipment.setMaintenancePeriodicity(30);
        equipment.setStatus(true);
        equipment.setIs_deleted(false);

        Department department = new Department();
        department.setId(1);
        equipment.setDepartment(department);

        Assert.assertEquals(expected, this.equipmentRepository.insert(equipment));

    }

    @Test
    public void updateEquipmentTest() throws SQLException {
        int expected = 1;

        Equipment equipment = new Equipment();
        equipment.setId(1);
        equipment.setEquipmentRegistry("1234567");
        equipment.setDescription("This is a equipment description test.");
        equipment.setLastMaintenance(new Date());
        equipment.setLocation("Equipment location test");
        equipment.setMaintenancePeriodicity(30);
        equipment.setStatus(true);
        equipment.setIs_deleted(false);

        Department department = new Department();
        department.setId(1);
        equipment.setDepartment(department);

        Assert.assertEquals(expected, this.equipmentRepository.update(equipment));

        equipment.setId(-1);
        Assert.assertNotEquals(expected, this.equipmentRepository.update(equipment));

    }

    @Test
    public void getEquipmentByEquipmentRegistryTest() throws SQLException
    {
        Equipment expected = null;


        String equipmentId = "abcdefg";
        Assert.assertEquals(expected, this.equipmentRepository.get(equipmentId));

        equipmentId = "1111111";
        Assert.assertNotEquals(expected, this.equipmentRepository.get(equipmentId));
    }

    @Test
    public void getEquipmentByIdTest() throws SQLException
    {
        Equipment expected = null;

        int equipmentId = 1;
        Assert.assertNotEquals(expected, this.equipmentRepository.get(equipmentId));

        equipmentId = -1;
        Assert.assertNotEquals(expected, this.equipmentRepository.get(equipmentId));

    }

    @Test
    public void getAllEquipmentsTest() throws SQLException
    {
        List<Equipment> expected = new LinkedList<>();

        Equipment equipment = new Equipment();
        equipment.setId(1);
        equipment.setEquipmentRegistry("1234567");
        equipment.setDescription("This is a equipment description test.");
        equipment.setLastMaintenance(new Date());
        equipment.setLocation("Equipment location test");
        equipment.setMaintenancePeriodicity(30);
        equipment.setStatus(true);
        equipment.setIs_deleted(false);

        Department department = new Department();
        department.setId(1);
        equipment.setDepartment(department);

        expected.add(equipment);
        Assert.assertEquals(expected.size(), this.equipmentRepository.getAll().size());

    }

    @Test
    public void softDeleteTest() throws SQLException
    {
        Equipment expected = null;

        int equipmentId = 1;
        this.equipmentRepository.softDelete(equipmentId);
        Assert.assertEquals(expected, this.equipmentRepository.get(equipmentId));
    }



}
