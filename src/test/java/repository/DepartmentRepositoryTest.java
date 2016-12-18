package repository;

import models.Department;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio-galvao on 17/12/16.
 */
public class DepartmentRepositoryTest
{
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() throws SQLException
    {
        this.departmentRepository = new DepartmentRepository();

        String query = "SET AUTOCOMMIT = 0;";
        this.departmentRepository.stmt.execute(query);
    }

    @After
    public void tearDown() throws SQLException
    {
        String query = "ROLLBACK;";
        this.departmentRepository.stmt.execute(query);
    }

    @Test
    public void getAllDepartmentsTest() throws SQLException
    {
        List<Department> expected = new LinkedList<>();

        Department department = new Department();
        department.setName("Department Test Name");

        expected.add(department);
        Assert.assertEquals(expected.size(), this.departmentRepository.getAll().size());

    }
}
