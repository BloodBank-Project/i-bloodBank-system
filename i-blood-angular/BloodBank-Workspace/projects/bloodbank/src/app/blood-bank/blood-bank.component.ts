import { Component, OnInit } from '@angular/core';
import { DataSource } from '../bloodBank.Model/bloodBankDatasource';
import Swal from 'sweetalert2';
import { BloodBank } from '../bloodBank.Model/bloodBankModel';

@Component({
  selector: 'app-blood-bank',
  templateUrl: './blood-bank.component.html',
  styleUrl: './blood-bank.component.css'
})
export class BloodBankComponent implements OnInit {

  public bank: BloodBank[] = [];
  public searchTerm: string = "";
  public pageSize: number = 4;
  public currentPage: number = 1;

  constructor(private data: DataSource) { }

  ngOnInit(): void {
    // Initialize component
    this.fetchBankDetails();
  }

  /**
   * Fetch blood bank details
   */
  fetchBankDetails(): void {
    this.data.fetchingBloodBankDetails().subscribe(banks => {
      this.bank = banks;
    }, error => {
      Swal.fire("Error", "Failed to fetch bank details. Please try again later.", "error");
    })
  }

  /**
  * Filter blood bank data based on search term
  * @param value Search term entered by the user
  */
  changeData(value: string): void {
    this.searchTerm = value;
    if (value == null || value == '' || value == undefined) {
      this.currentPage = 1;
      this.fetchBankDetails();
    } else {
      this.data.searchByLocation(value)
        .subscribe(response => {
          this.bank = response;
        }, error => {
          Swal.fire("Error", "Failed to search by location. Please try again later.", "error");
        });
    }
  }

  getPaginatedItems(): BloodBank[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.bank.slice(startIndex, endIndex);
  }

  onPageChange(page: number): void {
    this.currentPage = page;
  }
}