import { Component } from '@angular/core';
import { donor_form } from '../../Models/donor.model';
import { DonorRepo } from '../../Models/donor.repo';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../Models/auth.service';
import { donorEntity } from '../../Models/donorentity.model';
import Swal from 'sweetalert2';
import { DataSourceDonor } from '../../Models/donorentity.datasource';
import { DataSource } from '../../Models/donor.datasource';
import { MatDialog } from '@angular/material/dialog';
import { DonorComponent } from '../donor/donor.component';

@Component({
  selector: 'app-donor-details',
  templateUrl: './donor-details.component.html',
  styleUrl: './donor-details.component.css'
})
export class DonorDetailsComponent {

  request: any = {
    dateOfDonation: '',
    bloodQuantity: '',
    agree: false
  };
  currentDate: any;
  id: any;

  public don: donorEntity = new donorEntity(0, 0, [])

  constructor(
    private repo: DonorRepo,
    private formBuilder: FormBuilder,
    private router: Router,
    private auth: AuthService,
    private data: DataSource,
    private d: DataSourceDonor,
    public dialog: MatDialog
  ) {
    const today = new Date();
    this.currentDate = this.formatDate(today);
  }
  formatDate(date: Date): string {
    const yyyy = date.getFullYear();
    let mm: string | number = date.getMonth() + 1;
    let dd: string | number = date.getDate();

    mm = (mm < 10) ? '0' + mm : mm;
    dd = (dd < 10) ? '0' + dd : dd;

    return `${yyyy}-${mm}-${dd}`;
  }
  SendRequest(donor: donorEntity) {
    this.don = donor
    this.find(this.auth.userId)
  }
  send(donor: donor_form) {
    this.request = donor
    this.data.savedonor(this.request).subscribe()
  }
  find(id: number) {
    this.d.findIdByUserId(id).subscribe(
      (e) => {
        if (e > 0) {
          this.request.donorId = e
          this.send(this.request)
        }
        else {
          this.don.userId = id
          this.d.savedonor(this.don).subscribe(
            (g) => {
              this.request.donorId = g.id
              this.send(this.request)
            }
          )
        }
        Swal.fire("Thank you 🤗")
        this.router.navigate(["donorHome"])
      }
    )
  }

  openDialog() {
    const dialogRef = this.dialog.open(DonorComponent, {
      width: '550px',
      height: '450px',
      position: {
        bottom: '35px',
        right: '25%'
      },
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }
}