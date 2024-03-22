import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class BodyComponent implements OnInit {

  @Input() collapsed = false;
  @Input() screenWidth = 0;

  constructor() { }
  ngOnInit(): void {
    this.getBodyClass();
  }

  getBodyClass(): string {

    let styleClass = '';
    if (this.collapsed && this.screenWidth > 500) {
      styleClass = 'body-trimmed';
    }
    else if (this.collapsed && this.screenWidth <= 500 && this.screenWidth > 0) {
      styleClass = 'body-md-screen';
    }
    return styleClass;

  }
}
