export class Address {
    id: String;
    line1: String;
    line2: String;
    line3: String;
    state: String;
    city: String;
    pinCode: String;

    constructor(id?: String,
        line1?: String,
        line2?: String,
        line3?: String,
        state?: String,
        city?: String,
        pinCode?: String) {
            this.id = id;
            this.line1 = line1;
            this.line2 = line2;
            this.line3 = line3;
            this.state = state;
            this.city = city;
            this.pinCode = pinCode;
        }
}
