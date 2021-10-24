import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-staff-dashboard',
  templateUrl: './staff-dashboard.component.html',
  styleUrls: ['./staff-dashboard.component.css']
})
export class StaffDashboardComponent implements OnInit {

  isExpanded: boolean = false;
  displayView: boolean[] = [false, false];

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
