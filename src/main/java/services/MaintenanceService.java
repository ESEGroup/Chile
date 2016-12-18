package services;

import models.Maintenance;
import models.User;
import models.dto.LoginDTO;
import repository.MaintenanceRepository;
import repository.UserRepository;
import services.util.HashService;

import java.sql.SQLException;

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

    public void create(Maintenance maintenance) throws SQLException
    {
        // nome a ser decidido
        //Maintenance m = this.maintenanceRepository.getScheduledMaintenancesByEquipmentId(maintenance.getEquipment().getId());

        //if (m != null)
        {
            // nao deixa criar pois ja tem uma manutencao agendada em andamento para este equipamento
            // return MaintenanceStatus.CONFLICT;
        }

        this.maintenanceRepository.insert(maintenance);

        //return MaintenanceStatus.OK;
    }
}
