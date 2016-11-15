package services;

import models.Department;
import repository.DepartmentRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */
public class DepartmentService
{
    private DepartmentRepository departmentRepository;

    public DepartmentService()
    {
        this.departmentRepository = new DepartmentRepository();
    }

    public List<Department> getAll() throws SQLException
    {
        return this.departmentRepository.getAll();
    }
}
