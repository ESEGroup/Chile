package services;

import models.User;
import models.status.UserStatus;
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

    private HashService hashService;

    public UserService()
    {
        this.userRepository = new UserRepository();

        this.hashService = new HashService();
    }

    public User get(int id) throws SQLException
    {
        return this.userRepository.get(id);
    }

    public List<User> getAll() throws SQLException
    {
        return this.userRepository.getAll();
    }

    public int create(User user) throws SQLException
    {
        User u = this.userRepository.get(user.getEmployeeId());

        if (u != null)
        {
            return UserStatus.CONFLICT;
        }

        user.setPassword(this.hashService.generateSecureHash(user.getPassword()));

        this.userRepository.insert(user);

        return UserStatus.OK;
    }

    public int update(User user) throws SQLException
    {
        User u = this.userRepository.get(user.getId());

        if (u == null)
        {
            return UserStatus.NOT_FOUND;
        }

        user.setPassword(this.hashService.generateSecureHash(user.getPassword()));

        this.userRepository.update(user);

        return UserStatus.OK;
    }

    public int softDelete(int id) throws SQLException
    {
        User u = this.userRepository.get(id);

        if (u == null)
        {
            return UserStatus.NOT_FOUND;
        }

        this.userRepository.softDelete(id);

        return UserStatus.OK;
    }
}
