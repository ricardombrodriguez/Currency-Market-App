import { TestBed } from '@angular/core/testing';

import { CoinsServiceService } from './coins-service.service';

describe('CoinsServiceService', () => {
  let service: CoinsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoinsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
