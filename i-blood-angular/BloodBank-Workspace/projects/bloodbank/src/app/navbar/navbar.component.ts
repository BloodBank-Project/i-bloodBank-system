import { Component } from '@angular/core';
import { DataSource } from '../bloodBank.Model/bloodBankDatasource';
import { AuthService } from '../../Models/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {


  constructor(public authService: AuthService, private data: DataSource) { }

  public bank: any = [];

  bankDetails() {
    this.data.fetchingBloodBankDetails().subscribe(d => {
      this.bank = d;
    })
  }
}