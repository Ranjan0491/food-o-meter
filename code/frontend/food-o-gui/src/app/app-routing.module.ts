import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { CustomerDashboardComponent } from './customer/customer-dashboard/customer-dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { StaffDashboardComponent } from './staff-non-admin/staff-dashboard/staff-dashboard.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "customer", component: CustomerDashboardComponent },
  { path: "admin", component: AdminDashboardComponent },
  { path: "staff", component: StaffDashboardComponent },
  { path: "customer-registration", component: CustomerRegistrationComponent },
  { path: "sign-in", component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
