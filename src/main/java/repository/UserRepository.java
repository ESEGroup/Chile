package repository;

import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by ericreis on 11/10/16.
 */
public class UserRepository extends BaseRepository
{
    private String query;
    private ResultSet rs;
    private HashMap<String, Object> params;

    public UserRepository()
    {
        super();
    }

    public User get (String employeeId) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("employee_id", employeeId);

        this.query = "select * from User where employee_id = :employee_id";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        User user = null;
        while(this.rs.next())
        {
            user = new User();
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setCpf(rs.getString("cpf"));
            user.setRg(rs.getString("rg"));
            user.setIssuer(rs.getString("issuer"));
            user.setEmployeeId(rs.getString("employee_id"));
            user.setPassword(rs.getString("password"));
            user.setBirthDate(rs.getDate("birth_date"));
            user.setAdmin(rs.getBoolean("is_admin"));
        }

        return user;
    }
}
