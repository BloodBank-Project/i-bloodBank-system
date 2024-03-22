import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBank } from './bloodBankModel';

@Injectable({ providedIn: 'root' })
export class DataSource {
  constructor(private http: HttpClient) {

  }

  /**
   * Fetches all blood bank details from the server.
   * @returns An Observable of BloodBank array.
   */
  fetchingBloodBankDetails(): Observable<BloodBank[]> {
    return this.http.get<BloodBank[]>('http://localhost:8082/bloodbank/all');
  }

  /**
   * Searches for blood banks by location.
   * @param location The location to search for.
   * @returns An Observable of BloodBank array matching the location.
   */
  searchByLocation(location: string): Observable<BloodBank[]> {
    return this.http.get<BloodBank[]>(`http://localhost:8082/bloodbank/location/${location}`);
  }
}