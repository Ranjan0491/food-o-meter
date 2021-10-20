import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { CustomerModule } from './customer/customer.module';
import { MatDialogModule } from '@angular/material/dialog';
import { AlertComponent } from './alert/alert.component';
import { MatIconModule } from '@angular/material/icon';
import { AdminModule } from './admin/admin.module';
import { StaffNonAdminModule } from './staff-non-admin/staff-non-admin.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    HttpClientModule,
    MatTabsModule,
    MatTableModule,
    MatPaginatorModule,
    CustomerModule,
    AdminModule,
    StaffNonAdminModule,
    MatDialogModule,
    MatIconModule
  ],
  providers: [
    AlertComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
