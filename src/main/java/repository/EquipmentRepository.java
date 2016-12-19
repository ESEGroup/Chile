package repository;
import models.Equipment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio-galvao on 14/12/16.
 */

@SuppressWarnings("Duplicates")
public class EquipmentRepository extends BaseRepository{
    private String query;
    private ResultSet rs;
    private HashMap<String, Object> params;

    public EquipmentRepository() { super(); }

    public Equipment get(int id) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_id", id);

        this.query = "SELECT * FROM Equipment e " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "WHERE e.equipment_id = :equipment_id AND e.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        Equipment equipment = null;

        while(this.rs.next())
        {
            equipment = new Equipment();
            equipment.setId(this.rs.getInt("equipment_id"));
            equipment.setEquipmentRegistry(this.rs.getString("equipment_registry"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setStatus(this.rs.getBoolean("status"));
            equipment.setDeleted(this.rs.getBoolean("is_deleted"));

            equipment.getDepartment().setId(this.rs.getInt("department_id"));
            equipment.getDepartment().setName(this.rs.getString("d.name"));
        }

        return equipment;
    }

    public Equipment get(String equipmentRegistry) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_registry", equipmentRegistry);

        this.query = "SELECT * FROM Equipment e " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "WHERE e.equipment_registry = :equipment_registry AND e.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        Equipment equipment = null;

        while(this.rs.next())
        {
            equipment = new Equipment();
            equipment.setId(this.rs.getInt("equipment_id"));
            equipment.setEquipmentRegistry(this.rs.getString("equipment_registry"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setStatus(this.rs.getBoolean("status"));
            equipment.setDeleted(this.rs.getBoolean("is_deleted"));

            equipment.getDepartment().setId(this.rs.getInt("department_id"));
            equipment.getDepartment().setName(this.rs.getString("d.name"));
        }

        return equipment;
    }

    public List<Equipment> getAll() throws SQLException
    {
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Equipment e " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "WHERE e.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<Equipment> equipments = new LinkedList<>();
        Equipment equipment = null;
        while(this.rs.next()){
            equipment = new Equipment();
            equipment.setId(this.rs.getInt("equipment_id"));
            equipment.setEquipmentRegistry(this.rs.getString("equipment_registry"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setStatus(this.rs.getBoolean("status"));
            equipment.setDeleted(this.rs.getBoolean("is_deleted"));

            equipment.getDepartment().setId(this.rs.getInt("department_id"));
            equipment.getDepartment().setName(this.rs.getString("d.name"));

            equipments.add(equipment);
        }
        return equipments;
    }

    public List<Equipment> getAllEquipmentWithUnscheduledMaintenance() throws SQLException
    {
        this.params = new HashMap<>();

//        this.query = "SELECT * FROM Equipment e " +
//                "JOIN Department d ON e.department_id = d.department_id " +
//                "LEFT JOIN Maintenance m ON e.equipment_id = m.equipment_id " +
//                "WHERE m.equipment_id is null OR m.finished = 1";

        this.query = "SELECT    * " +
                     "FROM      Equipment e " +
                     "JOIN      Department d    ON e.department_id = d.department_id " +
                     "LEFT JOIN Maintenance m   ON e.equipment_id = m.equipment_id AND m.finished != 1 " +
                     "WHERE     m.maintenance_id is null";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<Equipment> equipments = new LinkedList<>();
        Equipment equipment = null;
        while(this.rs.next())
        {
            equipment = new Equipment();
            equipment.setId(this.rs.getInt("equipment_id"));
            equipment.setEquipmentRegistry(this.rs.getString("equipment_registry"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setStatus(this.rs.getBoolean("status"));
            equipment.setDeleted(this.rs.getBoolean("is_deleted"));

            equipment.getDepartment().setId(this.rs.getInt("department_id"));
            equipment.getDepartment().setName(this.rs.getString("d.name"));

            equipments.add(equipment);
        }

        return equipments;
    }

    public int insert(Equipment equipment) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_registry", equipment.getEquipmentRegistry());
        this.params.put("description", equipment.getDescription());
        this.params.put("last_maintenance", equipment.getLastMaintenance());
        this.params.put("maintenance_periodicity", equipment.getMaintenancePeriodicity());
        this.params.put("location", equipment.getLocation());
        this.params.put("status", equipment.getStatus());
        this.params.put("department_id", equipment.getDepartment().getId());

        this.query = "INSERT INTO Equipment (equipment_registry, description, last_maintenance, maintenance_periodicity, location, status, department_id, is_deleted)" +
                "VALUES (:equipment_registry, :description, :last_maintenance, :maintenance_periodicity, :location, :status, :department_id, :is_deleted)";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public int update(Equipment equipment) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_id", equipment.getId());
        this.params.put("equipment_registry", equipment.getEquipmentRegistry());
        this.params.put("description", equipment.getDescription());
        this.params.put("last_maintenance", equipment.getLastMaintenance());
        this.params.put("maintenance_periodicity", equipment.getMaintenancePeriodicity());
        this.params.put("location", equipment.getLocation());
        this.params.put("status", equipment.getStatus());
        this.params.put("department_id", equipment.getDepartment().getId());

        this.query = "UPDATE Equipment SET equipment_registry = :equipment_registry, description = :description, last_maintenance = :last_maintenance, " +
                "maintenance_periodicity = :maintenance_periodicity, location = :location, status = :status, department_id = :department_id " +
                "WHERE equipment_id = :equipment_id and is_deleted = 0" ;

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public int softDelete(int id) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_id", id);

        this.query = "UPDATE Equipment SET is_deleted = 1 " +
                "WHERE equipment_id = :equipment_id";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }


}