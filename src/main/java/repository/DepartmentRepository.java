package repository;

import models.Department;
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
public class DepartmentRepository extends BaseRepository
{
    private String query;
    private ResultSet rs;
    private HashMap<String, Object> params;

    public DepartmentRepository()
    {
        super();
    }

    public List<Department> getAll() throws SQLException
    {
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Department";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<Department> departments = new LinkedList<>();
        Department department = null;
        while (this.rs.next())
        {
            department = new Department();
            department.setId(this.rs.getInt("department_id"));
            department.setName(this.rs.getString("name"));

            departments.add(department);
        }

        return departments;
    }
}
