import { Component } from '@angular/core';
import { UserRepo } from '../../Models/user.repo';
import { User } from '../../Models/user.model';
import { Blood } from '../../Models/blood.model';
import { Router } from '@angular/router';
import { DataSource } from '../../Models/user.datasource';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  user: User = new User(0, '', '', '', '', new Date(), '', '', 0, 0, '', 0, 0);


  currentDate: string = new Date().toISOString().split('T')[0];
  maxDate: string = new Date(new Date().setFullYear(new Date().getFullYear() - 18)).toISOString().split('T')[0];

  constructor(private datasource: DataSource, private router: Router) { }


  sendUser(user: User) {
    this.datasource.saveUser(user).subscribe(
      (d) => {
        Swal.fire("Account created successfully")
        this.router.navigate(['home'])
      },
      (error: any) => {
        Swal.fire('with this email account already registred')
      }
    )

  }

}