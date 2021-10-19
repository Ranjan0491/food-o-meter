import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPlaceOrderComponent } from './customer-place-order.component';

describe('CustomerPlaceOrderComponent', () => {
  let component: CustomerPlaceOrderComponent;
  let fixture: ComponentFixture<CustomerPlaceOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerPlaceOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerPlaceOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
