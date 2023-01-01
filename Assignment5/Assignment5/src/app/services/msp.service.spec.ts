import { TestBed } from '@angular/core/testing';

import { MspService } from './msp.service';

describe('ParliamentarianService', () => {
  let service: MspService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MspService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
