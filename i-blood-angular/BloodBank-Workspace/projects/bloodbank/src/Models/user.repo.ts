import { Injectable } from "@angular/core";
import { DataSource } from "./user.datasource";
import { User } from "./user.model";
import { Observable } from "rxjs";

@Injectable({ providedIn: "root" })
export class UserRepo {

    public user: User[] = []
    constructor(private datasource: DataSource) { }

    SendUser(user: User) {
        this.datasource.saveUser(user).subscribe(
            (data) => {
                console.info(data);
            },
            (error) => {
                console.error("data not found", error);
            }
        )
    }

    updatePassword(email: string, password: string): Observable<any> {
        return this.datasource.updatePassword(email, password)
    }

    loginUser(email: string, password: string): Observable<any> {
        return this.datasource.loginUser(email, password);
    }

}