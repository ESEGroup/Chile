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

    public List<Equipment> getAll() throws SQLException{
        this.params = new HashMap<>();

        this.query = "SELECT * FROM Equipment";

        this.createNamedParameterStatement(this.query, this.params);

        this.rs = this.namedStmt.executeQuery();

        List<Equipment> equipments = new LinkedList<>();
        Equipment equipment = null;
        while(this.rs.next()){
            equipment = new Equipment();
            equipment.setEquipmentId(this.rs.getInt("equipment_id"));
            equipment.setDescription(this.rs.getString("description"));
            equipment.setLastMaintenance(this.rs.getDate("last_maintenance"));
            equipment.setMaintenancePeriodicity(this.rs.getInt("maintenance_periodicity"));
            equipment.setLocation(this.rs.getString("location"));
            equipment.setStatus(this.rs.getBoolean("status"));
            equipment.setDepartment();

            equipments.add(equipment);
        }
        return equipments;
    }


}
