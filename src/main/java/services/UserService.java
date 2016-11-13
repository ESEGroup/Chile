package services;

import models.User;
import models.dto.LoginDTO;
import repository.UserRepository;
import services.util.HashService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */
public class UserService
{
    private UserRepository userRepository;

    public UserService()
    {
        this.userRepository = new UserRepository();
    }

    public List<User> getAll() throws SQLException
    {
        return this.userRepository.getAll();
    }
}
