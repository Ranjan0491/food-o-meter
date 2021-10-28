import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  isExpanded: boolean = false;
  displayView: boolean[] = [false, false, false];

  constructor() { }

  ngOnInit(): void {
    this.changeView(0);
  }

  changeView(index: number) {
    for (let i = 0; i < this.displayView.length; i++) {
      if (i === index) {
        this.displayView[i] = true;
      } else {
        this.displayView[i] = false;
      }
    }
  }

}
