namespace App.Interfaces {

    export interface IUser {
        id: number;
        name: string;
        cpf: string;
        rg: string;
        rgIssuer: string;
        employeeId: string;
        password: string;
        email: string;
        telephone: string;
        birthDate: Date;
        creationDate: Date;
        isDeleted: boolean;

        role: IRole;
        department: IDepartment;
    }

}