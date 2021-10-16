import { Address } from "./address";

export class User {
    id: string;
    firstName: string;
    lastName: string;
    phone: string;
    email: string;
    dob: Date;
    addresses: Address[];
    password: string;
    userRole: string;

    constructor(id?: string,
        firstName?: string,
        lastName?: string,
        phone?: string,
        email?: string,
        dob?: Date,
        addresses?: Address[],
        password?: string,
        userRole?: string) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.addresses = addresses;
        this.password = password;
        this.userRole = userRole;
    }
}
