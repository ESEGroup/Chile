namespace App.Interfaces {

    export interface IUser {
        id: number;
        firstName: string;
        lastName: string;
        username: string;
        password: string;
        email: string;
        educationalInstitution: string;
        academicEducation: string;
        telephone: string;
        creationDate: Date;
        birthDate: Date;
        curriculumVitaePath: string;
    }

}