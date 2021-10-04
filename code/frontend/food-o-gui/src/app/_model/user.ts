import { Address } from "cluster";

export class User {
    id: String;
    firstName: String;
    lastName: String;
    phone: String;
    email: String;
    dob: Date;
    addresses: Address[];
    password: String;
    userRole: String;

    constructor(id?: String,
        firstName?: String,
        lastName?: String,
        phone?: String,
        email?: String,
        dob?: Date,
        addresses?: Address[],
        password?: String,
        userRole?: String) {
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
