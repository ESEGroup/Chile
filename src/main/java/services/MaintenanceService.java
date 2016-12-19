package services;

import models.Maintenance;
import models.status.MaintenanceStatus;
import repository.MaintenanceRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ericreis on 11/10/16.
 */
public class MaintenanceService
{
    private MaintenanceRepository maintenanceRepository;

    public MaintenanceService()
    {
        this.maintenanceRepository = new MaintenanceRepository();
    }


    public List<Maintenance> getAlert() throws SQLException
    {
        return this.maintenanceRepository.getAlert();
    }


    public Maintenance get(int id) throws SQLException
    {
        return this.maintenanceRepository.get(id);
    }

    public List<Maintenance> getAll() throws SQLException
    {
        return this.maintenanceRepository.getAll();
    }

    public List<Maintenance> getAllFinished() throws SQLException
    {
        return this.maintenanceRepository.getAllFinished();
    }

    public List<Maintenance> getAllNotFinished() throws SQLException
    {
        return this.maintenanceRepository.getAllNotFinished();
    }

    public Maintenance getMaintenanceByEquipmentId(int equipmentId) throws SQLException
    {
        return this.maintenanceRepository.getMaintenanceByEquipmentId(equipmentId);
    }

    public List<Maintenance> getMaintenancesByEquipmentId(int equipmentId) throws SQLException
    {
        return this.maintenanceRepository.getMaintenancesByEquipmentId(equipmentId);
    }

    public int create(Maintenance maintenance) throws SQLException
    {
        Maintenance m = this.maintenanceRepository.getMaintenanceByEquipmentId(maintenance.getEquipment().getId());

        if (m != null)
        {
            return MaintenanceStatus.CONFLICT; //There is already a scheduled maintenance going on for this equipment
        }

        this.maintenanceRepository.insert(maintenance);

        return MaintenanceStatus.OK;
    }

    public int update(Maintenance maintenance) throws SQLException
    {
        Maintenance m = this.maintenanceRepository.get(maintenance.getId());

        if (m == null)
        {
            return MaintenanceStatus.NOT_FOUND;
        }

        this.maintenanceRepository.update(maintenance);

        return MaintenanceStatus.OK;
    }

    public int softDelete(int id) throws SQLException
    {
        Maintenance u = this.maintenanceRepository.get(id);

        if (u == null)
        {
            return MaintenanceStatus.NOT_FOUND;
        }

        this.maintenanceRepository.softDelete(id);

        return MaintenanceStatus.OK;
    }
}
