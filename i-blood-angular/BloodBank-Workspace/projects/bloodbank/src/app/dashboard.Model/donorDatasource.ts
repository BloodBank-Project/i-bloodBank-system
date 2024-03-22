import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { DonorDetails } from "./donorModel";
import { PatientRequest } from "./patientModel";
import { BloodBank } from "./bloodBankModel";

@Injectable({ providedIn: 'root' })
export class DataSource {

  constructor(private http: HttpClient) { }

  getCountDonations(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://localhost:8084/details/count/donations')
  }

  getCountRequests(): Observable<PatientRequest[]> {
    return this.http.get<PatientRequest[]>('http://localhost:8085/patientRequest/count/requests')
  }

  getTotalAcceptedStatuses(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://localhost:8084/details/total/accepted')
  }

  getTotalRejectedStatuses(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://localhost:8084/details/total/rejected')
  }

  getTotalPendingStatuses(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://localhost:8084/details/total/pending')
  }

  getTotalBloodBanks(): Observable<BloodBank[]> {
    return this.http.get<BloodBank[]>('http://localhost:8084/details/total/bloodBanks')
  }
}