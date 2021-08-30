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
      'Accept': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  public getAllFoodItems() {
    return this.http.get<FoodItem[]>(environment.apiUrl + "/food-o-meter-item-service/v1/food-items", this.httpOptions);
  }
}
