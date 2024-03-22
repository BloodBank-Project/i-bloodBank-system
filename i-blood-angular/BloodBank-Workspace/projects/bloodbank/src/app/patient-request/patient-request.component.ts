import { Component, OnInit } from '@angular/core';
import { PatientRequest } from '../../Models/patientRequest.model';
import { PatientRequestRepo } from '../../Models/patientRequest.repo';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Patient } from '../../Models/patiententity.model';
import { DataSourcePatient } from '../../Models/patiententity.datasource';
import { AuthService } from '../../Models/auth.service';
import { DataSource } from '../../Models/patientRequest.datasource';
import { PatientComponent } from '../patient/patient.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-patient-request',
  templateUrl: './patient-request.component.html',
  styleUrl: './patient-request.component.css'
})
export class PatientRequestComponent {
  currentDate: any;

  request: any = {
    patientMedicalCondition: '',
    patientRequestOnDate: '',
    patientRequestUnits: '',
    agree: false
  };

  public patient: Patient = new Patient(0, 0, [])

  constructor(
    private auth: AuthService,
    private d: DataSourcePatient,
    private repo: PatientRequestRepo,
    private fb: FormBuilder,
    private router: Router,
    private data: DataSource,
    public dialog: MatDialog
  ) {
    const today = new Date();
    this.currentDate = this.formatDate(today);
  }
  formatDate(date: Date): string {
    const yyyy = date.getFullYear();
    let mm: string | number = date.getMonth() + 1;
    let dd: string | number = date.getDate();

    mm = (mm < 10) ? '0' + mm : mm;
    dd = (dd < 10) ? '0' + dd : dd;

    return `${yyyy}-${mm}-${dd}`;
  }

  SendRequest(patient: Patient) {
    this.patient = patient
    this.find(this.auth.userId)
  }

  send(p: PatientRequest) {
    this.request = p
    this.data.savePatientRequest(this.request).subscribe(

    )
  }

  find(id: number) {
    this.d.findIdByUserId(id).subscribe(
      (e) => {
        if (e > 0) {
          this.request.patientId = e
          this.send(this.request)
        }
        else {
          this.patient.userId = id
          this.d.savePatientRequest(this.patient).subscribe(
            (g) => {
              this.request.patientId = g.patientId
              this.send(this.request)
            }
          )
        }
        Swal.fire("your request has been successfully sent")
        this.router.navigate(['patientHome'])
      }
    )
  }

  openDialogPatient() {
    const dialogRefPatient = this.dialog.open(PatientComponent, {
      width: '550px',
      height: '450px',
      position: {
        top: '120px',
        right: '25%'
      },
    });

    dialogRefPatient.afterClosed().subscribe((result: any) => {
    });
  };
}