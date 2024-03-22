import { Component, OnInit } from '@angular/core';
import { DataSource } from '../dashboard.Model/donorDatasource';
import { DonorDetails } from '../dashboard.Model/donorModel';
import { PatientRequest } from '../dashboard.Model/patientModel';
import { BloodBank } from '../dashboard.Model/bloodBankModel';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  public allDonations: DonorDetails[] = []
  public allPatients: PatientRequest[] = []
  public totalAccepted: DonorDetails[] | PatientRequest[] = [];
  public totalRejected: DonorDetails[] | PatientRequest[] = [];
  public totalPending: DonorDetails[] | PatientRequest[] = [];
  public totalBanks: BloodBank[] = [];

  constructor(private data: DataSource) { }

  ngOnInit(): void {
    this.countDonations();
    this.countPatientRequests();
    this.getTotalAccepted();
    this.getTotalRejected();
    this.getTotalPending();
    this.getTotalBloodBanks();

  }

  countDonations(): void {
    this.data.getCountDonations().subscribe(d => this.allDonations = d);
  }

  countPatientRequests(): void {
    this.data.getCountRequests().subscribe(d => this.allPatients = d);
  }

  getTotalAccepted(): void {
    this.data.getTotalAcceptedStatuses().subscribe(d => {
      this.totalAccepted = d;
    })
  }

  getTotalRejected(): void {
    this.data.getTotalRejectedStatuses().subscribe(d => {
      this.totalRejected = d;
    })
  }

  getTotalPending(): void {
    this.data.getTotalPendingStatuses().subscribe(d => {
      this.totalPending = d;
    })
  }

  getTotalBloodBanks(): void {
    this.data.getTotalBloodBanks().subscribe(d => {
      this.totalBanks = d;
    })
  }

}
