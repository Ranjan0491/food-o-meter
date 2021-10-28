import { TestBed } from '@angular/core/testing';

import { FoodOrderServiceService } from './food-order-service.service';

describe('FoodOrderServiceService', () => {
  let service: FoodOrderServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodOrderServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
