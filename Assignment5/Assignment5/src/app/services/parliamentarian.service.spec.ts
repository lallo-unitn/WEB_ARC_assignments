import { TestBed } from '@angular/core/testing';

import { ParliamentarianService } from './parliamentarian.service';

describe('ParliamentarianService', () => {
  let service: ParliamentarianService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParliamentarianService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
