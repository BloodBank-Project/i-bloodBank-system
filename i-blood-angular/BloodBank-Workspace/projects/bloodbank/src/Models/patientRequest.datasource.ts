import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PatientRequest } from './patientRequest.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DataSource {
  constructor(private http: HttpClient) {

  }

  getPatientRequest(): Observable<PatientRequest[]> {
    return this.http.get<PatientRequest[]>(`http://localhost:8085/patientRequest/allPatients`);
  }
  savePatientRequest(PatientRequest: PatientRequest): Observable<PatientRequest> {
    return this.http.post<PatientRequest>(`http://localhost:8085/patientRequest/save`, PatientRequest);
  }
  deletePatient(requestId: number) {
    return this.http.delete(`http://localhost:8085/patientRequest/${requestId}`)
  }


  // Method to update a patient request on the server.
  // Takes a PatientRequest object as input and returns an Observable of a PatientRequest object.
  updatePatientForm(patientRequest: PatientRequest): Observable<PatientRequest> {
    return this.http.put<PatientRequest>('http://localhost:8085/patientRequest/update', patientRequest);
  }

  /**
   * Updates the blood quantity for a specific patient  .
   * @param id The ID of the donor whose blood quantity is to be updated.
   * @returns An observable of type DonorDetails representing the updated donor details.
   */
  statusAccept(status: PatientRequest): Observable<PatientRequest> {
    return this.http.put<PatientRequest>('http://localhost:8085/patientRequest/status/accepted', status);
  }

  statusReject(status: PatientRequest): Observable<PatientRequest> {
    return this.http.put<PatientRequest>('http://localhost:8085/patientRequest/status/rejected', status);
  }

}