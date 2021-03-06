package repository;

import models.Maintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */
@SuppressWarnings("Duplicates")
public class MaintenanceRepository extends BaseRepository
{
    private String query;
    private ResultSet rs;
    private HashMap<String, Object> params;

    public MaintenanceRepository()
    {
        super();
    }

    public Maintenance get(int id) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("maintenance_id", id);

        this.query = "SELECT * FROM Maintenance m " +
                "JOIN User u ON u.user_id = m.user_id " +
                "JOIN Equipment e ON e.equipment_id = m.equipment_id " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "JOIN Role r on u.role_id = r.role_id " +
                "WHERE m.maintenance_id = :maintenance_id AND m.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();


        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
            maintenance.setDeleted(this.rs.getBoolean("is_deleted"));

            maintenance.getEmployee().setId(this.rs.getInt("u.user_id"));
            maintenance.getEmployee().setEmployeeId(this.rs.getString("u.employee_id"));
            maintenance.getEmployee().setCpf(this.rs.getString("u.cpf"));
            maintenance.getEmployee().setRg(this.rs.getString("u.rg"));
            maintenance.getEmployee().setRgIssuer(this.rs.getString("u.rg_issuer"));
            maintenance.getEmployee().setName(this.rs.getString("u.name"));
            maintenance.getEmployee().setEmail(this.rs.getString("u.email"));
            maintenance.getEmployee().setTelephone(this.rs.getString("u.telephone"));
            maintenance.getEmployee().setPassword(this.rs.getString("u.password"));
            maintenance.getEmployee().setBirthDate(this.rs.getDate("u.birth_date"));
            maintenance.getEmployee().setCreationDate(this.rs.getDate("u.creation_date"));
            maintenance.getEmployee().setDeleted(this.rs.getBoolean("u.is_deleted"));

            maintenance.getEmployee().getRole().setId(this.rs.getInt("r.role_id"));
            maintenance.getEmployee().getRole().setName(this.rs.getString("r.name"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_Id"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));
        }

        return maintenance;
    }

