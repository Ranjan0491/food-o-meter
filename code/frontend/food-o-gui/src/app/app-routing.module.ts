import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { CustomerRegistrationComponent } from './customer-registration/customer-registration.component';
import { CustomerDashboardComponent } from './customer/customer-dashboard/customer-dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { StaffDashboardComponent } from './staff-non-admin/staff-dashboard/staff-dashboard.component';
import { AuthGuard } from './_guard/auth.guard';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "customer", component: CustomerDashboardComponent, canActivate: [AuthGuard] },
  { path: "admin", component: AdminDashboardComponent, canActivate: [AuthGuard] },
  { path: "staff", component: StaffDashboardComponent, canActivate: [AuthGuard] },
  { path: "customer-registration", component: CustomerRegistrationComponent },
  { path: "sign-in", component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
