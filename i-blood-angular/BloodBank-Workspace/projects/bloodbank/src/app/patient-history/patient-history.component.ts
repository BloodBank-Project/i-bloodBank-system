import { Component, OnInit } from '@angular/core';
import { Patient } from '../../Models/patiententity.model';
import { DataSourcePatient } from '../../Models/patiententity.datasource';
import { AuthService } from '../../Models/auth.service';

@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrl: './patient-history.component.css'
})
export class PatientHistoryComponent implements OnInit {
  public patientHistor!: Patient;
  constructor(private data: DataSourcePatient, private auth: AuthService) { }

  ngOnInit(): void {
    this.findPatientId();
  }

  userId: number = this.auth.userId

  findPatientId() {
    this.data.findIdByUserId(this.userId).subscribe(
      (d) => {
        this.patientHistory(d)
      }
    )
  }

  patientHistory(id: number) {
    this.data.fetchByPatientId(id).subscribe(
      (p: Patient) => {
        this.patientHistor = p
      },
      (error) => {
        console.error("Error fetching patient history:", error);
      }
    )
  }
}