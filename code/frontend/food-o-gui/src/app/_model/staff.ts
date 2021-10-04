export class Staff {
    id: String;
    firstName: String;
    lastName: String;
    phone: String;
    userRole: String;

    constructor(id?: String,
        firstName?: String,
        lastName?: String,
        phone?: String,
        userRole?: String) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.userRole = userRole;
        }
}
