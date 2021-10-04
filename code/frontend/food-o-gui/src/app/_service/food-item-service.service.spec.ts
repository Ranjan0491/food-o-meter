import { TestBed } from '@angular/core/testing';

import { FoodItemServiceService } from './food-item-service.service';

describe('FoodItemServiceService', () => {
  let service: FoodItemServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodItemServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
