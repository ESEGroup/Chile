package models;

/**
 * Created by antonio-galvao on 14/12/16.
 */
public class MaintenanceLog extends ScheduledMaintenance{
    private String observations;
    private boolean equipmentStatus;

    public MaintenanceLog() {
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public boolean getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(boolean equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public void updateEquipmentStatus(Equipment equipment) {
        equipment.setStatus(this.equipmentStatus);
    }

    public void updateEquipmentLastMaintenance(Equipment equipment) {
        equipment.setLastMaintenance(this.date);
    }
}