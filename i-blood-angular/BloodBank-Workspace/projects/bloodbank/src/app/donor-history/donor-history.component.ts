import { Component, OnInit } from '@angular/core';
import { donor_form } from '../../Models/donor.model';
import { DataSource } from '../../Models/donor.datasource';
import { donorEntity } from '../../Models/donorentity.model';
import { DataSourceDonor } from '../../Models/donorentity.datasource';
import { AuthService } from '../../Models/auth.service';

@Component({
  selector: 'app-donor-history',
  templateUrl: './donor-history.component.html',
  styleUrl: './donor-history.component.css'
})
export class DonorHistoryComponent implements OnInit {


  public donorHistory!: donorEntity;

  constructor(private data: DataSourceDonor, private auth: AuthService) { }

  ngOnInit(): void {
    this.finddonorId();
  }

  userId: number = this.auth.userId

  finddonorId() {
    this.data.findIdByUserId(this.userId).subscribe(
      (data) => {
        this.donoHistory(data)
      }
    )
  }

  donoHistory(id: number) {
    this.data.fetchByDonorId(id)
      .subscribe(
        (d: donorEntity) => {
          this.donorHistory = d;
        },
        (error) => {
          console.error("Error fetching donor history:", error);
        }
      );
  }

}