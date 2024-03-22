import { Injectable } from "@angular/core";
import { DataSource } from "./blood.datasource";
import { Blood } from "./blood.model";

@Injectable({ providedIn: "root" })
export class BloodRepo {
    public bloodList: Blood[] = []
    public id: number = 0

    constructor(private datasource: DataSource) { }

    getBlood() {
        this.datasource.getBloodAll().subscribe(data => {
            this.bloodList = data
        })
    }


}