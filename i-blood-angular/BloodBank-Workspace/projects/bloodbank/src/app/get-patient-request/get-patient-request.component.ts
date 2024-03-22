import { Component, OnInit } from '@angular/core';
import { PatientRequest } from '../../Models/patientRequest.model';
import { PatientRequestRepo } from '../../Models/patientRequest.repo';
import { DataSource } from '../../Models/patientRequest.datasource';

@Component({
  selector: 'app-get-patient-request',
  templateUrl: './get-patient-request.component.html',
  styleUrl: './get-patient-request.component.css'
})
export class GetPatientRequestComponent implements OnInit {
  public request: PatientRequest[] = [];

  constructor(private repository: PatientRequestRepo, private data: DataSource) { }

  ngOnInit(): void {
    this.getPatient()
  }

  getPatient() {
    this.data.getPatientRequest().subscribe(d => {
      this.request = d;
    })
  }

  delete(id: any) {
    this.repository.deletePatientRequest(id)

  }
}
