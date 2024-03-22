import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BloodGroupComponent } from './blood-group/blood-group.component';
import { BloodBankComponent } from './blood-bank/blood-bank.component';
import { PatientRequestsComponent } from './patient-requests/patient-requests.component';
import { DonationDetailsComponent } from './donation-details/donation-details.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { FaqComponent } from './faq/faq.component';
import { PatientHomeComponent } from './patient-home/patient-home.component';
import { PatientComponent } from './patient/patient.component';
import { DonorComponent } from './donor/donor.component';
import { PatientRequestComponent } from './patient-request/patient-request.component';
import { DonorHomeComponent } from './donor-home/donor-home.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UsertypeComponent } from './usertype/usertype.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { UserComponent } from './user/user.component';
import { SearchComponent } from './search/search.component';
import { DonorDetailsComponent } from './donor-details/donor-details.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UpdateLocationComponent } from './update-location/update-location.component';
import { DonorHistoryComponent } from './donor-history/donor-history.component';
import { PatientHistoryComponent } from './patient-history/patient-history.component';
import { UpadateBloodGroupComponent } from './upadate-blood-group/upadate-blood-group.component';
import { VerifyOtpComponent } from './verify-otp/verify-otp.component';

const routes: Routes = [
  { path: "home", component: HomeComponent },
  { path: "about", component: AboutComponent },
  { path: "contact", component: ContactComponent },
  { path: "faq-auth", component: FaqComponent },
  { path: "loginUser", component: UserLoginComponent },
  { path:"verify", component:VerifyOtpComponent},
  { path: "type", component: UsertypeComponent },
  { path: "updatePassword", component: UpdatePasswordComponent },
  { path: "saveUser", component: UserComponent },
  { path: "search", component: SearchComponent },
  {
    path: 'donorHome', component: DonorHomeComponent,
    children: [
      { path: 'donorDetails', component: DonorDetailsComponent },
      { path: 'location', component: UpdateLocationComponent },
      { path: 'getdonor', component: DonorComponent },
      { path: 'details', component: DonorHistoryComponent }

    ]
  },
  {
    path: 'patientHome', component: PatientHomeComponent,
    children: [
      { path: 'patientRequest', component: PatientRequestComponent },
      { path: 'getPatient', component: PatientComponent },
      { path: 'patientdetails', component: PatientHistoryComponent },
      { path: 'group', component: UpadateBloodGroupComponent }
    ]
  },
  { path: 'donor', component: DonorComponent },
  { path: 'patient', component: PatientComponent },
  {
    path: 'sidenav', component: SidenavComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'group', component: BloodGroupComponent },
      { path: 'bank', component: BloodBankComponent },
      { path: 'requests', component: PatientRequestsComponent },
      { path: 'donors', component: DonationDetailsComponent },
      { path: 'userDetails', component: UserDetailsComponent }
    ]
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }