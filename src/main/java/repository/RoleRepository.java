package repository;

import models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */
@SuppressWarnings("Duplicates")
public class RoleRepository extends BaseRepository
{
    private String query;
    private ResultSet rs;
    private HashMap<String, Object> params;

    public RoleRepository()
    {
        super();
    }

    public List<Role> getAll() throws SQLException
    {
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Role";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<Role> roles = new LinkedList<>();
        Role role = null;
        while (this.rs.next())
        {
            role = new Role();
            role.setId(this.rs.getInt("role_id"));
            role.setName(this.rs.getString("name"));

            roles.add(role);
        }

        return roles;
    }
}
