import { Component, OnInit } from '@angular/core';
import { DataSource } from '../../Models/patientRequest.datasource';
import { PatientRequest } from '../../Models/patientRequest.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-patient-requests',
  templateUrl: './patient-requests.component.html',
  styleUrl: './patient-requests.component.css'
})
export class PatientRequestsComponent implements OnInit {

  public patientRequest: PatientRequest[] = [];
  public buttonDisabled: boolean = false;
  public pageSize: number = 4;
  public currentPage: number = 1;

  constructor(private data: DataSource) { }

  ngOnInit(): void {
    this.getPatientRequests()
  }

  /**
 * Fetches patient requests from the server.
 * Updates the 'patientRequest' array with the fetched data.
 */
  getPatientRequests(): void {
    this.data.getPatientRequest().subscribe((patients: PatientRequest[]) => {
      this.patientRequest = patients;
      this.disableAcceptedOrRejectedPatients(); // Disable buttons for accepted or rejected donors
    }, error => {
      Swal.fire("Error", "Failed to fetch patient requests. Please try again later.", "error");
    })
  }

/**
   * Disable buttons for patients with status 'Accepted' or 'Rejected'.
   */
disableAcceptedOrRejectedPatients(): void {
  for (let patients of this.patientRequest) {
    if (patients.status === 'Accepted' || patients.status === 'Rejected') {
      patients.disabled = true;
    }
  }
}

  /**
 * Accepts a patient request.
 * Updates the patient's status to 'Accepted' and saves it to the backend.
 * @param patient The patient request to accept.
 */
  acceptPatient(patient: PatientRequest): void {
    patient.buttonDisabled = true;
    this.data.statusAccept(patient).subscribe(message => {
      Swal.fire("Status Updated As Accepted 👍")
      this.getPatientRequests();
    }, error => {
      patient.buttonDisabled = false;
      Swal.fire("Error", "Failed to update status as Accepted. Please try again later.", "error");
    });
  }

  /**
  * Rejects a patient request.
  * Updates the patient's status to 'Rejected' and saves it to the backend.
  * @param patient The patient request to reject.
  */
  rejectPatient(patient: PatientRequest): void {
    patient.buttonDisabled = true;
    this.data.statusReject(patient).subscribe(message => {
      Swal.fire("Status Updated As Rejected 👍")
      this.getPatientRequests();
    }, error => {
      patient.buttonDisabled = false;
      Swal.fire("Error", "Failed to update status as Rejected. Please try again later.", "error");
    });
  }

  /**
  * Filter donor request data based on search term.
  * @param value Search term entered by the user.
  */
  changeData(value: string): void {
    this.currentPage = 1; // Reset to first page
    this.getPatientRequests(); // Reload all data
  }

  /**
   * Get paginated patient request items.
   * @returns Paginated donor request items based on current page and page size.
   */
  getPaginatedItems(): PatientRequest[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.patientRequest.slice(startIndex, endIndex);
  }

  /**
  * Handle page change event.
  * @param page The new page number.
  */
  onPageChange(page: number): void {
    this.currentPage = page;
  }

}