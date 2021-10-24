import { Address } from "./address";
import { User } from "./user";

export class Staff {
    id: string;
    firstName: string;
    lastName: string;
    phone: string;
    email: string;
    dob: string;
    addresses: Address[];
    userRole: string;
    status: string;

    constructor(id?: string,
        firstName?: string,
        lastName?: string,
        phone?: string,
        email?: string,
        dob?: string,
        addresses?: Address[],
        userRole?: string,
        status?: string) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.userRole = userRole;
        this.email = email;
        this.dob = dob;
        this.addresses = addresses;
        this.status = status;
    }

    public toUser() {
        return new User(this.id, this.firstName, this.lastName, this.phone, this.email, this.dob, this.addresses, null, this.userRole, this.status);
    }
}
