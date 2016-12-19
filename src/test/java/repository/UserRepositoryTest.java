package repository;

import models.Department;
import models.Role;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;

/**
 * Created by ericreis on 12/17/16.
 */
public class UserRepositoryTest
{
    private UserRepository userRepository;

    @Before
    public void setUp() throws SQLException
    {
        this.userRepository = new UserRepository();

        String query = "SET AUTOCOMMIT = 0;";
        this.userRepository.stmt.execute(query);
    }

    @After
    public void tearDown() throws SQLException
    {
        String query = "ROLLBACK;";
        this.userRepository.stmt.execute(query);
    }

    @Test
    public void insertUserTest() throws SQLException
    {
        int expected = 1;

        User user = new User();
        user.setEmployeeId("3094582");
        user.setCpf("12345678901");
        user.setRg("123456789");
        user.setRgIssuer("DETRAN-RJ");
        user.setName("teste");
        user.setPassword("teste");
        user.setBirthDate(new Date());

        Department department = new Department();
        department.setId(1);
        user.setDepartment(department);

        Role role = new Role();
        role.setId(1);
        user.setRole(role);

        Assert.assertEquals(expected, this.userRepository.insert(user));
    }

    @Test
    public void updateUserTest() throws SQLException
    {
        int expected = 1;

        User user = new User();
        user.setId(1);
        user.setEmployeeId("3094582");
        user.setCpf("12345678901");
        user.setRg("123456789");
        user.setRgIssuer("DETRAN-RJ");
        user.setName("teste");
        user.setPassword("teste");
        user.setBirthDate(new Date());

        Department department = new Department();
        department.setId(1);
        user.setDepartment(department);

        Role role = new Role();
        role.setId(1);
        user.setRole(role);

        Assert.assertEquals(expected, this.userRepository.update(user));

        expected = 0;
        user.setId(-1);
        Assert.assertEquals(expected, this.userRepository.update(user));
    }

    @Test
    public void getUserByEmployeeIdTest() throws SQLException
    {
        User expected = null;

        String employeeId = "admin";
        Assert.assertNotEquals(expected, this.userRepository.get(employeeId));

        employeeId = "abcdefg";
        Assert.assertEquals(expected, this.userRepository.get(employeeId));
    }

    @Test
    public void getUserByIdTest() throws SQLException
    {
        User expected = null;

        int userId = 1;
        Assert.assertNotEquals(expected, this.userRepository.get(userId));

        userId = -1;
        Assert.assertEquals(expected, this.userRepository.get(userId));
    }

    @Test
    public void getAllUsersTest() throws SQLException
    {
        List<User> expected = new LinkedList<>();

        User user = new User();
        user.setEmployeeId("3094582");
        user.setCpf("12345678901");
        user.setRg("123456789");
        user.setRgIssuer("DETRAN-RJ");
        user.setName("teste");
        user.setPassword("teste");
        user.setBirthDate(new Date());

        Department department = new Department();
        department.setId(1);
        user.setDepartment(department);

        Role role = new Role();
        role.setId(1);
        user.setRole(role);

        this.userRepository.insert(user);

        Assert.assertNotEquals(expected.size(), this.userRepository.getAll().size());
    }

    @Test
    public void softDeleteTest() throws SQLException
    {
        User expected = null;

        int userId = 1;
        this.userRepository.softDelete(userId);
        Assert.assertEquals(expected, this.userRepository.get(userId));
    }
}
