namespace App.Interfaces {

    export interface IMaintenance {
        id: number;
        date: Date;
        finishedDate: Date;
        description: string;
        finished: boolean;
        isDeleted: boolean;

        employee: IUser;
        equipment: IEquipment;
    }

}