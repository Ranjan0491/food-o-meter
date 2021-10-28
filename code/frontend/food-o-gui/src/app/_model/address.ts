export class Address {
    id: string;
    line1: string;
    line2: string;
    line3: string;
    state: string;
    city: string;
    pinCode: string;

    constructor(id?: string,
        line1?: string,
        line2?: string,
        line3?: string,
        state?: string,
        city?: string,
        pinCode?: string) {
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
    }
}
