package models;

import java.util.Date;

/**
 * Created by antonio-galvao on 14/12/16.
 */

public class ScheduledMaintenance {
    protected int equipmentId;
    protected Date date;

    public ScheduledMaintenance() {
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

