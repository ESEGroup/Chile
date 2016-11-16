package repository;

import models.User;
import models.status.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */
@SuppressWarnings("Duplicates")
public class UserRepository extends BaseRepository
{
    private String query;
    private ResultSet rs;
    private HashMap<String, Object> params;

    public UserRepository()
    {
        super();
    }

    public User get(int id) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("user_id", id);

        this.query = "SELECT * FROM User u " +
                "JOIN Role r ON u.role_id = r.role_id " +
                "JOIN Department d on u.department_id = d.department_id " +
                "WHERE user_id = :user_id AND is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        User user = null;
        while (this.rs.next())
        {
            user = new User();
            user.setId(this.rs.getInt("user_id"));
            user.setEmployeeId(this.rs.getString("employee_id"));
            user.setCpf(this.rs.getString("cpf"));
            user.setRg(this.rs.getString("rg"));
            user.setRgIssuer(this.rs.getString("rg_issuer"));
            user.setName(this.rs.getString("u.name"));
            user.setPassword(this.rs.getString("password"));
            user.setBirthDate(this.rs.getDate("birth_date"));
            user.setCreationDate(this.rs.getDate("creation_date"));
            user.setDeleted(this.rs.getBoolean("is_deleted"));

            user.getRole().setId(this.rs.getInt("role_id"));
            user.getRole().setName(this.rs.getString("r.name"));

            user.getDepartment().setId(this.rs.getInt("department_id"));
            user.getDepartment().setName(this.rs.getString("d.name"));
        }

        return user;
    }

    public User get(String employeeId) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("employee_id", employeeId);

        this.query = "SELECT * FROM User u " +
                     "JOIN Role r ON u.role_id = r.role_id " +
                     "JOIN Department d on u.department_id = d.department_id " +
                     "WHERE employee_id = :employee_id AND is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        User user = null;
        while (this.rs.next())
        {
            user = new User();
            user.setId(this.rs.getInt("user_id"));
            user.setEmployeeId(this.rs.getString("employee_id"));
            user.setCpf(this.rs.getString("cpf"));
            user.setRg(this.rs.getString("rg"));
            user.setRgIssuer(this.rs.getString("rg_issuer"));
            user.setName(this.rs.getString("u.name"));
            user.setPassword(this.rs.getString("password"));
            user.setBirthDate(this.rs.getDate("birth_date"));
            user.setCreationDate(this.rs.getDate("creation_date"));
            user.setDeleted(this.rs.getBoolean("is_deleted"));

            user.getRole().setId(this.rs.getInt("role_id"));
            user.getRole().setName(this.rs.getString("r.name"));

            user.getDepartment().setId(this.rs.getInt("department_id"));
            user.getDepartment().setName(this.rs.getString("d.name"));
        }

        return user;
    }

    public List<User> getAll() throws SQLException
    {
        this.params = new HashMap<>();

        this.query = "SELECT * FROM User u " +
                     "JOIN Role r ON u.role_id = r.role_id " +
                     "JOIN Department d on u.department_id = d.department_id " +
                     "WHERE is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<User> users = new LinkedList<>();
        User user = null;
        while (this.rs.next())
        {
            user = new User();
            user.setId(this.rs.getInt("user_id"));
            user.setEmployeeId(this.rs.getString("employee_id"));
            user.setCpf(this.rs.getString("cpf"));
            user.setRg(this.rs.getString("rg"));
            user.setRgIssuer(this.rs.getString("rg_issuer"));
            user.setName(this.rs.getString("u.name"));
            user.setPassword(this.rs.getString("password"));
            user.setBirthDate(this.rs.getDate("birth_date"));
            user.setCreationDate(this.rs.getDate("creation_date"));
            user.setDeleted(this.rs.getBoolean("is_deleted"));

            user.getRole().setId(this.rs.getInt("role_id"));
            user.getRole().setName(this.rs.getString("r.name"));

            user.getDepartment().setId(this.rs.getInt("department_id"));
            user.getDepartment().setName(this.rs.getString("d.name"));

            users.add(user);
        }

        return users;
    }

    public int insert(User user) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("employee_id", user.getEmployeeId());
        this.params.put("cpf", user.getCpf());
        this.params.put("rg", user.getRg());
        this.params.put("rg_issuer", user.getRgIssuer());
        this.params.put("name", user.getName());
        this.params.put("password", user.getPassword());
        this.params.put("birth_date", user.getBirthDate());

        this.params.put("role_id", user.getRole().getId());

        this.params.put("department_id", user.getDepartment().getId());

        this.query = "INSERT INTO User (employee_id, cpf, rg, rg_issuer, name, password, birth_date, role_id, department_id) " +
                     "VALUES (:employee_id, :cpf, :rg, :rg_issuer, :name, :password, :birth_date, :role_id, :department_id)";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public int update(User user) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("user_id", user.getId());
        this.params.put("employee_id", user.getEmployeeId());
        this.params.put("cpf", user.getCpf());
        this.params.put("rg", user.getRg());
        this.params.put("rg_issuer", user.getRgIssuer());
        this.params.put("name", user.getName());
        this.params.put("password", user.getPassword());
        this.params.put("birth_date", user.getBirthDate());

        this.params.put("role_id", user.getRole().getId());

        this.params.put("department_id", user.getDepartment().getId());

        this.query = "UPDATE User SET employee_id = :employee_id, cpf = :cpf, rg = :rg, rg_issuer = :rg_issuer, name = :name, " +
                                     "password = :password, birth_date = :birth_date, role_id = :role_id, department_id = :department_id " +
                     "WHERE user_id = :user_id and is_deleted = 0";


        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public int softDelete(int id) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("user_id", id);

        this.query = "UPDATE User SET is_deleted = 1 " +
                     "WHERE user_id = :user_id";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }
}
