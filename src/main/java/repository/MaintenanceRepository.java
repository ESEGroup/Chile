package repository;

import models.Equipment;
import models.Maintenance;
import models.User;

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

    public void insert() throws SQLException
    {

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
            // ...
        }


        return maintenance;
    }
}
