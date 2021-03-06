import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DetailedFoodOrder } from '../_model/detailed-food-order';
import { FoodOrder } from '../_model/food-order';

@Injectable({
  providedIn: 'root'
})
export class FoodOrderServiceService {

  private httpOptions = {
    headers: new HttpHeaders({
      'accept': 'application/json'
    })
  };

  private httpOptions2 = {
    headers: new HttpHeaders({
      'accept': 'application/json',
      'content-type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  public getAllOrdersForCustomer(customerId: string) {
    return this.http.get<FoodOrder[]>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + customerId + "/orders", this.httpOptions);
  }

  public saveOrderForCustomer(order: FoodOrder) {
    return this.http.post<FoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + order.customerId + "/orders", order, this.httpOptions2);
  }

  public getDetailedOrderForCustomer(customerId: string, orderId: string) {
    return this.http.get<DetailedFoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + customerId + "/orders/" + orderId, this.httpOptions);
  }

  public getOneOrder(orderId: string) {
    return this.http.get<FoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/orders/" + orderId, this.httpOptions);
  }

  public cancelOrderForCustomer(customerId: string, orderId: string) {
    return this.http.put<FoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + customerId + "/orders/" + orderId + "/cancel", this.httpOptions);
  }

  public getOrdersByStaffId(staffId: string) {
    let httpOptions = {
      headers: new HttpHeaders({
        'accept': 'application/json'
      }),
      params: { 'staffId': staffId }
    };
    return this.http.get<DetailedFoodOrder[]>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/orders/staff", httpOptions);
  }

}
