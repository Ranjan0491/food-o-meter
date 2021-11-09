import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Address } from '../_model/address';
import { Login } from '../_model/login';
import { Staff } from '../_model/staff';
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

  public updateUserDetails(id: string, user: User) {
    return this.http.put<void>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/" + id, user, this.httpOptions);
  }

  public getAllStaffByRoles(...roles: string[]) {
    let httpOptions = {
      headers: new HttpHeaders({
        'accept': 'application/json'
      }),
      params: { 'userRoles': roles.join() }
    };
    return this.http.get<Staff[]>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/staffs-by-role", httpOptions);
  }

  public deleteStaff(id: string, requesterId: string) {
    let httpOptions = {
      headers: new HttpHeaders({
        'accept': 'application/json'
      }),
      params: { 'requesterId': requesterId }
    };
    return this.http.delete<void>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/" + id, httpOptions);
  }

  public userLogin(login: Login) {
    return this.http.post<User>(environment.apiUrlHostAndPort + environment.userServiceUrlPrefix + "/login", login, this.httpOptions);
  }
}
