package services;

import models.Equipment;
import models.status.EquipmentStatus;
import repository.EquipmentRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by antonio-galvao on 15/12/16.
 */
public class EquipmentService
{

    private EquipmentRepository equipmentRepository;

    public EquipmentService()
    {
        this.equipmentRepository = new EquipmentRepository();

    }

    public Equipment get(int id) throws SQLException
    {
        return this.equipmentRepository.get(id);
    }

    public Equipment get(String equipmentRegistry) throws SQLException
    {
        return this.equipmentRepository.get(equipmentRegistry);
    }


    public List<Equipment> getAll() throws SQLException
    {
        return this.equipmentRepository.getAll();
    }

    public int create(Equipment equipment) throws  SQLException
    {
        Equipment e = this.equipmentRepository.get(equipment.getEquipmentRegistry());

        if(e != null)
        {
            return EquipmentStatus.CONFLICT;
        }

        this.equipmentRepository.insert(equipment);

        return EquipmentStatus.OK;
    }

    public int update(Equipment equipment) throws SQLException
    {
        Equipment e = this.equipmentRepository.get(equipment.getId());

        if (e == null)
        {
            return EquipmentStatus.NOT_FOUND;
        }

        this.equipmentRepository.update(equipment);

        return EquipmentStatus.OK;
    }

    public int softDelete(int id) throws SQLException
    {
        Equipment e = this.equipmentRepository.get(id);

        if (e == null)
        {
            return EquipmentStatus.NOT_FOUND;
        }

        this.equipmentRepository.softDelete(id);

        return EquipmentStatus.OK;
    }


}
