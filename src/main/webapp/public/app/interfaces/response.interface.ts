namespace App.Interfaces {

    export interface IResponse {
        status: number;
        message: string;
        user: IUser;
    }

}