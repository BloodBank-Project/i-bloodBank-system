import { Injectable, OnInit } from '@angular/core';
import { donor_form } from './donor.model';
import { DataSource } from './donor.datasource';

@Injectable({ providedIn: 'root' })
export class DonorRepo {
    public donorRequest: any;
    constructor(private dataSource: DataSource) {
    }
}