import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectOrderAddressComponent } from './select-order-address.component';

describe('SelectOrderAddressComponent', () => {
  let component: SelectOrderAddressComponent;
  let fixture: ComponentFixture<SelectOrderAddressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectOrderAddressComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectOrderAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
