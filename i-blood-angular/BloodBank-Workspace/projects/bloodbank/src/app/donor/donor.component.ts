import { Component, Inject, OnInit } from '@angular/core';
import { DataSource } from '../../Models/donor.datasource';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-donor',
  templateUrl: './donor.component.html',
  styleUrl: './donor.component.css'
})
export class DonorComponent {
  constructor(
    public dialogRef: MatDialogRef<DonorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  closeDialog(): void {
    this.dialogRef.close();
  }
}