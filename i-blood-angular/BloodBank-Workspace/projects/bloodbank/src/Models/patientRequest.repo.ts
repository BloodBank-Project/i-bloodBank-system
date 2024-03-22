import { Injectable, OnInit } from '@angular/core';
import { PatientRequest } from './patientRequest.model';
import { DataSource } from './patientRequest.datasource';

@Injectable({ providedIn: 'root' })
export class PatientRequestRepo {
    public patientRequest: PatientRequest[] = [];
    constructor(private dataSource: DataSource) {
        this.getPatientRequest();
    }
    getPatientRequest() {
        this.dataSource.getPatientRequest().subscribe(data =>
            this.patientRequest = data)
    }
    getPatientRequestDetails(): PatientRequest[] {
        return this.patientRequest;
    }

    sendPatientRequest(patinet: PatientRequest) {
        this.dataSource.savePatientRequest(patinet).subscribe((data) => {
        },
            (error) => {
                console.error("error no data found", error);
            })
    }

    deletePatientRequest(id: number) {
        this.dataSource.deletePatient(id).subscribe(() => {
        })
    }

}