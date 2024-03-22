import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Blood } from "./blood.model";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: "root" })
export class DataSource {
    constructor(private http: HttpClient) { }

    getBloodAll(): Observable<Blood[]> {
        return this.http.get<Blood[]>(`http://localhost:8010/bloodinfo/all`)
    }
} 