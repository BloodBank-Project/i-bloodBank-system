
import { Component } from '@angular/core';
import { UserRepo } from '../../Models/user.repo';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../Models/auth.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {
  isedIn: boolean = false;
  loginForm: FormGroup;

  title: String = "LOGIN";


  email: string = '';
  password: string = '';

  constructor(private repo: UserRepo, private router: Router, private fb: FormBuilder, private auth: AuthService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  loginUser() {
    this.auth.login(this.loginForm.value.email, this.loginForm.value.password)
  }
}
