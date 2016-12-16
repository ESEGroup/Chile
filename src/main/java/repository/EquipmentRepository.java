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

    public Equipment get(int id) throws SQLException{
        this.params = new HashMap<>();
        this.params.put("id", id);

        this.query = "SELECT * FROM Equipment e " +
                     "JOIN Department d ON e.department_id = d.department_id " +
                     "WHERE id = :id AND is deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        Equipment equipment = null;

        while(this.rs.next()){
            equipment = new Equipment();
            equipment.setId(this.rs.getInt("id"));
            equipment.setEquipmentId(this.rs.getString("equipment_Id"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setStatus(this.rs.getBoolean("status"));

            equipment.getDepartment().setId(this.rs.getInt("department_id"));
            equipment.getDepartment().setName(this.rs.getString("d.name"));

        }

        return equipment;
    }

    public Equipment get(String equipmentId) throws SQLException{
        this.params = new HashMap<>();
        this.params.put("equipment_id", equipmentId);

        this.query = "SELECT * FROM Equipment e " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "WHERE equipment_id = :equipment_id AND is deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        Equipment equipment = null;

        while(this.rs.next()){
            equipment = new Equipment();
            equipment.setId(this.rs.getInt("id"));
            equipment.setEquipmentId(this.rs.getString("equipment_Id"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setStatus(this.rs.getBoolean("status"));

            equipment.getDepartment().setId(this.rs.getInt("department_id"));
            equipment.getDepartment().setName(this.rs.getString("d.name"));

        }

        return equipment;
    }

    public List<Equipment> getAll() throws SQLException{
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Equipment e" +
                     "JOIN Department ON e.department_id = d.department_id" +
                     "WHERE is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<Equipment> equipments = new LinkedList<>();
        Equipment equipment = null;
        while(this.rs.next()){
            equipment = new Equipment();
            equipment.setId(this.rs.getInt("id"));
            equipment.setEquipmentId(this.rs.getString("equipment_id"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setStatus(this.rs.getBoolean("status"));

            equipment.getDepartment().setId(this.rs.getInt("department_id"));
            equipment.getDepartment().setName(this.rs.getString("d.name"));

            equipments.add(equipment);
        }
        return equipments;
    }


    public int insert(Equipment equipment) throws SQLException {
        this.params = new HashMap<>;
        this.params.put("equipment_id", equipment.getEquipmentId());
        this.params.put("description", equipment.getDescription());
        this.params.put("last_maintenance", equipment.getLastMaintenance());
        this.params.put("maintenance_periodicity", equipment.getMaintenancePeriodicity());
        this.params.put("location", equipment.getLocation());
        this.params.put("status", equipment.getStatus());

        this.params.put("department_id", equipment.getDepartment().getId());

        this.query = "INSERT INTO Equipment (equipment_id, description, last_maintenance, location, status, department_id)" +
                     "VALUES (:equipment_id, :description, :last_maintenance, :maintenance_periodicity, :location, :status, :department_id)";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public int update(Equipment equipment) throws SQLException{
        this.params = new HashMap<>;
        this.params.put("equipment_id", equipment.getEquipmentId());
        this.params.put("description", equipment.getDescription());
        this.params.put("last_maintenance", equipment.getLastMaintenance());
        this.params.put("location", equipment.getLocation());
        this.params.put("status", equipment.getStatus());

        this.params.put("department_id", equipment.getDepartment().getId());

        this.query = "UPDATE Equipment SET equipment_id = :equipment_id, description = :description, last_maintenance = :last_maintenance, " +
                                            "location = :location, status = :status, department_id = :department_id" +
                     "WHERE id = :id and is_deleted = 0" ;
        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public int softDelete(int id) throws SQLException{
        this.params = new HashMap<>();
        this.params.put("equipment_id", id);

        this.query = "UPDATE Equipment SET is_deleted = 1 " +
                     "WHERE id = :id";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }



}
