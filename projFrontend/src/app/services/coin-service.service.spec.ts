import { TestBed } from '@angular/core/testing';

import { CoinServiceService } from './coin-service.service';

describe('CoinServiceService', () => {
  let service: CoinServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoinServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
