package services;

import models.Role;
import repository.RoleRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */
public class RoleService
{
    private RoleRepository roleRepository;

    public RoleService()
    {
        this.roleRepository = new RoleRepository();
    }

    public List<Role> getAll() throws SQLException
    {
        return this.roleRepository.getAll();
    }
}
