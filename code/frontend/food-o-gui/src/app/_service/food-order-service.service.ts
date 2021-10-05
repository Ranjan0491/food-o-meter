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
      'Accept': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  public getAllOrdersForCustomer(customerId: String) {
    return this.http.get<FoodOrder[]>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + customerId + "/orders", this.httpOptions);
  }

  public saveOrderForCustomer(customerId: String, order: FoodOrder) {
    return this.http.post<FoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + customerId + "/orders", order, this.httpOptions);
  }

  public getDetailedOrderForCustomer(customerId: String, orderId: String) {
    return this.http.get<DetailedFoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + customerId + "/orders/" + orderId, this.httpOptions);
  }

  public getOneOrder(orderId: String) {
    return this.http.get<FoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/orders/" + orderId, this.httpOptions);
  }

  public cancelOrderForCustomer(customerId: String, orderId: String) {
    return this.http.put<FoodOrder>(environment.apiUrlHostAndPort + environment.orderServiceUrlPrefix + "/customers/" + customerId + "/orders/" + orderId + "/cancel", this.httpOptions);
  }

}
