package services;

import models.Equipment;
import models.status.EquipmentStatus;
import repository.EquipmentRepository;
import services.util.HashService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by antonio-galvao on 15/12/16.
 */
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    private HashService hashService;

    public EquipmentService(){
        this.equipmentRepository = new EquipmentRepository();

        this.hashService = new HashService();
    }

    public Equipment get(int id) throws SQLException
    {
        return this.equipmentRepository.get(id);
    }

    public List<Equipment> getAll() throws SQLException
    {
        return this.equipmentRepository.getAll();
    }

    public int create(Equipment equipment) throws  SQLException
    {
        Equipment e = this.equipmentRepository.get(equipment.getEquipmentId());

        if(e != null){
            return EquipmentStatus.CONFLICT;
        }

        this.equipmentRepository.insert(equipment);

        return EquipmentStatus.OK;
    }

    public int update(Equipment equipment) throws SQLException
    {
        switch (this.equipmentRepository.update(equipment))
        {
            case 0:
                return EquipmentStatus.NOT_FOUND;
        }

        return EquipmentStatus.OK;
    }

    public int softDelete(int id) throws SQLException
    {
        switch (this.equipmentRepository.softDelete(id))
        {
            case 0:
                return EquipmentStatus.NOT_FOUND;
        }

        return EquipmentStatus.OK;
    }


}
