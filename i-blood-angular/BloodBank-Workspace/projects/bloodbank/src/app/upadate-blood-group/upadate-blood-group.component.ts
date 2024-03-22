import { Component } from '@angular/core';
import { DataSource } from '../../Models/user.datasource';
import { AuthService } from '../../Models/auth.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upadate-blood-group',
  templateUrl: './upadate-blood-group.component.html',
  styleUrl: './upadate-blood-group.component.css'
})
export class UpadateBloodGroupComponent {

  constructor(private data: DataSource, private auth: AuthService, private router: Router) { }

  bloodBankId: any;
  bloodGroupId: any;

  updateGroup() {
    this.data.updateBloodGroup(this.auth.email, this.bloodBankId, this.bloodGroupId).subscribe(
      () => {
        Swal.fire("location changed successfully")
        this.router.navigate(['patientHome'])
      }
    )
  }

}
