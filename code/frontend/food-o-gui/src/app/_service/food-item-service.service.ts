import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { FoodItem } from '../_model/food-item';

@Injectable({
  providedIn: 'root'
})
export class FoodItemServiceService {

  private httpOptions = {
    headers: new HttpHeaders({
      'accept': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  public getAllFoodItems() {
    return this.http.get<FoodItem[]>(environment.apiUrlHostAndPort + environment.itemServiceUrlPrefix, this.httpOptions);
  }

  public getOneFoodItem(id: string) {
    return this.http.get<FoodItem>(environment.apiUrlHostAndPort + environment.itemServiceUrlPrefix + "/" + id, this.httpOptions);
  }

  public saveFoodItem(foodItem: FoodItem) {
    return this.http.post<FoodItem>(environment.apiUrlHostAndPort + environment.itemServiceUrlPrefix, foodItem, this.httpOptions);
  }

  public updateFoodItem(id: string, foodItem: FoodItem) {
    return this.http.put<void>(environment.apiUrlHostAndPort + environment.itemServiceUrlPrefix + "/" + id, foodItem, this.httpOptions);
  }

  public deleteFoodItem(id: string) {
    return this.http.delete<void>(environment.apiUrlHostAndPort + environment.itemServiceUrlPrefix + "/" + id, this.httpOptions);
  }

}
