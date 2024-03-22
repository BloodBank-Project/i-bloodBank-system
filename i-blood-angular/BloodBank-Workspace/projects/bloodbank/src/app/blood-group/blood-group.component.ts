import { Component, OnInit } from '@angular/core';
import { DataSource } from '../bloodGroup.Model/bloodGroupDatsasource';
import { BloodGroup } from '../bloodGroup.Model/bloodGroupModel';

@Component({
  selector: 'app-blood-group',
  templateUrl: './blood-group.component.html',
  styleUrl: './blood-group.component.css'
})
export class BloodGroupComponent implements OnInit {

  public groups: any = [];

  bloodStock: BloodGroup[] = [];
  bloodTypeCounts: number = 0;

  constructor(private data: DataSource) { }

  ngOnInit(): void {
    this.bloodGroups();
  }

  bloodGroups() {
    this.data.getAllBloodGroups().subscribe(d => {
      this.groups = d;
    })
  }
}