import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Address } from '../_model/address';
import { User } from '../_model/user';

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

  public getAllAddressesForCustomer(customerId: string) {
    return this.http.get<Address[]>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/" + customerId + "/addresses", this.httpOptions);
  }

  public getUserDetails(customerId: string) {
    return this.http.get<User>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/" + customerId, this.httpOptions);
  }

  public createNewUser(user: User) {
    return this.http.post<void>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix, user, this.httpOptions);
  }

  public addNewAddress(customerId: string, address: Address) {
    return this.http.put<Address>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/" + customerId + "/addresses", address, this.httpOptions);
  }
}
