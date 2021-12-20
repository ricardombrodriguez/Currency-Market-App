import { TestBed } from '@angular/core/testing';

import { TickerServiceService } from './ticker-service.service';

describe('TickerServiceService', () => {
  let service: TickerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TickerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
