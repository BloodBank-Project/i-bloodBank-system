import { Component } from '@angular/core';
import { DataSource } from '../bloodBank.Model/bloodBankDatasource';
import { BloodBank } from '../dashboard.Model/bloodBankModel';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {

  public bank: any = [];
  searchTerm: any = "";
  public pageSize: number = 4;
  public currentPage: number = 1;

  constructor(private data: DataSource) { }


  ngOnInit(): void {
    this.bankDetails();
  }

  getPaginatedItems(): BloodBank[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.bank.slice(startIndex, endIndex);
  }

  onPageChange(page: number): void {
    this.currentPage = page;
  }

  bankDetails() {
    this.data.fetchingBloodBankDetails().subscribe(d => {
      this.bank = d;
    })
  }

  changeData(value: any): void {
    if (value == null || value == '' || value == undefined) {
      this.bankDetails();
    } else {
      this.data.searchByLocation(value)
        .subscribe(response => {
          this.bank = response;
        });
    }
  }
}