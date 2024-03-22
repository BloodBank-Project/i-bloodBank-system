import { Injectable } from '@angular/core';
import { UserRepo } from './user.repo';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  userId: any;
  email: any;
  donorId: any;
  fullName: any;


  session: any = null;

  constructor(private repo: UserRepo, private router: Router) { }

  login(email: string, password: string) {
    this.repo.loginUser(email, password).subscribe(
      (response) => {
        this.session = response.email
        this.userId = response.userId
        this.email = response.email
        this.fullName = response.firstName

        if (response.type == 'A') {
          this.router.navigate(['/sidenav']);
        } else {
          this.router.navigate(['/type']);
        }
        Swal.fire("Login done successfully 😊")
      },
      (error) => {
        Swal.fire('Failed to Login 😑')
      }
    );
  }

  logout() {
    this.session = null
  }


}