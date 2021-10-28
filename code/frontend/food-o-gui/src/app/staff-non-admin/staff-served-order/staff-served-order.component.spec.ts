import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffServedOrderComponent } from './staff-served-order.component';

describe('StaffServedOrderComponent', () => {
  let component: StaffServedOrderComponent;
  let fixture: ComponentFixture<StaffServedOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffServedOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffServedOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
