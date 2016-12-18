namespace App.Interfaces {

    export interface IEquipment {
        id: number;
        equipmentRegistry: string;
        description: string;
        lastMaintenance: Date;
        location: string;
        maintenancePeriodicity: number;
        status: boolean;
        isDeleted: boolean;

        department: IDepartment;
    }

}