import { Address } from "./address";

export class Staff {
    id: string;
    firstName: string;
    lastName: string;
    phone: string;
    email: string;
    dob: string;
    addresses: Address[];
    userRole: string;

    constructor(id?: string,
        firstName?: string,
        lastName?: string,
        phone?: string,
        email?: string,
        dob?: string,
        addresses?: Address[],
        userRole?: string) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.userRole = userRole;
        this.email = email;
        this.dob = dob;
        this.addresses = addresses;
    }
}
