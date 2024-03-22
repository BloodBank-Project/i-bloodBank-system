import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { UsertypeComponent } from './usertype/usertype.component';
import { HttpClientModule } from '@angular/common/http';
import { UserModule } from '../Models/user.module';
import { UserLoginComponent } from './user-login/user-login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PatientRequestComponent } from './patient-request/patient-request.component';
import { PatientRequestModule } from '../Models/patientRequest.module';
import { HomeComponent } from './home/home.component';
import { DonorDetailsComponent } from './donor-details/donor-details.component';
import { DonorHomeComponent } from './donor-home/donor-home.component';
import { GetPatientRequestComponent } from './get-patient-request/get-patient-request.component';
import { DonorFormModule } from '../Models/donor.module';
import { BloodGroupComponent } from './blood-group/blood-group.component';
import { PatientComponent } from './patient/patient.component';
import { DonationDetailsComponent } from './donation-details/donation-details.component';
import { BloodBankComponent } from './blood-bank/blood-bank.component';
import { PatientRequestsComponent } from './patient-requests/patient-requests.component';
import { DonorComponent } from './donor/donor.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BodyComponent } from './body/body.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { FaqComponent } from './faq/faq.component';
import { PatientHomeComponent } from './patient-home/patient-home.component';
import { SearchComponent } from './search/search.component';
import { CommonModule } from '@angular/common';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UpdateLocationComponent } from './update-location/update-location.component';
import { DonorHistoryComponent } from './donor-history/donor-history.component';
import { PatientHistoryComponent } from './patient-history/patient-history.component';
import { UpadateBloodGroupComponent } from './upadate-blood-group/upadate-blood-group.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatDialogModule } from '@angular/material/dialog';
import { VerifyOtpComponent } from './verify-otp/verify-otp.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UpdatePasswordComponent,
    UsertypeComponent,
    UserLoginComponent,
    NavbarComponent,
    PatientRequestComponent,
    HomeComponent,
    DonorDetailsComponent,
    DonorHomeComponent,
    GetPatientRequestComponent,

    BloodGroupComponent,
    BloodBankComponent,
    PatientComponent,
    DonationDetailsComponent,
    PatientRequestsComponent,
    DonorComponent,
    SidenavComponent,
    BodyComponent,
    DashboardComponent,
    AboutComponent,
    ContactComponent,
    FaqComponent,
    PatientHomeComponent,
    SearchComponent,
    UserDetailsComponent,
    UpdateLocationComponent,
    DonorHistoryComponent,
    PatientHistoryComponent,
    UpadateBloodGroupComponent,
    VerifyOtpComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    UserModule,
    PatientRequestModule,
    DonorFormModule,
    ReactiveFormsModule,
    CommonModule,
    MatDialogModule

  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [NavbarComponent]
})
export class AppModule { }