    public List<Maintenance> getAlert() throws SQLException
    {
        this.params = new HashMap<>();

        // Select equipment that has maintenance proximity before today's data and up to 3 days after
        this.query = "SELECT get_next_maintenance(e.equipment_id) next_maintenance, e.*,d.* from Equipment e " +
                "JOIN Department d on e.department_id= d.department_id " +
                "WHERE is_deleted = false AND get_next_maintenance(e.equipment_id) != '0000-00-00' " +
                "AND (get_next_maintenance(e.equipment_id) <= DATE_ADD(CURDATE(), INTERVAL + 3 DAY))";


        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<Maintenance> maintenances = new LinkedList<>();
        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setDate(this.rs.getDate("next_maintenance"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_Id"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));

            maintenances.add(maintenance);
        }

        return maintenances;
    }

    // Maintenances done
    public List<Maintenance> getAll() throws SQLException
    {
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Maintenance m " +
                "JOIN User u ON u.user_id = m.user_id " +
                "JOIN Equipment e ON e.equipment_id = m.equipment_id " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "JOIN Role r on u.role_id = r.role_id " +
                "WHERE m.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();


        List<Maintenance> maintenances = new LinkedList<>();
        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
            maintenance.setDeleted(this.rs.getBoolean("is_deleted"));

            maintenance.getEmployee().setId(this.rs.getInt("u.user_id"));
            maintenance.getEmployee().setEmployeeId(this.rs.getString("u.employee_id"));
            maintenance.getEmployee().setCpf(this.rs.getString("u.cpf"));
            maintenance.getEmployee().setRg(this.rs.getString("u.rg"));
            maintenance.getEmployee().setRgIssuer(this.rs.getString("u.rg_issuer"));
            maintenance.getEmployee().setName(this.rs.getString("u.name"));
            maintenance.getEmployee().setEmail(this.rs.getString("u.email"));
            maintenance.getEmployee().setTelephone(this.rs.getString("u.telephone"));
            maintenance.getEmployee().setPassword(this.rs.getString("u.password"));
            maintenance.getEmployee().setBirthDate(this.rs.getDate("u.birth_date"));
            maintenance.getEmployee().setCreationDate(this.rs.getDate("u.creation_date"));
            maintenance.getEmployee().setDeleted(this.rs.getBoolean("u.is_deleted"));

            maintenance.getEmployee().getRole().setId(this.rs.getInt("r.role_id"));
            maintenance.getEmployee().getRole().setName(this.rs.getString("r.name"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_registry"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));

            maintenances.add(maintenance);
        }

        return maintenances;
    }

    // Maintenances done
    public List<Maintenance> getAllFinished() throws SQLException
    {
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Maintenance m " +
                "JOIN User u ON u.user_id = m.user_id " +
                "JOIN Equipment e ON e.equipment_id = m.equipment_id " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "JOIN Role r on u.role_id = r.role_id " +
                "WHERE m.finished = 1 AND m.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();


        List<Maintenance> maintenances = new LinkedList<>();
        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
            maintenance.setDeleted(this.rs.getBoolean("is_deleted"));

            maintenance.getEmployee().setId(this.rs.getInt("u.user_id"));
            maintenance.getEmployee().setEmployeeId(this.rs.getString("u.employee_id"));
            maintenance.getEmployee().setCpf(this.rs.getString("u.cpf"));
            maintenance.getEmployee().setRg(this.rs.getString("u.rg"));
            maintenance.getEmployee().setRgIssuer(this.rs.getString("u.rg_issuer"));
            maintenance.getEmployee().setName(this.rs.getString("u.name"));
            maintenance.getEmployee().setEmail(this.rs.getString("u.email"));
            maintenance.getEmployee().setTelephone(this.rs.getString("u.telephone"));
            maintenance.getEmployee().setPassword(this.rs.getString("u.password"));
            maintenance.getEmployee().setBirthDate(this.rs.getDate("u.birth_date"));
            maintenance.getEmployee().setCreationDate(this.rs.getDate("u.creation_date"));
            maintenance.getEmployee().setDeleted(this.rs.getBoolean("u.is_deleted"));

            maintenance.getEmployee().getRole().setId(this.rs.getInt("r.role_id"));
            maintenance.getEmployee().getRole().setName(this.rs.getString("r.name"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_registry"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));

            maintenances.add(maintenance);
        }

        return maintenances;
    }

    // Maintenances scheduled
    public List<Maintenance> getAllNotFinished() throws SQLException
    {
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Maintenance m " +
                "JOIN User u ON u.user_id = m.user_id " +
                "JOIN Equipment e ON e.equipment_id = m.equipment_id " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "JOIN Role r on u.role_id = r.role_id " +
                "WHERE m.finished = 0 AND m.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();


        List<Maintenance> maintenances = new LinkedList<>();
        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
            maintenance.setDeleted(this.rs.getBoolean("is_deleted"));

            maintenance.getEmployee().setId(this.rs.getInt("u.user_id"));
            maintenance.getEmployee().setEmployeeId(this.rs.getString("u.employee_id"));
            maintenance.getEmployee().setCpf(this.rs.getString("u.cpf"));
            maintenance.getEmployee().setRg(this.rs.getString("u.rg"));
            maintenance.getEmployee().setRgIssuer(this.rs.getString("u.rg_issuer"));
            maintenance.getEmployee().setName(this.rs.getString("u.name"));
            maintenance.getEmployee().setEmail(this.rs.getString("u.email"));
            maintenance.getEmployee().setTelephone(this.rs.getString("u.telephone"));
            maintenance.getEmployee().setPassword(this.rs.getString("u.password"));
            maintenance.getEmployee().setBirthDate(this.rs.getDate("u.birth_date"));
            maintenance.getEmployee().setCreationDate(this.rs.getDate("u.creation_date"));
            maintenance.getEmployee().setDeleted(this.rs.getBoolean("u.is_deleted"));

            maintenance.getEmployee().getRole().setId(this.rs.getInt("r.role_id"));
            maintenance.getEmployee().getRole().setName(this.rs.getString("r.name"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_registry"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));

            maintenances.add(maintenance);
        }

        return maintenances;
    }

    public List<Maintenance> getAllNotFinishedByUserId(int userId) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("user_id", userId);

        this.query = "SELECT * FROM Maintenance m " +
                "JOIN User u ON u.user_id = m.user_id " +
                "JOIN Equipment e ON e.equipment_id = m.equipment_id " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "JOIN Role r on u.role_id = r.role_id " +
                "WHERE m.finished = 0 AND m.is_deleted = 0 AND u.user_id = :user_id";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();


        List<Maintenance> maintenances = new LinkedList<>();
        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
            maintenance.setDeleted(this.rs.getBoolean("is_deleted"));

            maintenance.getEmployee().setId(this.rs.getInt("u.user_id"));
            maintenance.getEmployee().setEmployeeId(this.rs.getString("u.employee_id"));
            maintenance.getEmployee().setCpf(this.rs.getString("u.cpf"));
            maintenance.getEmployee().setRg(this.rs.getString("u.rg"));
            maintenance.getEmployee().setRgIssuer(this.rs.getString("u.rg_issuer"));
            maintenance.getEmployee().setName(this.rs.getString("u.name"));
            maintenance.getEmployee().setEmail(this.rs.getString("u.email"));
            maintenance.getEmployee().setTelephone(this.rs.getString("u.telephone"));
            maintenance.getEmployee().setPassword(this.rs.getString("u.password"));
            maintenance.getEmployee().setBirthDate(this.rs.getDate("u.birth_date"));
            maintenance.getEmployee().setCreationDate(this.rs.getDate("u.creation_date"));
            maintenance.getEmployee().setDeleted(this.rs.getBoolean("u.is_deleted"));

            maintenance.getEmployee().getRole().setId(this.rs.getInt("r.role_id"));
            maintenance.getEmployee().getRole().setName(this.rs.getString("r.name"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_registry"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));

            maintenances.add(maintenance);
        }

        return maintenances;
    }

    // Return only the maintenance that was not finished
    public Maintenance getMaintenanceByEquipmentId(int equipmentId) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_id", equipmentId);

        this.query = "SELECT * FROM Maintenance m " +
                "JOIN User u ON u.user_id = m.user_id " +
                "JOIN Equipment e ON e.equipment_id = m.equipment_id " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "JOIN Role r on u.role_id = r.role_id " +
                "WHERE m.finished = 0 AND m.equipment_id = :equipment_id AND m.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();


        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
            maintenance.setDeleted(this.rs.getBoolean("is_deleted"));

            maintenance.getEmployee().setId(this.rs.getInt("u.user_id"));
            maintenance.getEmployee().setEmployeeId(this.rs.getString("u.employee_id"));
            maintenance.getEmployee().setCpf(this.rs.getString("u.cpf"));
            maintenance.getEmployee().setRg(this.rs.getString("u.rg"));
            maintenance.getEmployee().setRgIssuer(this.rs.getString("u.rg_issuer"));
            maintenance.getEmployee().setName(this.rs.getString("u.name"));
            maintenance.getEmployee().setEmail(this.rs.getString("u.email"));
            maintenance.getEmployee().setTelephone(this.rs.getString("u.telephone"));
            maintenance.getEmployee().setPassword(this.rs.getString("u.password"));
            maintenance.getEmployee().setBirthDate(this.rs.getDate("u.birth_date"));
            maintenance.getEmployee().setCreationDate(this.rs.getDate("u.creation_date"));
            maintenance.getEmployee().setDeleted(this.rs.getBoolean("u.is_deleted"));

            maintenance.getEmployee().getRole().setId(this.rs.getInt("r.role_id"));
            maintenance.getEmployee().getRole().setName(this.rs.getString("r.name"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_registry"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));

        }

        return maintenance;
    }

    //Return all the maintenances according to the equipment
    public List<Maintenance> getAllMaintenanceByEquipmentId(int equipmentId) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_id", equipmentId);

        this.query = "SELECT * FROM Maintenance m " +
                "JOIN User u ON u.user_id = m.user_id " +
                "JOIN Equipment e ON e.equipment_id = m.equipment_id " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "JOIN Role r on u.role_id = r.role_id " +
                "WHERE m.equipment_id = :equipment_id AND m.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();


        List<Maintenance> maintenances = new LinkedList<>();
        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
            maintenance.setDeleted(this.rs.getBoolean("is_deleted"));

            maintenance.getEmployee().setId(this.rs.getInt("u.user_id"));
            maintenance.getEmployee().setEmployeeId(this.rs.getString("u.employee_id"));
            maintenance.getEmployee().setCpf(this.rs.getString("u.cpf"));
            maintenance.getEmployee().setRg(this.rs.getString("u.rg"));
            maintenance.getEmployee().setRgIssuer(this.rs.getString("u.rg_issuer"));
            maintenance.getEmployee().setName(this.rs.getString("u.name"));
            maintenance.getEmployee().setEmail(this.rs.getString("u.email"));
            maintenance.getEmployee().setTelephone(this.rs.getString("u.telephone"));
            maintenance.getEmployee().setPassword(this.rs.getString("u.password"));
            maintenance.getEmployee().setBirthDate(this.rs.getDate("u.birth_date"));
            maintenance.getEmployee().setCreationDate(this.rs.getDate("u.creation_date"));
            maintenance.getEmployee().setDeleted(this.rs.getBoolean("u.is_deleted"));

            maintenance.getEmployee().getRole().setId(this.rs.getInt("r.role_id"));
            maintenance.getEmployee().getRole().setName(this.rs.getString("r.name"));

            maintenance.getEquipment().setId(this.rs.getInt("e.equipment_id"));
            maintenance.getEquipment().setEquipmentRegistry(this.rs.getString("e.equipment_registry"));
            maintenance.getEquipment().setDescription(this.rs.getString("e.description"));
            maintenance.getEquipment().setLastMaintenance(this.rs.getDate("e.last_maintenance"));
            maintenance.getEquipment().setLocation(this.rs.getString("e.location"));
            maintenance.getEquipment().setMaintenancePeriodicity(this.rs.getInt("e.maintenance_periodicity"));
            maintenance.getEquipment().setStatus(this.rs.getBoolean("e.status"));
            maintenance.getEquipment().setDeleted(this.rs.getBoolean("e.is_deleted"));

            maintenance.getEquipment().getDepartment().setId(this.rs.getInt("d.department_id"));
            maintenance.getEquipment().getDepartment().setName(this.rs.getString("d.name"));

            maintenances.add(maintenance);
        }

        return maintenances;
    }



    public int insert(Maintenance maintenance) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("date", maintenance.getDate());
        this.params.put("finished_date", maintenance.getFinishedDate());
        this.params.put("description", maintenance.getDescription());
        this.params.put("finished", maintenance.isFinished());

        this.params.put("user_id", maintenance.getEmployee().getId());
        this.params.put("equipment_id", maintenance.getEquipment().getId());

        this.query = "INSERT INTO Maintenance (date, finished_date, description, user_id, equipment_id, finished)" +
                "VALUES (:date, :finished_date, :description, :user_id, :equipment_id, :finished)";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public int update(Maintenance maintenance) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("maintenance_id", maintenance.getId());
        this.params.put("date", maintenance.getDate());
        this.params.put("finished_date", maintenance.getFinishedDate());
        this.params.put("description", maintenance.getDescription());
        this.params.put("finished", maintenance.isFinished());
        this.params.put("is_deleted", maintenance.isDeleted());

        this.params.put("user_id", maintenance.getEmployee().getId());

        this.params.put("equipment_id", maintenance.getEquipment().getId());


        this.query = "UPDATE Maintenance SET date = :date, finished_date = :finished_date, description = :description, " +
                "finished = :finished, is_deleted = :is_deleted, user_id = :user_id, equipment_id = :equipment_id " +
                "WHERE maintenance_id = :maintenance_id AND is_deleted = 0";


        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }


    public int softDelete(int id) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("maintenance_id", id);

        this.query = "UPDATE Maintenance SET is_deleted = 1 " +
                "WHERE maintenance_id = :maintenance_id";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

}
