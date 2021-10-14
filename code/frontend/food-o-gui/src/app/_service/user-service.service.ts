import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Address } from '../_model/address';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private httpOptions = {
    headers: new HttpHeaders({
      'accept': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  public getAllAddressesForCustomer(customerId: String) {
    return this.http.get<Address[]>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/" + customerId + "/addresses", this.httpOptions);
  }
}
