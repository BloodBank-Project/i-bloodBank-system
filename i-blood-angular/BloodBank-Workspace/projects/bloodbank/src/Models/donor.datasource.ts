import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { donor_form } from './donor.model';
import { NotificationService } from './notificationDatasource';

@Injectable({ providedIn: 'root' })
export class DataSource {

  constructor(private http: HttpClient, private notificationService: NotificationService) { }

  savedonor(donor: donor_form): Observable<donor_form> {
    return this.http.post<donor_form>(`http://localhost:8084/details/save`, donor);
  }
  getDonorAll(): Observable<donor_form[]> {
    return this.http.get<donor_form[]>('http://localhost:8084/details/all');
  }

  deleteDetails(id: number): Observable<donor_form[]> {
    return this.http.get<donor_form[]>(`http://localhost:8084/details/delete/${id}`);
  }

  /**
     * Updates the donor form with new details.
     * @param donorForm The donor form data to be updated.
     * @returns An observable of type DonorDetails representing the updated donor details.
     */
  updateDonorForm(donorDetails: donor_form): Observable<donor_form> {
    return this.http.put<donor_form>('http://localhost:8084/details/update', donorDetails);
  }

  /**
   * Updates the blood quantity for a specific donor.
   * @param id The ID of the donor whose blood quantity is to be updated.
   * @returns An observable of type DonorDetails representing the updated donor details.
   */
  statusAccept(status: donor_form): Observable<donor_form> {
    return this.http.put<donor_form>('http://localhost:8084/details/status/accepted', status);
  }

  statusReject(status: donor_form): Observable<donor_form> {
    return this.http.put<donor_form>('http://localhost:8084/details/status/rejected', status);
  }

}