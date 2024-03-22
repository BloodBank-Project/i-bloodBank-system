import { Component } from '@angular/core';
import { DataSource } from '../../Models/user.datasource';
import { AuthService } from '../../Models/auth.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-location',
  templateUrl: './update-location.component.html',
  styleUrl: './update-location.component.css'
})
export class UpdateLocationComponent {

  constructor(private data: DataSource, private auth: AuthService, private router: Router) { }

  bloodBankId: any;

  updateLocation() {
    this.data.updateLocation(this.auth.email, this.bloodBankId).subscribe(
      () => {
        Swal.fire("location changed successfully")
        this.router.navigate(['donorHome'])
      }
    )
  }
}
