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

    public int insert(Maintenance maintenance) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("date", maintenance.getDate());
        this.params.put("finished_date", maintenance.getFinishedDate());
        this.params.put("description", maintenance.getDescription());
        this.params.put("finished", maintenance.isFinished());

        this.params.put("user_id", maintenance.getEmployee().getId());
        this.params.put("equipment_id", maintenance.getEquipment().getId());

        this.query = "";

        this.createNamedParameterStatement(this.query, this.params);

        return this.namedStmt.executeUpdate();
    }

    public Maintenance getScheduledMaintenanceByEquipmentId(int equipmentId) throws SQLException
    {
        this.params = new HashMap<>();
        this.params.put("equipment_id", equipmentId);

        this.query = "SELECT * FROM Maintenance m " +
                     "WHERE m.finished = 0 AND m.equipment_id = :equipment_id AND m.is_deleted = 0";

        this.createNamedParameterStatement(this.query, this.params);

        Maintenance maintenance = null;
        while (this.rs.next())
        {
            maintenance = new Maintenance();
            maintenance.setId(this.rs.getInt("maintenance_id"));
            maintenance.setDate(this.rs.getDate("date"));
            maintenance.setFinishedDate(this.rs.getDate("finished_date"));
            maintenance.setDescription(this.rs.getString("description"));
            maintenance.setFinished(this.rs.getBoolean("finished"));
        }

        return maintenance;
    }

}
