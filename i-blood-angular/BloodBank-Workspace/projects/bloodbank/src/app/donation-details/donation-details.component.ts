import { Component, OnInit } from '@angular/core';
import { DataSource } from '../../Models/donor.datasource';
import { donor_form } from '../../Models/donor.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-donation-details',
  templateUrl: './donation-details.component.html',  
  styleUrl: './donation-details.component.css'
})
export class DonationDetailsComponent implements OnInit {

  public donorRequest: donor_form[] = []
  public buttonDisabled: boolean = false;
  public pageSize: number = 5;
  public currentPage: number = 1;

  constructor(private data: DataSource) { }

  ngOnInit(): void {
    this.fetchDonorRequests();
  }


  /**
   * Fetches donor requests from the server.
   * Updates the 'donorRequest' array with the fetched data.
   */
  fetchDonorRequests(): void {
    this.data.getDonorAll().subscribe((donors: donor_form[]) => {
      this.donorRequest = donors;
      this.disableAcceptedOrRejectedDonors(); // Disable buttons for accepted or rejected donors
    }, error => {
      Swal.fire("Error", "Failed to fetch donor requests. Please try again later.", "error");
    });
  }

   /**
   * Disable buttons for donors with status 'Accepted' or 'Rejected'.
   */
   disableAcceptedOrRejectedDonors(): void {
    for (let donor of this.donorRequest) {
      if (donor.status === 'Accepted' || donor.status === 'Rejected') {
        donor.disabled = true;
      }
    }
  }

  /**
  * Accepts a donor request.
  * Updates the donor's status to 'Accepted' and saves it to the backend.
  * @param donor The donor request to accept.
  */
  acceptDonor(donor: donor_form): void {
    donor.buttonDisabled = true;
    donor.disabled=true;
    this.data.statusAccept(donor).subscribe(message => {
      Swal.fire("Status Updated As Accepted 👍")
      this.fetchDonorRequests();
    }, error => {
      donor.buttonDisabled = false;
      Swal.fire("Error", "Failed to update status as Accepted. Please try again later.", "error");
    });
  }

  /**
  * Rejects a donor request.
  * Updates the donor's status to 'Rejected' and saves it to the backend.
  * @param donor The donor request to reject.
  */
  rejectDonor(donor: donor_form): void {
    donor.buttonDisabled = true;
    donor.disabled=true;
    this.data.statusReject(donor).subscribe(message => {
      Swal.fire("Status Updated As Rejected 👎")
      this.fetchDonorRequests();
    }, error => {
      donor.buttonDisabled = false;
      Swal.fire("Error", "Failed to update status as Rejected. Please try again later.", "error");
    })
  }

  updateAndStoreDonorRequests(): void {
    localStorage.setItem('donorRequests', JSON.stringify(this.donorRequest));
  }

  /**
  * Filter donor request data based on search term.
  * @param value Search term entered by the user.
  */
  changeData(value: string): void {
    this.currentPage = 1; // Reset to first page
    this.fetchDonorRequests(); // Reload all data
  }


  /**
   * Get paginated donor request items.
   * @returns Paginated donor request items based on current page and page size.
   */
  getPaginatedItems(): donor_form[] {
    const startIndex = (this.currentPage - 1) * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.donorRequest.slice(startIndex, endIndex);
  }


  /**
  * Handle page change event.
  * @param page The new page number.
  */
  onPageChange(page: number): void {
    this.currentPage = page;
  }
}