package repository;

import models.Department;
import models.Role;
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
public class RoleRepositoryTest
{
    private RoleRepository roleRepository;

    @Before
    public void setUp() throws SQLException
    {
        this.roleRepository = new RoleRepository();

        String query = "SET AUTOCOMMIT = 0;";
        this.roleRepository.stmt.execute(query);
    }

    @After
    public void tearDown() throws SQLException
    {
        String query = "ROLLBACK;";
        this.roleRepository.stmt.execute(query);
    }

    @Test
    public void getAllRolesTest() throws SQLException
    {
        List<Role> expected = new LinkedList<>();

        Role role = new Role();
        role.setName("Role Test Name");

        expected.add(role);
        Assert.assertNotEquals(expected.size(), this.roleRepository.getAll().size());

    }
}
