import { Component, OnInit } from '@angular/core';
import { User } from '../../Models/user.model';
import { DataSource } from '../../Models/user.datasource';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css'
})
export class UserDetailsComponent implements OnInit {
  public users: any = []
  public pageSize: number = 5;
  public currentPage: number = 1;

  constructor(private data: DataSource) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.data.fetchAll().subscribe(
      (d) => {
        this.users = d
      }
    )
  }

  getPaginatedItems(): User[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.users.slice(startIndex, endIndex);
  }

  onPageChange(page: number): void {
    this.currentPage = page;
  }
}
