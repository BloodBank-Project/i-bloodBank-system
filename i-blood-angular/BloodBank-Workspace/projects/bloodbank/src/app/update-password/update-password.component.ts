import { Component } from '@angular/core';
import { UserRepo } from '../../Models/user.repo';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { DataSource } from '../../Models/user.datasource';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']

})
export class UpdatePasswordComponent {
  public email: string='';
  public otp: string='';
  public user: any[] | undefined;

  updateForm!:FormGroup;
  constructor(private repo:UserRepo,private router:Router,private fb:FormBuilder,private datasource:DataSource){}
  ngOnInit(): void {
    this.updateForm=this.fb.group({
      email:this.fb.control('',[Validators.required,Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$'),Validators.email]),
      password:['',[Validators.required,this.validatePassword]]
    })
    
  }
  generateOTP() {
    this.email = this.updateForm.get('email')?.value;
    
    this.datasource.sendOtp(this.email).subscribe(
      (response) => {
        this.user =response;
        Swal.fire({
          title: 'Success',
          text: 'OTP sent successfully.',
          icon: 'success',
          confirmButtonText: 'Ok',
        });

        this.router.navigate(['/verify']);
      },
      (error) => {
        console.error('Error sending OTP: ', error);
  }
);
}


  validatePassword(control: FormControl) {
    const password = control.value;
    const strongRegex = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})');
    if (!strongRegex.test(password)) {
      return { invalidPassword: true };
    }
    return null;
  }
}