import { Pipe, PipeTransform } from "@angular/core";
import { Address } from "../_model/address";

@Pipe({name: 'addressTransform'})
export class AddressTransformPipe implements PipeTransform {
    transform(value: Address) {
        return value ? (value.line1.toString() + (value.line2 ? ", " + value.line2 : "") + (value.line3 ? ", " + value.line3 : "") 
        + ", " + value.city + ", " + value.state + ", Pin - " + value.pinCode) : "";
    }
}
