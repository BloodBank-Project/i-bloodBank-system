import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpadateBloodGroupComponent } from './upadate-blood-group.component';

describe('UpadateBloodGroupComponent', () => {
  let component: UpadateBloodGroupComponent;
  let fixture: ComponentFixture<UpadateBloodGroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpadateBloodGroupComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpadateBloodGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
