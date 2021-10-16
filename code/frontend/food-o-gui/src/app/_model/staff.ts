export class Staff {
    id: string;
    firstName: string;
    lastName: string;
    phone: string;
    userRole: string;

    constructor(id?: string,
        firstName?: string,
        lastName?: string,
        phone?: string,
        userRole?: string) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.userRole = userRole;
    }
}
