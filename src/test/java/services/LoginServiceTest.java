package services;

import models.User;
import models.dto.LoginDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.BaseRepository;

import java.sql.SQLException;

/**
 * Created by ericreis on 12/19/16.
 */
public class LoginServiceTest extends BaseRepository
{
    private LoginService loginService;

    @Before
    public void setUp() throws SQLException
    {
        this.loginService = new LoginService();

        String query = "SET AUTOCOMMIT = 0;";
        this.stmt.execute(query);
    }

    @After
    public void tearDown() throws SQLException
    {
        String query = "ROLLBACK;";
        this.stmt.execute(query);
    }

    @Test
    public void loginTest() throws SQLException
    {
        User expected = null;

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmployeeId("admin");
        loginDTO.setPassword("admin");
        Assert.assertNotEquals(expected, this.loginService.login(loginDTO));

        loginDTO.setEmployeeId("asdjhfklajs");
        loginDTO.setPassword("admin");
        Assert.assertEquals(expected, this.loginService.login(loginDTO));

        loginDTO.setEmployeeId("admin");
        loginDTO.setPassword("         ");
        Assert.assertEquals(expected, this.loginService.login(loginDTO));

        loginDTO.setEmployeeId("1234568904987651236");
        loginDTO.setPassword("admin");
        Assert.assertEquals(expected, this.loginService.login(loginDTO));

        loginDTO.setEmployeeId("123456");
        loginDTO.setPassword("admin");
        Assert.assertEquals(expected, this.loginService.login(loginDTO));

        loginDTO.setEmployeeId("12345678");
        loginDTO.setPassword("admin");
        Assert.assertEquals(expected, this.loginService.login(loginDTO));

        loginDTO.setEmployeeId("       ");
        loginDTO.setPassword("admin");
        Assert.assertEquals(expected, this.loginService.login(loginDTO));

        loginDTO.setEmployeeId("1234567");
        loginDTO.setPassword("1234567");
        Assert.assertNotEquals(expected, this.loginService.login(loginDTO));
    }
}
