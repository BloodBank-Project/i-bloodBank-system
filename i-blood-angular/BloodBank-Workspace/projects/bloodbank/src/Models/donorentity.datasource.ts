import { Injectable } from "@angular/core";
import { donorEntity } from "./donorentity.model";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({ providedIn: 'root' })
export class DataSourceDonor {
    constructor(private http: HttpClient) {

    }

    savedonor(donor: donorEntity): Observable<donorEntity> {
        return this.http.post<donorEntity>(`http://localhost:8084/donor/save`, donor);
    }

    fetchDonorAll(): Observable<donorEntity[]> {
        return this.http.get<donorEntity[]>('http://localhost:8084/donor/all');
    }

    fetchByDonorId(id: number): Observable<donorEntity> {
        return this.http.get<donorEntity>(`http://localhost:8084/donor/get/${id}`);
    }

    findIdByUserId(id: number): Observable<number> {
        return this.http.get<number>(`http://localhost:8084/donor/userId/${id}`)
    }
}