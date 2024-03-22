import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { DataSource } from '../../Models/user.datasource';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrl: './verify-otp.component.css'
})
export class VerifyOtpComponent {
  public securitycode: number = 0;
  passwordForm!: FormGroup;
  successmessage: string='';
  password: string = '';
  newpassword: string = '';
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private datasource:DataSource,
    private router: Router
  ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.passwordForm = this.fb.group({
      email: this.fb.control('', [Validators.required, Validators.email]),
      enteredOtp: this.fb.control('', [
        Validators.required,
        Validators.pattern(/[6]/),
      ]),
      newpassword: this.fb.control('', [Validators.required]),
    });
  }

  
  patternValidator(regex: RegExp, error: any) {
    return (control: any) => {
      if (!control.value) {
        return null;
      }
      const valid = regex.test(control.value);
      return valid ? null : error;
    };
  }
  createForm() {
    this.passwordForm = this.fb.group({
      otp: ['', Validators.required],
    });
  }
  email: string = '';
  enteredOtp: string = '';
  verificationMessage: string = '';
  verifyOtp() {
    this.email = this.passwordForm.get('email')?.value;
    this.enteredOtp = this.passwordForm.get('enteredOtp')?.value;
    this.datasource.verifyOtp(this.email, this.enteredOtp).subscribe(
      (response:any) => {
        if(response!=null){
          Swal.fire({
            title: 'Success',
            text: 'OTP verified successfully.',
            icon: 'success',
            confirmButtonText: 'Ok',
          });
          this.router.navigate(['/type']);
        }
      },
      (error) => {
        Swal.fire({
          title: 'Error',
          text: 'OTP Verification Failed.',
          icon: 'error',
          confirmButtonText: 'Ok',
        });
      }
    );
  }
}