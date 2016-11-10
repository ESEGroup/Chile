package services;

import models.User;
import models.dto.LoginDTO;
import repository.UserRepository;
import services.util.HashService;

import java.sql.SQLException;

/**
 * Created by ericreis on 11/10/16.
 */
public class LoginService
{
    private UserRepository userRepository;
    private HashService hashService;

    public LoginService()
    {
        this.userRepository = new UserRepository();
        this.hashService = new HashService();
    }

    public User login(LoginDTO loginDTO) throws SQLException
    {
        User user = this.userRepository.get(loginDTO.getEmployeeId());

        if (user != null)
        {
            if (!this.hashService.checkSecureHashMatch(loginDTO.getPassword(), user.getPassword()))
            {
                user = null;
            }
        }

        return user;
    }
}
